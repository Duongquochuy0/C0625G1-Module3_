package com.example.baitap1.repository;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PawnContractRepository implements IPawnContractRepository {

    @Override
    public List<PawnContractDto> findAll() {
        List<PawnContractDto> result = new ArrayList<>();
        String sql = "SELECT pc.pawn_contract_id, c.`full_name` AS customer_name, e.`full_name` AS employee_name, " +
                "p.product_name, pc.pawn_date, pc.pawn_price, pc.interest_rate, pc.due_date, pc.return_date, " +
                "CASE WHEN pc.return_date IS NOT NULL THEN 'CLOSED' " +
                "WHEN EXISTS (SELECT 1 FROM liquidation_contract lc WHERE lc.product_id = pc.product_id) THEN 'LIQUIDATED' " +
                "ELSE 'ACTIVE' END AS status " +
                "FROM pawn_contract pc " +
                "LEFT JOIN customer c ON pc.customer_id = c.customer_id " +
                "LEFT JOIN employee e ON pc.employee_id = e.employee_id " +
                "LEFT JOIN product p ON pc.product_id = p.product_id " +
                "ORDER BY pc.pawn_contract_id DESC";

        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.add(mapToPawnContractDto(rs));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong findAll: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách hợp đồng cầm: " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean add(PawnContract pawnContract) {
        String sql = "INSERT INTO pawn_contract (customer_id, employee_id, product_id, pawn_date, pawn_price, interest_rate, due_date, return_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pawnContract.getCustomerId());
            stmt.setInt(2, pawnContract.getEmployeeId());
            stmt.setInt(3, pawnContract.getProductId());
            stmt.setObject(4, pawnContract.getPawnDate());
            stmt.setBigDecimal(5, pawnContract.getPawnPrice());
            stmt.setBigDecimal(6, pawnContract.getInterestRate());
            stmt.setObject(7, pawnContract.getDueDate());
            stmt.setObject(8, pawnContract.getReturnDate());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong add: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm hợp đồng cầm: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM pawn_contract WHERE pawn_contract_id = ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong delete: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi xóa hợp đồng cầm: " + e.getMessage());
        }
    }

    @Override
    public boolean update(PawnContract pawnContract) {
        String sql = "UPDATE pawn_contract SET customer_id = ?, employee_id = ?, product_id = ?, " +
                "pawn_date = ?, pawn_price = ?, interest_rate = ?, due_date = ?, return_date = ? " +
                "WHERE pawn_contract_id = ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pawnContract.getCustomerId());
            stmt.setInt(2, pawnContract.getEmployeeId());
            stmt.setInt(3, pawnContract.getProductId());
            stmt.setObject(4, pawnContract.getPawnDate());
            stmt.setBigDecimal(5, pawnContract.getPawnPrice());
            stmt.setBigDecimal(6, pawnContract.getInterestRate());
            stmt.setObject(7, pawnContract.getDueDate());
            stmt.setObject(8, pawnContract.getReturnDate());
            stmt.setInt(9, pawnContract.getPawnContractId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong update: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật hợp đồng cầm: " + e.getMessage());
        }
    }

    @Override
    public PawnContractDto findById(int id) {
        String sql = "SELECT pc.pawn_contract_id, c.`full_name` AS customer_name, e.`full_name` AS employee_name, " +
                "p.product_name, pc.pawn_date, pc.pawn_price, pc.interest_rate, pc.due_date, pc.return_date, " +
                "CASE WHEN pc.return_date IS NOT NULL THEN 'CLOSED' " +
                "WHEN EXISTS (SELECT 1 FROM liquidation_contract lc WHERE lc.product_id = pc.product_id) THEN 'LIQUIDATED' " +
                "ELSE 'ACTIVE' END AS status " +
                "FROM pawn_contract pc " +
                "LEFT JOIN customer c ON pc.customer_id = c.customer_id " +
                "LEFT JOIN employee e ON pc.employee_id = e.employee_id " +
                "LEFT JOIN product p ON pc.product_id = p.product_id " +
                "WHERE pc.pawn_contract_id = ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToPawnContractDto(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong findById: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tìm hợp đồng cầm theo ID: " + e.getMessage());
        }
    }

    @Override
    public List<PawnContractDto> search(String customerName, String employeeName, String productName) {
        List<PawnContractDto> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT pc.pawn_contract_id, c.`full_name` AS customer_name, e.`full_name` AS employee_name, " +
                        "p.product_name, pc.pawn_date, pc.pawn_price, pc.interest_rate, pc.due_date, pc.return_date, " +
                        "CASE WHEN pc.return_date IS NOT NULL THEN 'CLOSED' " +
                        "WHEN EXISTS (SELECT 1 FROM liquidation_contract lc WHERE lc.product_id = pc.product_id) THEN 'LIQUIDATED' " +
                        "ELSE 'ACTIVE' END AS status " +
                        "FROM pawn_contract pc " +
                        "LEFT JOIN customer c ON pc.customer_id = c.customer_id " +
                        "LEFT JOIN employee e ON pc.employee_id = e.employee_id " +
                        "LEFT JOIN product p ON pc.product_id = p.product_id WHERE 1=1");
        List<String> params = new ArrayList<>();

        if (customerName != null && !customerName.trim().isEmpty()) {
            sql.append(" AND c.`full_name` LIKE ?");
            params.add("%" + customerName + "%");
        }
        if (employeeName != null && !employeeName.trim().isEmpty()) {
            sql.append(" AND e.`full_name` LIKE ?");
            params.add("%" + employeeName + "%");
        }
        if (productName != null && !productName.trim().isEmpty()) {
            sql.append(" AND p.product_name LIKE ?");
            params.add("%" + productName + "%");
        }
        sql.append(" ORDER BY pc.pawn_contract_id DESC");

        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapToPawnContractDto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong search: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tìm kiếm hợp đồng cầm: " + e.getMessage());
        }
        return result;
    }

    @Override
    public PawnContract findByIdProduct(int id) {
        String sql = "SELECT pc.pawn_contract_id, pc.customer_id, pc.employee_id, pc.product_id, pc.pawn_date, " +
                "pc.pawn_price, pc.interest_rate, pc.due_date, pc.return_date " +
                "FROM pawn_contract pc WHERE pc.product_id = ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToPawnContract(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong findByIdProduct: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tìm hợp đồng cầm theo ID sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public List<PawnContractDto> findAll(int offset, int limit) {
        List<PawnContractDto> result = new ArrayList<>();
        String sql = "SELECT pc.pawn_contract_id, c.`full_name` AS customer_name, e.`full_name` AS employee_name, " +
                "p.product_name, pc.pawn_date, pc.pawn_price, pc.interest_rate, pc.due_date, pc.return_date, " +
                "CASE WHEN pc.return_date IS NOT NULL THEN 'CLOSED' " +
                "WHEN EXISTS (SELECT 1 FROM liquidation_contract lc WHERE lc.product_id = pc.product_id) THEN 'LIQUIDATED' " +
                "ELSE 'ACTIVE' END AS status " +
                "FROM pawn_contract pc " +
                "LEFT JOIN customer c ON pc.customer_id = c.customer_id " +
                "LEFT JOIN employee e ON pc.employee_id = e.employee_id " +
                "LEFT JOIN product p ON pc.product_id = p.product_id " +
                "ORDER BY pc.pawn_contract_id DESC " +
                "LIMIT ? OFFSET ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(mapToPawnContractDto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong findAll(offset, limit): " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách hợp đồng cầm với phân trang: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int countPawnContract() {
        String sql = "SELECT COUNT(*) FROM pawn_contract";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL trong countPawnContract: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi đếm số lượng hợp đồng cầm: " + e.getMessage());
        }
    }

    private PawnContractDto mapToPawnContractDto(ResultSet rs) throws SQLException {
        return new PawnContractDto(
                rs.getInt("pawn_contract_id"),
                rs.getString("customer_name"),
                rs.getString("employee_name"),
                rs.getString("product_name"),
                rs.getDate("pawn_date") != null ? rs.getDate("pawn_date").toLocalDate() : null,
                rs.getBigDecimal("pawn_price"),
                rs.getBigDecimal("interest_rate"),
                rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                rs.getString("status")
        );
    }

    private PawnContract mapToPawnContract(ResultSet rs) throws SQLException {
        return new PawnContract(
                rs.getInt("pawn_contract_id"),
                rs.getInt("customer_id"),
                rs.getInt("employee_id"),
                rs.getInt("product_id"),
                rs.getDate("pawn_date") != null ? rs.getDate("pawn_date").toLocalDate() : null,
                rs.getBigDecimal("pawn_price"),
                rs.getBigDecimal("interest_rate"),
                rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
                rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
        );
    }

    public static void main(String[] args) {
        PawnContractRepository repo = new PawnContractRepository();
        List<PawnContractDto> list = repo.findAll();
        System.out.println("Số hợp đồng: " + list.size());
        for (PawnContractDto c : list) {
            System.out.println(c.getPawnContractId() + " - " + c.getCustomerName());
        }
    }
}
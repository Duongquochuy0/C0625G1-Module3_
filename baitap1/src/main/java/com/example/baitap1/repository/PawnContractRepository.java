package com.example.baitap1.repository;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PawnContractRepository implements IPawnContractRepository {

    private static final String SELECT_ALL_DTO =
            "SELECT pc.pawn_contract_id, pc.pawn_price, pc.interest_rate, pc.pawn_date, pc.due_date, pc.return_date, " +
                    "c.full_name AS customer_name, e.full_name AS employee_name, p.product_name " +
                    "FROM pawn_contract pc " +
                    "JOIN customer c ON pc.customer_id = c.customer_id " +
                    "JOIN employee e ON pc.employee_id = e.employee_id " +
                    "JOIN product p ON pc.product_id = p.product_id";

    private static final String INSERT_CONTRACT =
            "INSERT INTO pawn_contract (customer_id, employee_id, product_id, pawn_date, pawn_price, interest_rate, due_date, return_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_CONTRACT =
            "UPDATE pawn_contract SET customer_id=?, employee_id=?, product_id=?, pawn_date=?, pawn_price=?, interest_rate=?, due_date=?, return_date=? " +
                    "WHERE pawn_contract_id=?";

    private static final String DELETE_CONTRACT =
            "DELETE FROM pawn_contract WHERE pawn_contract_id=?";

    private static final String SELECT_BY_ID = SELECT_ALL_DTO + " WHERE pc.pawn_contract_id=?";

    private static final String SEARCH_CONTRACT =
            "SELECT pc.pawn_contract_id, pc.pawn_price, pc.interest_rate, pc.pawn_date, pc.due_date, pc.return_date, " +
                    "c.full_name AS customer_name, e.full_name AS employee_name, p.product_name " +
                    "FROM pawn_contract pc " +
                    "JOIN customer c ON pc.customer_id = c.customer_id " +
                    "JOIN employee e ON pc.employee_id = e.employee_id " +
                    "JOIN product p ON pc.product_id = p.product_id " +
                    "WHERE c.full_name LIKE ? AND e.full_name LIKE ? AND p.product_name LIKE ?";

    @Override
    public List<PawnContractDto> findAll() {
        List<PawnContractDto> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_DTO);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean add(PawnContract pawnContract) {
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(INSERT_CONTRACT)) {

            ps.setInt(1, pawnContract.getCustomerId());
            ps.setInt(2, pawnContract.getEmployeeId());
            ps.setInt(3, pawnContract.getProductId());
            ps.setDate(4, Date.valueOf(pawnContract.getPawnDate()));
            ps.setBigDecimal(5, pawnContract.getPawnPrice());
            ps.setBigDecimal(6, pawnContract.getInterestRate());
            ps.setDate(7, Date.valueOf(pawnContract.getDueDate()));
            if (pawnContract.getReturnDate() != null) {
                ps.setDate(8, Date.valueOf(pawnContract.getReturnDate()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(DELETE_CONTRACT)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(PawnContract pawnContract) {
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CONTRACT)) {

            ps.setInt(1, pawnContract.getCustomerId());
            ps.setInt(2, pawnContract.getEmployeeId());
            ps.setInt(3, pawnContract.getProductId());
            ps.setDate(4, Date.valueOf(pawnContract.getPawnDate()));
            ps.setBigDecimal(5, pawnContract.getPawnPrice());
            ps.setBigDecimal(6, pawnContract.getInterestRate());
            ps.setDate(7, Date.valueOf(pawnContract.getDueDate()));
            if (pawnContract.getReturnDate() != null) {
                ps.setDate(8, Date.valueOf(pawnContract.getReturnDate()));
            } else {
                ps.setNull(8, Types.DATE);
            }
            ps.setInt(9, pawnContract.getPawnContractId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PawnContractDto findById(int id) {
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDto(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PawnContractDto> search(String customerName, String employeeName, String productName) {
        List<PawnContractDto> list = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(SEARCH_CONTRACT)) {

            ps.setString(1, "%" + (customerName == null ? "" : customerName) + "%");
            ps.setString(2, "%" + (employeeName == null ? "" : employeeName) + "%");
            ps.setString(3, "%" + (productName == null ? "" : productName) + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDto(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PawnContract findByIdProduct(int id) {
        String sql = "SELECT * FROM pawn_contract WHERE product_id=?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PawnContract pc = new PawnContract();
                    pc.setPawnContractId(rs.getInt("pawn_contract_id"));
                    pc.setCustomerId(rs.getInt("customer_id"));
                    pc.setEmployeeId(rs.getInt("employee_id"));
                    pc.setProductId(rs.getInt("product_id"));
                    pc.setPawnDate(rs.getDate("pawn_date").toLocalDate());
                    pc.setPawnPrice(rs.getBigDecimal("pawn_price"));
                    pc.setInterestRate(rs.getBigDecimal("interest_rate"));
                    pc.setDueDate(rs.getDate("due_date").toLocalDate());
                    Date returnDate = rs.getDate("return_date");
                    if (returnDate != null) pc.setReturnDate(returnDate.toLocalDate());
                    return pc;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PawnContractDto> findAll(int offset, int limit) {
        List<PawnContractDto> list = new ArrayList<>();
        String sql = SELECT_ALL_DTO + " LIMIT ? OFFSET ?";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDto(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countPawnContract() {
        String sql = "SELECT COUNT(*) AS total FROM pawn_contract";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt("total");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ================= Helper =================
    private PawnContractDto mapResultSetToDto(ResultSet rs) throws SQLException {
        PawnContractDto dto = new PawnContractDto();
        dto.setPawnContractId(rs.getInt("pawn_contract_id"));
        dto.setPawnPrice(rs.getBigDecimal("pawn_price"));
        dto.setInterestRate(rs.getBigDecimal("interest_rate"));
        dto.setPawnDate(rs.getDate("pawn_date").toLocalDate());
        dto.setDueDate(rs.getDate("due_date").toLocalDate());
        Date returnDate = rs.getDate("return_date");
        if (returnDate != null) dto.setReturnDate(returnDate.toLocalDate());
        dto.setCustomerName(rs.getString("customer_name"));
        dto.setEmployeeName(rs.getString("employee_name"));
        dto.setProductName(rs.getString("product_name"));

        // Status: ACTIVE nếu chưa trả, CLOSED nếu đã trả
        if (dto.getReturnDate() == null) dto.setContractStatus("ACTIVE");
        else dto.setContractStatus("CLOSED");

        return dto;
    }
}

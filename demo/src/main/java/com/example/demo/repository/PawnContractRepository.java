package com.example.demo.repository;

import com.example.demo.dto.PawnContractDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PawnContractRepository implements IPawnContractRepository {

    private static final String SELECT_PAWN_CONTRACTS =
            "SELECT p.pawn_contract_id, c.full_name AS customer_name, e.full_name AS employee_name, " +
                    "pr.product_name, p.pawn_date, p.pawn_price, p.interest_rate, p.due_date, p.return_date " +
                    "FROM pawn_contract p " +
                    "JOIN customer c ON p.customer_id = c.customer_id " +
                    "JOIN employee e ON p.employee_id = e.employee_id " +
                    "JOIN product pr ON p.product_id = pr.product_id";

    @Override
    public List<PawnContractDto> getAll() {
        List<PawnContractDto> list = new ArrayList<>();

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_PAWN_CONTRACTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PawnContractDto dto = new PawnContractDto();
                dto.setPawnContractId(rs.getInt("pawn_contract_id"));
                dto.setCustomerName(rs.getString("customer_name"));
                dto.setEmployeeName(rs.getString("employee_name"));
                dto.setProductName(rs.getString("product_name"));
                dto.setPawnDate(rs.getDate("pawn_date").toLocalDate());
                dto.setPawnPrice(rs.getBigDecimal("pawn_price"));
                dto.setInterestRate(rs.getBigDecimal("interest_rate"));
                dto.setDueDate(rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null);
                dto.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);

                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

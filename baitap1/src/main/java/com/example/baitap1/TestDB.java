package com.example.baitap1;

import com.example.baitap1.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDB {
    public static void main(String[] args) {
        String sql =  "SELECT \n" +
                "    c.pawn_contract_id,\n" +
                "    c.customer_id,\n" +
                "    c.employee_id,\n" +
                "    c.product_id,\n" +
                "    c.pawn_price,\n" +
                "    c.interest_rate,\n" +
                "    c.pawn_date,\n" +
                "    c.due_date,\n" +
                "    c.return_date,\n" +
                "    p.status AS product_status, -- sửa đây\n" +
                "    cu.full_name AS customer_name,\n" +
                "    cu.phone_number AS customer_phone, -- sửa đây\n" +
                "    e.full_name AS employee_name,\n" +
                "    p.product_name\n" +
                "FROM pawn_contract c\n" +
                "JOIN customer cu ON c.customer_id = cu.customer_id\n" +
                "JOIN employee e ON c.employee_id = e.employee_id\n" +
                "JOIN product p ON c.product_id = p.product_id";
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("📌 Contract ID: " + rs.getInt("pawn_contract_id")
                        + " | Customer: " + rs.getString("customer_name")
                        + " | Employee: " + rs.getString("employee_name")
                        + " | Product: " + rs.getString("product_name")
                        + " | Price: " + rs.getDouble("pawn_price"));
            }
            System.out.println("📊 Tổng số hợp đồng lấy được: " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

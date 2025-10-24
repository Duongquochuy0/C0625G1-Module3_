    package com.example.bai_thi.repository;

    import com.example.bai_thi.entity.HinhThucThanhToan;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class HinhThucThanhToanRepository implements IHinhThucThanhToanRepository {

        private static final String SELECT_ALL = "SELECT * FROM hinh_thuc_thanh_toan";

        @Override
        public List<HinhThucThanhToan> findAll() {
            List<HinhThucThanhToan> list = new ArrayList<>();

            try (Connection connection = BaseRepository.getConnectDB();
                 PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String ten = rs.getString("ten");

                    HinhThucThanhToan hinhThuc = new HinhThucThanhToan(id, ten);
                    list.add(hinhThuc);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return list;
        }
    }

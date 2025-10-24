package com.example.bai_thi.repository;

import com.example.bai_thi.entity.NhaTro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhaTroRepository implements INhaTroRepository {

    private static final String SELECT_ALL =
            "SELECT n.id, n.ma_nha_tro, n.ten, n.so_dien_thoai, n.ngay_bat_dau, n.ghi_chu, " +
                    "n.hinh_thuc_thanh_toan_id, h.ten AS ten_hinh_thuc_thanh_toan " +
                    "FROM nha_tro n JOIN hinh_thuc_thanh_toan h ON n.hinh_thuc_thanh_toan_id = h.id";

    private static final String INSERT_NHA_TRO =
            "INSERT INTO nha_tro (ma_nha_tro, ten, so_dien_thoai, ngay_bat_dau, ghi_chu, hinh_thuc_thanh_toan_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String DELETE_NHA_TRO = "DELETE FROM nha_tro WHERE id = ?";

    private static final String SELECT_BY_ID =
            "SELECT n.id, n.ma_nha_tro, n.ten, n.so_dien_thoai, n.ngay_bat_dau, n.ghi_chu, " +
                    "n.hinh_thuc_thanh_toan_id, h.ten AS ten_hinh_thuc_thanh_toan " +
                    "FROM nha_tro n JOIN hinh_thuc_thanh_toan h ON n.hinh_thuc_thanh_toan_id = h.id WHERE n.id = ?";

    @Override
    public List<NhaTro> findAll() {
        List<NhaTro> nhaTroList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Date date = rs.getDate("ngay_bat_dau");
                LocalDate ngayBatDau = date != null ? date.toLocalDate() : null;

                NhaTro nhaTro = new NhaTro(
                        rs.getInt("id"),
                        rs.getString("ma_nha_tro"),
                        rs.getString("ten"),
                        rs.getString("so_dien_thoai"),
                        ngayBatDau,
                        rs.getString("ten_hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu"),
                        rs.getInt("hinh_thuc_thanh_toan_id")
                );
                nhaTroList.add(nhaTro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaTroList;
    }

    @Override
    public boolean add(NhaTro nhaTro) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(INSERT_NHA_TRO)) {

            ps.setString(1, nhaTro.getMaNhaTro());
            ps.setString(2, nhaTro.getTen());
            ps.setString(3, nhaTro.getSoDienThoai());
            ps.setDate(4, Date.valueOf(nhaTro.getNgayBatDauThue()));
            ps.setString(5, nhaTro.getGhiChu());
            ps.setInt(6, nhaTro.getHinhThucThanhToanID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(DELETE_NHA_TRO)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public NhaTro findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date date = rs.getDate("ngay_bat_dau");
                LocalDate ngayBatDau = date != null ? date.toLocalDate() : null;

                return new NhaTro(
                        rs.getInt("id"),
                        rs.getString("ma_nha_tro"),
                        rs.getString("ten"),
                        rs.getString("so_dien_thoai"),
                        ngayBatDau,
                        rs.getString("ten_hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu"),
                        rs.getInt("hinh_thuc_thanh_toan_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NhaTro> search(String maNhaTro, String name, String soDienThoai) {
        List<NhaTro> nhaTroList = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT n.id, n.ma_nha_tro, n.ten, n.so_dien_thoai, n.ngay_bat_dau, n.ghi_chu, " +
                        "n.hinh_thuc_thanh_toan_id, h.ten AS ten_hinh_thuc_thanh_toan " +
                        "FROM nha_tro n JOIN hinh_thuc_thanh_toan h ON n.hinh_thuc_thanh_toan_id = h.id " +
                        "WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (maNhaTro != null && !maNhaTro.trim().isEmpty()) {
            sql.append("AND n.ma_nha_tro LIKE ? ");
            params.add("%" + maNhaTro.trim() + "%");
        }

        if (name != null && !name.trim().isEmpty()) {
            sql.append("AND n.ten LIKE ? ");
            params.add("%" + name.trim() + "%");
        }

        if (soDienThoai != null && !soDienThoai.trim().isEmpty()) {
            sql.append("AND n.so_dien_thoai LIKE ? ");
            params.add("%" + soDienThoai.trim() + "%");
        }

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("ngay_bat_dau");
                LocalDate ngayBatDau = (date != null) ? date.toLocalDate() : null;

                NhaTro nhaTro = new NhaTro(
                        rs.getInt("id"),
                        rs.getString("ma_nha_tro"),
                        rs.getString("ten"),
                        rs.getString("so_dien_thoai"),
                        ngayBatDau,
                        rs.getString("ten_hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu"),
                        rs.getInt("hinh_thuc_thanh_toan_id")
                );
                nhaTroList.add(nhaTro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhaTroList;
    }


    @Override
    public NhaTro findByIdHinhThucThanhToan(int id) {
        String sql =
                "SELECT n.id, n.ma_nha_tro, n.ten, n.so_dien_thoai, n.ngay_bat_dau, n.ghi_chu, " +
                        "n.hinh_thuc_thanh_toan_id, h.ten AS ten_hinh_thuc_thanh_toan " +
                        "FROM nha_tro n JOIN hinh_thuc_thanh_toan h ON n.hinh_thuc_thanh_toan_id = h.id " +
                        "WHERE h.id = ?";

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date date = rs.getDate("ngay_bat_dau");
                LocalDate ngayBatDau = date != null ? date.toLocalDate() : null;

                return new NhaTro(
                        rs.getInt("id"),
                        rs.getString("ma_nha_tro"),
                        rs.getString("ten"),
                        rs.getString("so_dien_thoai"),
                        ngayBatDau,
                        rs.getString("ten_hinh_thuc_thanh_toan"),
                        rs.getString("ghi_chu"),
                        rs.getInt("hinh_thuc_thanh_toan_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean deleteAll(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return false;

        StringBuilder sql = new StringBuilder("DELETE FROM nha_tro WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            sql.append("?");
            if (i < ids.size() - 1) sql.append(",");
        }
        sql.append(")");

        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

package com.example.thi_thu.repository;

import com.example.thi_thu.entity.Heo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeoRepository implements IHeoRepository {
    private static final String SELECT_ALL = "select h.*, x.ten as ten_xuat_xu from heo h join xuat_xu x on h.xuat_xu_id = x.id order by h.id";
    private static final String SELECT_BY_ID = "select h.*, x.ten as ten_xuat_xu from heo h join xuat_xu x on h.xuat_xu_id = x.id where h.id = ?";
    private static final String DELETE_BY_ID = "delete from heo where id = ?";
    private static final String UPDATE_SQL = "update heo set ma_heo=?, ngay_nhap=?, trong_luong_nhap=?, ngay_xuat=?, trong_luong_xuat=?, xuat_xu_id=? where id=?";
    private static final String FIND_TOP_HEO = "select h.*, x.ten as ten_xuat_xu from heo h join xuat_xu x on h.xuat_xu_id = x.id where h.ngay_xuat is not null order by h.trong_luong_xuat desc limit ?";

    @Override
    public List<Heo> findAll() {
        List<Heo> list = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Heo heo = new Heo();
                heo.setId(rs.getInt("id"));
                heo.setMaHeo(rs.getString("ma_heo"));
                heo.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate());
                heo.setTrongLuongNhap(rs.getInt("trong_luong_nhap"));
                heo.setIdXuatXu(rs.getInt("xuat_xu_id"));
                heo.setXuatXu(rs.getString("ten_xuat_xu"));

                Date ngayXuat = rs.getDate("ngay_xuat");
                if (ngayXuat != null) {
                    heo.setNgayXuat(ngayXuat.toLocalDate());
                }
                int trongLuongXuat = rs.getInt("trong_luong_xuat");
                if (!rs.wasNull()) {
                    heo.setTrongLuongXuat(trongLuongXuat);
                }
                list.add(heo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Heo> search(String maHeo, String tinhTrang, String xuatXu) {
        List<Heo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT h.*, x.ten AS ten_xuat_xu from heo h join xuat_xu x on h.xuat_xu_id = x.id where 1=1");

        if (maHeo != null && !maHeo.isEmpty()) {
            sql.append(" and h.ma_heo like ?");
        }
        if (tinhTrang != null && !tinhTrang.isEmpty()) {
            if (tinhTrang.equalsIgnoreCase("Đã bán")) {
                sql.append(" and h.ngay_xuat is not null");
            } else if (tinhTrang.equalsIgnoreCase("Chưa bán")) {
                sql.append(" and h.ngay_xuat is null");
            }
        }
        if (xuatXu != null && !xuatXu.isEmpty()) {
            sql.append(" and x.ten like ?");
        }

        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (maHeo != null && !maHeo.isEmpty()) {
                ps.setString(index++, "%" + maHeo + "%");
            }
            if (xuatXu != null && !xuatXu.isEmpty()) {
                ps.setString(index++, "%" + xuatXu + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Heo heo = new Heo();
                heo.setId(rs.getInt("id"));
                heo.setMaHeo(rs.getString("ma_heo"));
                heo.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate());
                heo.setTrongLuongNhap(rs.getInt("trong_luong_nhap"));
                heo.setIdXuatXu(rs.getInt("xuat_xu_id"));
                heo.setXuatXu(rs.getString("ten_xuat_xu"));

                Date ngayXuat = rs.getDate("ngay_xuat");
                if (ngayXuat != null) {
                    heo.setNgayXuat(ngayXuat.toLocalDate());
                }
                int trongLuongXuat = rs.getInt("trong_luong_xuat");
                if (!rs.wasNull()) {
                    heo.setTrongLuongXuat(trongLuongXuat);
                }
                list.add(heo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public Heo findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Heo heo = new Heo();
                heo.setId(rs.getInt("id"));
                heo.setMaHeo(rs.getString("ma_heo"));
                heo.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate());
                heo.setTrongLuongNhap(rs.getInt("trong_luong_nhap"));
                heo.setIdXuatXu(rs.getInt("xuat_xu_id"));
                heo.setXuatXu(rs.getString("ten_xuat_xu"));

                Date ngayXuat = rs.getDate("ngay_xuat");
                if (ngayXuat != null) {
                    heo.setNgayXuat(ngayXuat.toLocalDate());
                }
                int trongLuongXuat = rs.getInt("trong_luong_xuat");
                if (!rs.wasNull()) {
                    heo.setTrongLuongXuat(trongLuongXuat);
                }
                return heo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(Heo heo) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, heo.getMaHeo());
            ps.setDate(2, Date.valueOf(heo.getNgayNhap()));
            ps.setInt(3, heo.getTrongLuongNhap());

            if (heo.getNgayXuat() != null) {
                ps.setDate(4, Date.valueOf(heo.getNgayXuat()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            if (heo.getTrongLuongXuat() != null && heo.getTrongLuongXuat() > 0) {
                ps.setInt(5, heo.getTrongLuongXuat());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, heo.getIdXuatXu());
            ps.setInt(7, heo.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean delete(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Heo> findTopHeoByTrongLuongXuat(int limit) {
        List<Heo> list = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(FIND_TOP_HEO)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Heo heo = new Heo();
                heo.setId(rs.getInt("id"));
                heo.setMaHeo(rs.getString("ma_heo"));
                heo.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate());
                heo.setTrongLuongNhap(rs.getInt("trong_luong_nhap"));
                heo.setIdXuatXu(rs.getInt("xuat_xu_id"));
                heo.setXuatXu(rs.getString("ten_xuat_xu"));

                Date ngayXuat = rs.getDate("ngay_xuat");
                if (ngayXuat != null) {
                    heo.setNgayXuat(ngayXuat.toLocalDate());
                }
                int trongLuongXuat = rs.getInt("trong_luong_xuat");
                if (!rs.wasNull()) {
                    heo.setTrongLuongXuat(trongLuongXuat);
                }
                list.add(heo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

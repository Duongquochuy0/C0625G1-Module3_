package com.example.thi_thu.repository;

import com.example.thi_thu.entity.XuatXu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class XuatXuRepository implements IXuatXuRepository{
    private static final String SELECT_ALL = "SELECT * FROM xuat_xu";
    @Override
    public List<XuatXu> findAll() {
        List<XuatXu> list = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu x = new XuatXu();
                x.setId(rs.getInt("id"));
                x.setTen(rs.getString("ten"));
                list.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

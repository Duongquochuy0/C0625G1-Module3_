package com.example.quan_ly_sinh_vien.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository { private static final String URL ="jdbc:mysql://localhost:3306/quan_ly_nhan_vien"; // sửa lại tên của csdl
    private static final String USER ="root";// mặc định của mysql
    private static final String PASS ="codegym";// do cài đặt khi cài đặt mysql
    public static Connection getConnectDB(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}

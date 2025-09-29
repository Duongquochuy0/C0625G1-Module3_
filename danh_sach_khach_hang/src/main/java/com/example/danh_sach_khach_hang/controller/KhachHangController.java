package com.example.danh_sach_khach_hang.controller;

import com.example.danh_sach_khach_hang.entity.KhachHang;
import com.example.danh_sach_khach_hang.service.IKhachHangService;
import com.example.danh_sach_khach_hang.service.KhachHangService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "KhachHangController", value = "/khachhang")
public class KhachHangController extends HttpServlet {
    private IKhachHangService khachHangService = new KhachHangService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action) {
            case "add":
                // trả về form thêm mới
                req.getRequestDispatcher("views/khachhang/add.jsp").forward(req, resp);

                break;
            case "delete":
                // xoá
                break;
            default:
                List<KhachHang> khachHangList = khachHangService.findAll();
                req.setAttribute("khachHangList", khachHangList);
                req.getRequestDispatcher("views/khachhang/list.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action==null){
//            action="";
//        }
//        switch (action) {
//            case "add":
//                // trả về form thêm mới
//                int id = Integer.parseInt(req.getParameter("id"));
//                float score = Float.parseFloat(req.getParameter("score"));
//                String name = req.getParameter("Name");
//                boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
//                Student student = new Student(id,name,gender,score);
//                studentService.add(student);
//                List<Student> studentList2 = studentService.findAll();
//                req.setAttribute("studentList", studentList2);
//                req.getRequestDispatcher("views/student/list.jsp").forward(req, resp);
//
//                break;
//            case "delete":
//                // xoá
//                break;
//            default:
//                List<Student> studentList = studentService.findAll();
//                req.setAttribute("studentList", studentList);
//                req.getRequestDispatcher("views/student/list.jsp").forward(req, resp);
//        }
//    }
}
}
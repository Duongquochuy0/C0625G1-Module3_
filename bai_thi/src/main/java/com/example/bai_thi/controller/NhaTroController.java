package com.example.bai_thi.controller;

import com.example.bai_thi.entity.HinhThucThanhToan;
import com.example.bai_thi.entity.NhaTro;
import com.example.bai_thi.service.HinhThucThanhToanService;
import com.example.bai_thi.service.IHinhThucThanhToanService;
import com.example.bai_thi.service.INhaTroService;
import com.example.bai_thi.service.NhaTroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "NhaTroController", urlPatterns = "/nhaTro")
public class NhaTroController extends HttpServlet {
    private final INhaTroService nhaTroService = new NhaTroService();
    private final IHinhThucThanhToanService hinhThucThanhToanService = new HinhThucThanhToanService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create" -> showCreateForm(request, response);
            case "delete" -> delete(request, response);
            default -> listNhaTro(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create" -> createNhaTro(request, response);
            case "search" -> search(request, response);
            default -> listNhaTro(request, response);
        }
    }

    private void listNhaTro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NhaTro> listNhaTro = nhaTroService.findAll();
        request.setAttribute("listNhaTro", listNhaTro);
        request.getRequestDispatcher("/views/nha_tro/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<HinhThucThanhToan> listHTTT = hinhThucThanhToanService.findAll();
        request.setAttribute("listHTTT", listHTTT);
        request.getRequestDispatcher("/views/nha_tro/create.jsp").forward(request, response);
    }

    private void createNhaTro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maNhaTro = request.getParameter("maNhaTro");
        String ten = request.getParameter("ten");
        String soDienThoai = request.getParameter("soDienThoai");
        String ngayBatDauStr = request.getParameter("ngayBatDau");
        String ghiChu = request.getParameter("ghiChu");
        String hinhThucThanhToanIdStr = request.getParameter("hinhThucThanhToanId");

        String error = null;
        if (maNhaTro == null || maNhaTro.trim().isEmpty()) {
            error = "Mã nhà trọ không được để trống!";
        } else if (ten == null || ten.trim().isEmpty()) {
            error = "Tên nhà trọ không được để trống!";
        } else if (soDienThoai == null || !soDienThoai.matches("\\d{9,11}")) {
            error = "Số điện thoại phải gồm 9–11 chữ số!";
        } else if (ngayBatDauStr == null || ngayBatDauStr.isEmpty()) {
            error = "Vui lòng chọn ngày bắt đầu!";
        } else if (hinhThucThanhToanIdStr == null || hinhThucThanhToanIdStr.isEmpty()) {
            error = "Vui lòng chọn hình thức thanh toán!";
        }
        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("listHTTT", hinhThucThanhToanService.findAll());
            request.getRequestDispatcher("/views/nha_tro/create.jsp").forward(request, response);
            return;
        }
        LocalDate ngayBatDau;
        try {
            ngayBatDau = LocalDate.parse(ngayBatDauStr);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Định dạng ngày không hợp lệ!");
            request.setAttribute("listHTTT", hinhThucThanhToanService.findAll());
            request.getRequestDispatcher("/views/nha_tro/create.jsp").forward(request, response);
            return;
        }

        int hinhThucThanhToanID = Integer.parseInt(hinhThucThanhToanIdStr);

        NhaTro nhaTro = new NhaTro(0, maNhaTro.trim(), ten.trim(), soDienThoai.trim(),
                ngayBatDau, null, ghiChu, hinhThucThanhToanID);

        boolean isAdded = nhaTroService.add(nhaTro);

        if (isAdded) {
            response.sendRedirect("/nhaTro");
        } else {
            request.setAttribute("error", "Thêm mới thất bại! Mã nhà trọ có thể đã tồn tại.");
            request.setAttribute("listHTTT", hinhThucThanhToanService.findAll());
            request.getRequestDispatcher("/views/nha_tro/create.jsp").forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                nhaTroService.delete(id);
            } catch (NumberFormatException e) {
                System.out.println("ID không hợp lệ: " + idStr);
            }
        }
        response.sendRedirect("/nhaTro");
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maNhaTro = request.getParameter("maNhaTro");
        String ten = request.getParameter("ten");
        String soDienThoai = request.getParameter("soDienThoai");

        List<NhaTro> list = nhaTroService.search(maNhaTro, ten, soDienThoai);
        request.setAttribute("listNhaTro", list);
        request.getRequestDispatcher("/views/nha_tro/list.jsp").forward(request, response);
    }
}

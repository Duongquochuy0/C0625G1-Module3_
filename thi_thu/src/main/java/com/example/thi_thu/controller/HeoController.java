package com.example.thi_thu.controller;

import com.example.thi_thu.entity.Heo;
import com.example.thi_thu.entity.XuatXu;
import com.example.thi_thu.service.HeoService;
import com.example.thi_thu.service.IHeoService;
import com.example.thi_thu.service.IXuatXuService;
import com.example.thi_thu.service.XuatXuService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "HeoController", urlPatterns = "/heo")
public class HeoController extends HttpServlet {
    private IHeoService heoService = new HeoService();
    private IXuatXuService xuatXuService = new XuatXuService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                deleteHeo(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "top":
                showTopHeo(request, response);
                break;
            default:
                showListHeo(request, response);
                break;
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
            case "update":
                updateHeo(request, response);
                break;
            case "search":
                searchHeo(request, response);
                break;
            default:
                response.sendRedirect("/heo");
        }
    }

    private void showListHeo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Heo> list = heoService.findAll();
        List<XuatXu> listXuatXu = xuatXuService.findAll();
        request.setAttribute("listHeo", list);
        request.setAttribute("listXuatXu", listXuatXu);
        request.getRequestDispatcher("/views/heo/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Heo heo = heoService.findById(id);
        request.setAttribute("heo", heo);
        request.getRequestDispatcher("/views/heo/update.jsp").forward(request, response);
    }

    private void updateHeo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String maHeo = request.getParameter("maHeo");
            int trongLuongNhap = Integer.parseInt(request.getParameter("trongLuongNhap"));
            Integer trongLuongXuat = null;
            if (request.getParameter("trongLuongXuat") != null && !request.getParameter("trongLuongXuat").isEmpty()) {
                trongLuongXuat = Integer.parseInt(request.getParameter("trongLuongXuat"));
            }
            int idXuatXu = Integer.parseInt(request.getParameter("idXuatXu"));

            LocalDate ngayNhap = LocalDate.parse(request.getParameter("ngayNhap"));
            LocalDate ngayXuat = null;
            if (request.getParameter("ngayXuat") != null && !request.getParameter("ngayXuat").isEmpty()) {
                ngayXuat = LocalDate.parse(request.getParameter("ngayXuat"));
            }
            if (trongLuongNhap < 10) {
                response.sendRedirect("/heo?error=trongLuongNhap");
                return;
            }

            Heo heo = heoService.findById(id);
            heo.setMaHeo(maHeo);
            heo.setNgayNhap(ngayNhap);
            heo.setNgayXuat(ngayXuat);
            heo.setTrongLuongNhap(trongLuongNhap);
            heo.setTrongLuongXuat(trongLuongXuat);
            heo.setIdXuatXu(idXuatXu);

            heoService.update(heo);
            response.sendRedirect("/heo");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/heo?error=update");
        }
    }

    private void deleteHeo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        heoService.delete(id);
        response.sendRedirect("/heo");
    }

    private void searchHeo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maHeo = request.getParameter("maHeo");
        String tinhTrang = request.getParameter("tinhTrang");
        String xuatXu = request.getParameter("xuatXu");
        List<Heo> list = heoService.search(maHeo, tinhTrang, xuatXu);
        List<XuatXu> listXuatXu = xuatXuService.findAll();
        request.setAttribute("listHeo", list);
        request.setAttribute("listXuatXu", listXuatXu);
        request.getRequestDispatcher("/views/heo/list.jsp").forward(request, response);
    }

    private void showTopHeo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int limit = 10;
        if (request.getParameter("limit") != null && !request.getParameter("limit").isEmpty()) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        List<Heo> list = heoService.findTopHeoByTrongLuongXuat(limit);
        List<XuatXu> listXuatXu = xuatXuService.findAll();

        request.setAttribute("listHeo", list);
        request.setAttribute("listXuatXu", listXuatXu);
        request.setAttribute("topLimit", limit);
        request.getRequestDispatcher("/views/heo/list.jsp").forward(request, response);
    }
}

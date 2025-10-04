package com.example.baitap1.controller;

import com.example.baitap1.dto.PawnContractDto;
import com.example.baitap1.entity.PawnContract;
import com.example.baitap1.service.IPawnContractService;
import com.example.baitap1.service.PawnContractService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "PawnContractController", urlPatterns = "/pawn-contracts")
public class PawnContractController extends HttpServlet {

    private final IPawnContractService pawnContractService = new PawnContractService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                request.getRequestDispatcher("/views/pawn_contract/create.jsp").forward(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePawnContract(request, response);
                break;
            case "search":
                searchPawnContracts(request, response);
                break;
            default:
                listPawnContracts(request, response);
                break;
        }
    }

    // ==========================
    // POST: xử lý thêm, sửa
    // ==========================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                createPawnContract(request, response);
                break;
            case "edit":
                editPawnContract(request, response);
                break;
            default:
                response.sendRedirect("/pawn-contracts");
                break;
        }
    }

    // ==========================
    // Hiển thị danh sách
    // ==========================
    private void listPawnContracts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PawnContractDto> list = pawnContractService.findAllDto();
        request.setAttribute("pawnContracts", list);
        request.getRequestDispatcher("/views/pawn_contract/list.jsp").forward(request, response);
    }

    // ==========================
    // Xóa hợp đồng
    // ==========================
    private void deletePawnContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pawnContractService.delete(id);
        response.sendRedirect("/pawn-contracts");
    }

    // ==========================
    // Hiển thị form sửa
    // ==========================
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<PawnContractDto> contracts = pawnContractService.search("", "", ""); // Lấy tất cả, sau lọc theo ID
        PawnContractDto dto = contracts.stream().filter(c -> c.getPawnContractId() == id).findFirst().orElse(null);
        request.setAttribute("pawnContract", dto);
        request.getRequestDispatcher("/views/pawn_contract/edit.jsp").forward(request, response);
    }

    // ==========================
    // Tìm kiếm hợp đồng
    // ==========================
    private void searchPawnContracts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String employeeName = request.getParameter("employeeName");
        String productName = request.getParameter("productName");

        List<PawnContractDto> list = pawnContractService.search(
                customerName != null ? customerName : "",
                employeeName != null ? employeeName : "",
                productName != null ? productName : ""
        );
        request.setAttribute("pawnContracts", list);
        request.getRequestDispatcher("/views/pawn_contract/list.jsp").forward(request, response);
    }

    // ==========================
    // Thêm hợp đồng
    // ==========================
    private void createPawnContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PawnContract pc = new PawnContract();
            pc.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
            pc.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            pc.setProductId(Integer.parseInt(request.getParameter("productId")));
            pc.setPawnDate(LocalDate.parse(request.getParameter("pawnDate")));
            pc.setPawnPrice(new BigDecimal(request.getParameter("pawnPrice")));
            pc.setInterestRate(new BigDecimal(request.getParameter("interestRate")));
            pc.setDueDate(request.getParameter("dueDate").isEmpty() ? null : LocalDate.parse(request.getParameter("dueDate")));
            pc.setReturnDate(request.getParameter("returnDate").isEmpty() ? null : LocalDate.parse(request.getParameter("returnDate")));

            pawnContractService.save(pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/pawn-contracts");
    }

    // ==========================
    // Sửa hợp đồng
    // ==========================
    private void editPawnContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PawnContract pc = new PawnContract();
            pc.setPawnContractId(Integer.parseInt(request.getParameter("id")));
            pc.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
            pc.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            pc.setProductId(Integer.parseInt(request.getParameter("productId")));
            pc.setPawnDate(LocalDate.parse(request.getParameter("pawnDate")));
            pc.setPawnPrice(new BigDecimal(request.getParameter("pawnPrice")));
            pc.setInterestRate(new BigDecimal(request.getParameter("interestRate")));
            pc.setDueDate(request.getParameter("dueDate").isEmpty() ? null : LocalDate.parse(request.getParameter("dueDate")));
            pc.setReturnDate(request.getParameter("returnDate").isEmpty() ? null : LocalDate.parse(request.getParameter("returnDate")));

            pawnContractService.update(pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/pawn-contracts");
    }
}

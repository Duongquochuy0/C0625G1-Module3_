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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePawnContract(request, response);
                break;
            default:
                listPawnContracts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createPawnContract(request, response);
                break;
            case "edit":
                updatePawnContract(request, response);
                break;
            default:
                listPawnContracts(request, response);
                break;
        }
    }

    private void listPawnContracts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String employeeName = request.getParameter("employeeName");
        String productName = request.getParameter("productName");

        int page = 1;
        int recordsPerPage = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<PawnContractDto> contracts;
        int totalRecords;

        boolean hasSearch = (customerName != null && !customerName.trim().isEmpty()) ||
                (employeeName != null && !employeeName.trim().isEmpty()) ||
                (productName != null && !productName.trim().isEmpty());

        if (hasSearch) {
            contracts = pawnContractService.search(customerName, employeeName, productName);
            totalRecords = contracts.size();
        } else {
            totalRecords = pawnContractService.countPawnContract();
            int offset = (page - 1) * recordsPerPage;
            contracts = pawnContractService.findAll(offset, recordsPerPage);
        }

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        request.setAttribute("pawnContracts", contracts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("customerName", customerName);
        request.setAttribute("employeeName", employeeName);
        request.setAttribute("productName", productName);

        request.getRequestDispatcher("views/pawn_contract/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/pawn_contract/add.jsp").forward(request, response);
    }

    private void createPawnContract(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int customerId = parseIntParameter(request.getParameter("customerId"), "Mã khách hàng không hợp lệ");
            int employeeId = parseIntParameter(request.getParameter("employeeId"), "Mã nhân viên không hợp lệ");
            int productId = parseIntParameter(request.getParameter("productId"), "Mã sản phẩm không hợp lệ");
            BigDecimal pawnPrice = parseBigDecimalParameter(request.getParameter("pawnPrice"), "Giá cầm không hợp lệ");
            BigDecimal interestRate = parseBigDecimalParameter(request.getParameter("interestRate"), "Lãi suất không hợp lệ");
            LocalDate pawnDate = parseLocalDateParameter(request.getParameter("pawnDate"), "Ngày cầm không hợp lệ");
            LocalDate dueDate = parseLocalDateParameter(request.getParameter("dueDate"), "Ngày đến hạn không hợp lệ");

            if (pawnPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Giá cầm phải lớn hơn 0");
            }
            if (interestRate != null && interestRate.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Lãi suất không được âm");
            }

            PawnContract contract = new PawnContract();
            contract.setCustomerId(customerId);
            contract.setEmployeeId(employeeId);
            contract.setProductId(productId);
            contract.setPawnPrice(pawnPrice);
            contract.setInterestRate(interestRate);
            contract.setPawnDate(pawnDate);
            contract.setDueDate(dueDate);

            boolean success = pawnContractService.add(contract);
            if (!success) {
                throw new RuntimeException("Không thể thêm hợp đồng cầm");
            }

            response.sendRedirect(request.getContextPath() + "/pawn-contracts?message=created");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/pawn_contract/add.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi thêm hợp đồng: " + e.getMessage());
            request.getRequestDispatcher("views/pawn_contract/add.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = parseIntParameter(request.getParameter("id"), "Mã hợp đồng không hợp lệ");
            PawnContractDto contract = pawnContractService.findById(id);
            if (contract == null) {
                request.setAttribute("error", "Không tìm thấy hợp đồng với ID: " + id);
                request.getRequestDispatcher("views/pawn_contract/list.jsp").forward(request, response);
                return;
            }
            request.setAttribute("contract", contract);
            request.getRequestDispatcher("views/pawn_contract/update.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/pawn_contract/list.jsp").forward(request, response);
        }
    }

    private void updatePawnContract(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = parseIntParameter(request.getParameter("id"), "Mã hợp đồng không hợp lệ");
            int customerId = parseIntParameter(request.getParameter("customerId"), "Mã khách hàng không hợp lệ");
            int employeeId = parseIntParameter(request.getParameter("employeeId"), "Mã nhân viên không hợp lệ");
            int productId = parseIntParameter(request.getParameter("productId"), "Mã sản phẩm không hợp lệ");
            BigDecimal pawnPrice = parseBigDecimalParameter(request.getParameter("pawnPrice"), "Giá cầm không hợp lệ");
            BigDecimal interestRate = parseBigDecimalParameter(request.getParameter("interestRate"), "Lãi suất không hợp lệ");
            LocalDate pawnDate = parseLocalDateParameter(request.getParameter("pawnDate"), "Ngày cầm không hợp lệ");
            LocalDate dueDate = parseLocalDateParameter(request.getParameter("dueDate"), "Ngày đến hạn không hợp lệ");
            String returnDateStr = request.getParameter("returnDate");
            LocalDate returnDate = returnDateStr != null && !returnDateStr.trim().isEmpty() ?
                    LocalDate.parse(returnDateStr) : null;

            if (pawnPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Giá cầm phải lớn hơn 0");
            }
            if (interestRate != null && interestRate.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Lãi suất không được âm");
            }

            PawnContract contract = new PawnContract();
            contract.setPawnContractId(id);
            contract.setCustomerId(customerId);
            contract.setEmployeeId(employeeId);
            contract.setProductId(productId);
            contract.setPawnPrice(pawnPrice);
            contract.setInterestRate(interestRate);
            contract.setPawnDate(pawnDate);
            contract.setDueDate(dueDate);
            contract.setReturnDate(returnDate);

            boolean success = pawnContractService.update(contract);
            if (!success) {
                throw new RuntimeException("Không thể cập nhật hợp đồng cầm");
            }

            response.sendRedirect(request.getContextPath() + "/pawn-contracts?message=updated");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/pawn_contract/update.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi cập nhật hợp đồng: " + e.getMessage());
            request.getRequestDispatcher("views/pawn_contract/update.jsp").forward(request, response);
        }
    }

    private void deletePawnContract(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = parseIntParameter(request.getParameter("id"), "Mã hợp đồng không hợp lệ");
            boolean success = pawnContractService.delete(id);
            if (!success) {
                throw new RuntimeException("Không thể xóa hợp đồng cầm");
            }
            response.sendRedirect(request.getContextPath() + "/pawn-contracts?message=deleted");
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/pawn-contracts?error=" + e.getMessage());
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/pawn-contracts?error=" + e.getMessage());
        }
    }

    private int parseIntParameter(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            int result = Integer.parseInt(value);
            if (result <= 0) {
                throw new IllegalArgumentException(errorMessage);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private BigDecimal parseBigDecimalParameter(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private LocalDate parseLocalDateParameter(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
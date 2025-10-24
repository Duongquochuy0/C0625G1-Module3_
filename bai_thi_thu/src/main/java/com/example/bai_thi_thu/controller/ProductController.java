package com.example.bai_thi_thu.controller;

import com.example.bai_thi_thu.dto.ProductDto;
import com.example.bai_thi_thu.entity.Product;
import com.example.bai_thi_thu.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/product/list.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.delete(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("product");
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String categoryId = request.getParameter("categoryId");
        if (categoryId != null && categoryId.isEmpty()) {
            categoryId = null;
        }
        List<ProductDto> products = productService.search(name == null ? "" : name, categoryId);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/product/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/product/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                addProduct(request, response);
                break;
            default:
                response.sendRedirect("product");
                break;
        }
    }
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        double price = 0;
        int categoryId = 0;
        try {
            price = Double.parseDouble(request.getParameter("price"));
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setUnit(unit);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        productService.add(product);
        response.sendRedirect("product");
    }
}

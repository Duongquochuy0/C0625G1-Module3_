package com.example.quan_ly_san_phams.controller;

import com.example.quan_ly_san_phams.dto.ProductDto;
import com.example.quan_ly_san_phams.enity.Category;
import com.example.quan_ly_san_phams.enity.Product;
import com.example.quan_ly_san_phams.service.CategoryService;
import com.example.quan_ly_san_phams.service.IProductDtoService;
import com.example.quan_ly_san_phams.service.ICategoryService;
import com.example.quan_ly_san_phams.service.ProductDtoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private IProductDtoService productService = new ProductDtoService();
    private ICategoryService categoryService = new CategoryService();

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
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
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
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("name");
        String searchCategory = request.getParameter("categoryId");
        int page = 1;
        int recordsPerPage = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        List<ProductDto> products;
        int totalRecords;
        if ((searchName != null && !searchName.isEmpty()) || (searchCategory != null && !searchCategory.isEmpty())) {
            products = productService.search(searchName, searchCategory);
            totalRecords = products.size();
        } else {
            totalRecords = productService.countProducts();
            int offset = (page - 1) * recordsPerPage;
            products = productService.findAll(offset, recordsPerPage);
        }
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        List<Category> categories = categoryService.findAll();
        int startIndex = (page - 1) * recordsPerPage;
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("selectedCategory", searchCategory);
        request.setAttribute("searchName", searchName);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("startIndex", startIndex);

        request.getRequestDispatcher("views/product/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("views/product/add.jsp").forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        Product product = new Product(name, price, quantity, description, categoryId);
        productService.add(product);

        response.sendRedirect("/products");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findByIdProduct(id);
        List<Category> categories = categoryService.findAll();

        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("views/product/update.jsp").forward(request, response);
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            Product product = new Product(id, name, price, quantity, description, categoryId);

            boolean updated = productService.update(product);
            if (updated) {
                System.out.println("Update thành công: " + id);
            } else {
                System.out.println("Update thất bại: " + id);
            }

            response.sendRedirect("/products");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/products");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/products");
    }
}

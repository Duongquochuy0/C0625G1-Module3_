package com.example.quan_ly_san_pham.controller;

import com.example.quan_ly_san_pham.enity.Product;
import com.example.quan_ly_san_pham.service.IProductService;
import com.example.quan_ly_san_pham.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/products")
public class ProductController extends HttpServlet {

    private final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

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
            case "view":
                viewProduct(request, response);
                break;
            default: // list
                listProducts(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
        }
    }

    /** Hiển thị danh sách */
    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/product/list.jsp").forward(req, resp);
    }

    /** Form thêm mới */
    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/product/create.jsp").forward(req, resp);
    }

    /** Lưu sản phẩm mới */
    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = (int) (Math.random() * 10000);
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String desc = req.getParameter("description");
        String manu = req.getParameter("manufacturer");
        productService.save(new Product(id, name, price, desc, manu));
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    /** Form sửa */
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("product", productService.findById(id));
        req.getRequestDispatcher("/views/product/edit.jsp").forward(req, resp);
    }

    /** Cập nhật */
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product p = new Product(
                id,
                req.getParameter("name"),
                Double.parseDouble(req.getParameter("price")),
                req.getParameter("description"),
                req.getParameter("manufacturer"));
        productService.update(id, p);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.remove(id);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    /** Xem chi tiết */
    private void viewProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("product", productService.findById(id));
        req.getRequestDispatcher("/views/product/view.jsp").forward(req, resp);
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("products", productService.findByName(name));
        req.getRequestDispatcher("/views/product/search.jsp").forward(req, resp);
    }
}

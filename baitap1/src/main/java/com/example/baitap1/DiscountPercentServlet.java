package com.example.baitap1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name ="DiscountPercentServlet",value = "/discount")
public class DiscountPercentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------Get Run-----------");
        RequestDispatcher requestDispatcher =req.getRequestDispatcher("form-tinh-chiet-khau.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------Post Run-----------");
        int num1 = Integer.parseInt(req.getParameter("n1"));
        int num2 = Integer.parseInt(req.getParameter("n2"));
        int discount = num1*num2*1/100;
        req.setAttribute("discount",discount);
        req.setAttribute("num1",num1);
        req.setAttribute("num2",num2);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("form-tinh-chiet-khau.jsp");
        requestDispatcher.forward(req,resp);
    }
}


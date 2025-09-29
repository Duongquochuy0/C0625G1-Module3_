package com.example.may_tinh.servlet;

import com.example.may_tinh.model.Calculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculate")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            float firstOperand = Float.parseFloat(req.getParameter("first-operand"));
            float secondOperand = Float.parseFloat(req.getParameter("second-operand"));
            char operator = req.getParameter("operator").charAt(0);

            float result = Calculator.calculate(firstOperand, secondOperand, operator);
            req.setAttribute("firstOperand", firstOperand);
            req.setAttribute("secondOperand", secondOperand);
            req.setAttribute("operator", operator);
            req.setAttribute("result", result);

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}

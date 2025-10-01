package com.example.user_managers.controller;

import com.example.user_managers.entity.User;
import com.example.user_managers.service.IUserService;
import com.example.user_managers.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "list":
                String country = req.getParameter("country");
                List<User> userList;
                if (country != null && !country.isEmpty()) {
                    userList = ((UserService) userService).findByCountry(country);
                    req.setAttribute("searchCountry", country);
                } else {
                    userList = userService.findAll();
                }
                req.setAttribute("userList", userList);
                req.getRequestDispatcher("views/user/list.jsp").forward(req, resp);
                break;

            case "add":
                req.getRequestDispatcher("views/user/add.jsp").forward(req, resp);
                break;

            case "update":
                int idUpdate = Integer.parseInt(req.getParameter("id"));
                User user = userService.findById(idUpdate);
                req.setAttribute("user", user);
                req.getRequestDispatcher("views/user/update.jsp").forward(req, resp);
                break;

            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                boolean deleted = userService.delete(deleteId);
                resp.sendRedirect("/users?action=list");
                break;

            default:
                resp.sendRedirect("/users?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add":
                User newUser = new User(
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("country")
                );
                userService.add(newUser);
                resp.sendRedirect("/users?action=list");
                break;

            case "update":
                int id = Integer.parseInt(req.getParameter("id"));
                User updatedUser = new User(
                        id,
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("country")
                );
                userService.update(updatedUser);
                resp.sendRedirect("/users?action=list");
                break;

            default:
                resp.sendRedirect("/users?action=list");
        }
    }
}

package com.example.quan_ly_sinh_vien.controller;


import com.example.quan_ly_sinh_vien.entity.Department;
import com.example.quan_ly_sinh_vien.entity.Employee;
import com.example.quan_ly_sinh_vien.service.DepartmentService;
import com.example.quan_ly_sinh_vien.service.EmployeeService;
import com.example.quan_ly_sinh_vien.service.IDepartmentService;
import com.example.quan_ly_sinh_vien.service.IEmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "EmployeeController", urlPatterns = "/employee")
public class EmployeeController extends HttpServlet {
    private IEmployeeService employeeService = new EmployeeService();
    private IDepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
                delete(request, response);
                break;
            default:
                listEmployee(request, response);
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
                createEmployee(request, response);
                break;
            case "edit":
                updateEmployee(request, response);
                break;
            case "search":
                search(request, response);
                break;
            default:
                listEmployee(request, response);
                break;
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> listEmployee = employeeService.findAll();
       List<Department> listDepartment = departmentService.findAll();
        request.setAttribute("listEmployee", listEmployee);
       request.setAttribute("listDepartment", listDepartment);
        request.getRequestDispatcher("/views/employee/list.jsp").forward(request, response);
    }


    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String departmentId = request.getParameter("departmentId");
        List<Employee> list = employeeService.search(code, name, departmentId);
        List<Department> listDepartment = departmentService.findAll();
        request.setAttribute("listEmployee", list);
        request.setAttribute("listDepartment", listDepartment);
        request.getRequestDispatcher("/views/employee/list.jsp").forward(request, response);
    }


    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.delete(id);
        response.sendRedirect("/employee");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.findById(id);
        List<Department> listDepartment = departmentService.findAll();
        request.setAttribute("employee", employee);
        request.setAttribute("listDepartment", listDepartment);
        request.getRequestDispatcher("/views/employee/update.jsp").forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            double salary = Double.parseDouble(request.getParameter("salary"));
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));

            Employee employee = employeeService.findById(id);
            employee.setCode(code);
            employee.setName(name);
            employee.setGender(gender);
            employee.setSalary(salary);
            employee.setDepartmentId(departmentId);

            employeeService.update(employee);
            response.sendRedirect("/employee");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/employee?error=update");
        }
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Department> listDepartment = departmentService.findAll();
        request.setAttribute("listDepartment", listDepartment);
        request.getRequestDispatcher("/views/employee/create.jsp").forward(request, response);
    }
    private void createEmployee(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            double salary = Double.parseDouble(request.getParameter("salary"));
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));

            Employee employee = new Employee();
            employee.setCode(code);
            employee.setName(name);
            employee.setGender(gender);
            employee.setSalary(salary);
            employee.setDepartmentId(departmentId);

            employeeService.add(employee);  // gọi repository add

            response.sendRedirect("/employee");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/employee?action=create&error=add");
        }
    }
}



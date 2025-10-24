<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý heo</title>
    <link rel="stylesheet" href="../../bootstrap520/css/bootstrap.min.css">
    <style>
        th {
            background-color: yellow;
            text-align: center;
        }
        td, th {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body class="container mt-4">
<h2 class="text-center mb-4">Quản lý nhân viên</h2>
<form action="/employee" method="post" class="row mb-3 align-items-end">
    <input type="hidden" name="action" value="search">

    <div class="col-md-3">
        <input type="text" name="code" class="form-control" placeholder="Tìm theo Mã nhân viên">
    </div>
    <div class="col-md-3">
        <input type="text" name="name" class="form-control" placeholder="Tìm theo tên nhân viên">
    </div>
    <div class="col-md-3">
        <select name="departmentId" class="form-select">
            <option value="">Chọn phòng ban</option>
            <c:forEach var="depar" items="${listDepartment}">
                <option value="${depar.id}">${depar.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-3 d-flex">
        <button type="submit" class="btn btn-dark me-2 flex-fill">Tìm kiếm</button>
        <!-- Nút thêm mới -->
        <a href="/employee?action=create" class="btn btn-success flex-fill">Thêm mới</a>
    </div>
</form>

<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã số nhân viên</th>
        <th>Tên nhân viên</th>
        <th>Giới tính</th>
        <th>Lương</th>
        <th>Phòng ban làm việc</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${listEmployee}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${employee.code}</td>
            <td>${employee.name}</td>
            <td>${employee.gender}</td>
            <td><fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="₫"/></td>
            <td>${employee.departmentName}</td>
            <td>
                <a href="/employee?action=edit&id=${employee.id}" class="btn btn-info btn-sm">Xem</a>
            </td>
            <td>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                        data-bs-target="#deleteModal${employee.id}">Xóa</button>
            </td>
        </tr>
        <div class="modal fade" id="deleteModal${employee.id}" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title">Xác nhận xóa</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc muốn xóa nhân viên <strong>${employee.code}</strong> không?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <a href="/employee?action=delete&id=${employee.id}" class="btn btn-danger">Xóa</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </tbody>
</table>

<script src="../../bootstrap520/js/bootstrap.bundle.min.js"></script>
</body>
</html>

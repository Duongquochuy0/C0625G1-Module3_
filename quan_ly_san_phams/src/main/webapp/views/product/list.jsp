<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-3">Danh sách sản phẩm</h2>

    <form method="get" action="/products" class="row g-2 mb-3">
        <input type="hidden" name="action" value="search"/>
        <div class="col-md-4">
            <input type="text" name="name" class="form-control" placeholder="Nhập tên sản phẩm"
                   value="${param.name}"/>
        </div>
        <div class="col-md-3">
            <select name="categoryId" class="form-control">
                <option value="">-- Tất cả danh mục --</option>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}" ${param.categoryId == c.id ? 'selected' : ''}>
                            ${c.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
        </div>
        <div class="col-md-3">
            <a href="/products?action=create" class="btn btn-success w-100">+ Thêm sản phẩm</a>
        </div>
    </form>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>STT</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Danh mục</th>
            <th>#</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}" varStatus="status">
            <tr>
                <td>${status.index +1}</td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.quantity}</td>
                <td>${p.description}</td>
                <td>${p.categoryName}</td>
                <td>
                    <a href="/products?action=edit&id=${p.id}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="/products?action=delete&id=${p.id}"
                       onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?');"
                       class="btn btn-danger btn-sm">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

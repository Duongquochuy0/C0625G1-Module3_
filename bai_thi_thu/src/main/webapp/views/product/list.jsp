<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Danh sách hàng hóa</title>
    <link href="${pageContext.request.contextPath}/bootstrap520/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<h2>Danh sách hàng hóa</h2>
<a href="product?action=add" class="btn btn-success mb-3"> Thêm mới</a>
<form action="product" method="get" class="mb-3">
    <input type="hidden" name="action" value="search"/>
    <div class="row g-2 align-items-end">
        <div class="col-md-4">
            <label for="name" class="form-label">Tên hàng hóa:</label>
            <input type="text" name="name" id="name" class="form-control" value="${param.name}" placeholder="Nhập tên sản phẩm"/>
        </div>
        <div class="col-md-4">
            <label for="categoryId" class="form-label">Loại hàng hóa:</label>
            <select name="categoryId" id="categoryId" class="form-select">
                <option value="">--Tất cả--</option>
                <option value="1" ${param.categoryId=='1'?'selected':''}>Rau</option>
                <option value="2" ${param.categoryId=='2'?'selected':''}>Củ</option>
                <option value="3" ${param.categoryId=='3'?'selected':''}>Quả</option>
                <option value="4" ${param.categoryId=='4'?'selected':''}>Hoa</option>
            </select>
        </div>
        <div class="col-md-4">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </div>
    </div>
</form>
<table class="table table-bordered text-center">
    <thead class="table-dark">
    <tr>
        <th>Mã hàng</th>
        <th>Tên hàng</th>
        <th>Đơn vị</th>
        <th>Giá (VNĐ)</th>
        <th>Loại</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.code}</td>
            <td>${p.name}</td>
            <td>${p.unit}</td>
            <td><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/></td>
            <td>${p.categoryName}</td>
            <td>
                <a href="product?action=delete&id=${p.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Xóa sản phẩm này?')">🗑️ Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

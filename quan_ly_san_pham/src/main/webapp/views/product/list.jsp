<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
<h1>Danh sách sản phẩm</h1>

<a href="${pageContext.request.contextPath}/products?action=create">Thêm sản phẩm mới</a>

<form action="${pageContext.request.contextPath}/products" method="post">
    <input type="hidden" name="action" value="search"/>
    <input type="text" name="name" placeholder="Tìm theo tên"/>
    <button type="submit">Tìm kiếm</button>
</form>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Nhà sản xuất</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td>${p.manufacturer}</td>
            <td>
                <a href="${pageContext.request.contextPath}/products?action=view&id=${p.id}">Xem</a> |
                <a href="${pageContext.request.contextPath}/products?action=edit&id=${p.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/products?action=delete&id=${p.id}"
                   onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

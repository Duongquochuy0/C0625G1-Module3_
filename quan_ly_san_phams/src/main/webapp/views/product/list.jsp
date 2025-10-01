<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
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
    <form class="row mb-3" method="get" action="/products">
        <div class="col">
            <input type="text" name="name" value="${searchName}" class="form-control" placeholder="Tìm theo tên">
        </div>
        <div class="col">
            <select name="categoryId" class="form-select">
                <option value="">Tất cả danh mục</option>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}"
                            <c:if test="${c.id == selectedCategory}">selected</c:if>>
                            ${c.name}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/products" class="btn btn-secondary">Reset</a>
        </div>
    </form>

    <a href="/products?action=create" class="btn btn-success mb-3">Thêm sản phẩm</a>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Danh mục</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}" varStatus="status">
            <tr>
                <td>${startIndex + status.index + 1}</td>
                <td>${p.name}</td>
                <td><fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${p.price}" type="currency" groupingUsed="true"/></td>
                <td>${p.quantity}</td>
                <td>${p.description}</td>
                <td>${p.categoryName}</td>
                <td>
                    <a href="/products?action=edit&id=${p.id}" class="btn btn-primary btn-sm">Sửa</a>
                    <a href="/products?action=delete&id=${p.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa không?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="/products?page=${i}&name=${searchName}&categoryId=${selectedCategory}">${i}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>
</body>
</html>

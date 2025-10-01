<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-3">Cập nhật sản phẩm</h2>

    <form action="/products?action=edit" method="post">
        <input type="hidden" name="id" value="${product.id}"/>

        <div class="mb-3">
            <label class="form-label">Tên sản phẩm</label>
            <input type="text" name="name" class="form-control" value="${product.name}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá</label>
            <input type="number" step="0.01" name="price" class="form-control" value="${product.price}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Số lượng</label>
            <input type="number" name="quantity" class="form-control" value="${product.quantity}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea name="description" class="form-control" rows="3">${product.description}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Danh mục</label>
            <select name="categoryId" class="form-select" required>
                <c:forEach var="c" items="${categories}">
                    <option value="${c.id}" <c:if test="${c.id == product.categoryId}">selected</c:if>>
                            ${c.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="/products" class="btn btn-secondary">Hủy</a>
    </form>
</div>
</body>
</html>

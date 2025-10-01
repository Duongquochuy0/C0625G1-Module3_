<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thêm sản phẩm</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<h2 class="mb-4">Thêm sản phẩm mới</h2>

<form action="/products?action=create" method="post">
  <div class="mb-3">
    <label class="form-label">Tên sản phẩm</label>
    <input type="text" class="form-control" name="name" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Giá</label>
    <input type="number" class="form-control" name="price" step="0.01" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Số lượng</label>
    <input type="number" class="form-control" name="quantity" required>
  </div>

  <div class="mb-3">
    <label class="form-label">Mô tả</label>
    <textarea class="form-control" name="description"></textarea>
  </div>

  <div class="mb-3">
    <label class="form-label">Danh mục</label>
    <select class="form-select" name="categoryId" required>
      <c:forEach var="category" items="${categories}">
        <option value="${category.id}">${category.name}</option>
      </c:forEach>
    </select>
  </div>

  <button type="submit" class="btn btn-primary">Thêm mới</button>
  <a href="/products" class="btn btn-secondary">Hủy</a>
</form>
</body>
</html>

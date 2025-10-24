<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${product.id > 0 ? "Chỉnh sửa" : "Thêm mới"} sản phẩm</title>
  <style>
    body {
      font-family: "Segoe UI", Arial, sans-serif;
      background-color: #f8f9fa;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      background-color: white;
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.1);
      width: 400px;
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 25px;
    }

    label {
      display: block;
      font-weight: 600;
      margin-bottom: 6px;
      color: #555;
    }

    input[type="text"], input[type="number"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 15px;
      margin-bottom: 20px;
      box-sizing: border-box;
    }

    input:focus {
      border-color: #007bff;
      outline: none;
      box-shadow: 0 0 4px rgba(0,123,255,0.4);
    }

    .btn {
      display: inline-block;
      padding: 10px 16px;
      font-size: 15px;
      border: none;
      border-radius: 6px;
      cursor: pointer;
      text-decoration: none;
    }

    .btn-save {
      background-color: #007bff;
      color: white;
      margin-right: 8px;
    }

    .btn-save:hover {
      background-color: #0056b3;
    }

    .btn-back {
      background-color: #6c757d;
      color: white;
    }

    .btn-back:hover {
      background-color: #5a6268;
    }

    .btn-container {
      text-align: center;
    }
  </style>
</head>
<body>

<div class="container">
  <h2>${product.id > 0 ? "✏️ Chỉnh sửa sản phẩm" : "➕ Thêm mới sản phẩm"}</h2>

  <form:form method="post" action="${pageContext.request.contextPath}/products/save" modelAttribute="product">
    <form:hidden path="id"/>

    <label for="name">Tên sản phẩm:</label>
    <form:input path="name" id="name" />

    <label for="price">Giá:</label>
    <form:input path="price" id="price" type="number" step="0.01"/>

    <div class="btn-container">
      <button type="submit" class="btn btn-save">💾 Lưu</button>
      <a href="${pageContext.request.contextPath}/products" class="btn btn-back">↩ Quay lại</a>
    </div>
  </form:form>
</div>

</body>
</html>

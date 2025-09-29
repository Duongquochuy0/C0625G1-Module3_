<%--
  Created by IntelliJ IDEA.
  User: WELCOME
  Date: 25/09/2025
  Time: 1:51 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/discount" method="post">
    <h2>Tính chiết khấu</h2>
    <p>Mô tả của sản phẩm:</p>
    <input type="text" name="description">
    <p>Giá niêm yết của sản phẩm:</p>
    <input type="number" name="n1" value="${num1}">
    <p>Tỷ lệ chiết khấu (%):</p>
    <input type="number" name="n2" value="${num2}">
    <button>Discount</button>
</form>
<h3>Kết quả:${discount}</h3>
</body>
</html>

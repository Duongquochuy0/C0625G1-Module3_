<%-- Created by IntelliJ IDEA.
     User: Home
     Date: 9/26/2025
     Time: 11:00 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Danh sách khách hàng</title>
  <c:import url="../layout/libary.jsp"/>
</head>
<body>

<div class="container my-4">
  <h1 class="text-center mb-4 fw-bold">Danh sách khách hàng</h1>

  <table class="table table-striped table-hover table-bordered align-middle shadow rounded-3">
    <thead class="table-primary text-dark">
    <tr>
      <th>Tên</th>
      <th>Ngày sinh</th>
      <th>Địa chỉ</th>
      <th>Ảnh</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="khachHang" items="${khachHangList}">
      <tr>
        <td>${khachHang.ten}</td>
        <td>${khachHang.ngaySinh}</td>
        <td>${khachHang.diaChi}</td>
        <td>
          <img src="<c:url value='/${khachHang.anh}'/>"
               alt="Ảnh khách hàng"
               class="img-thumbnail"
               style="width:120px;height:auto;">
        </td>

      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>

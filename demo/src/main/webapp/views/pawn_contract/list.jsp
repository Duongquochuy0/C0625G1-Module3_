<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Danh sách hợp đồng cầm đồ</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-5.3.8-dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
  <h2>📄 Danh sách hợp đồng cầm đồ</h2>

  <c:choose>
    <c:when test="${not empty contracts}">
      <table class="table table-bordered mt-3">
        <thead>
        <tr>
          <th>ID</th>
          <th>Khách hàng</th>
          <th>Nhân viên</th>
          <th>Sản phẩm</th>
          <th>Ngày cầm</th>
          <th>Giá trị cầm</th>
          <th>Lãi suất</th>
          <th>Ngày đáo hạn</th>
          <th>Ngày trả</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${contracts}">
          <tr>
            <td>${c.pawnContractId}</td>
            <td>${c.customerName}</td>
            <td>${c.employeeName}</td>
            <td>${c.productName}</td>
            <td>${c.pawnDate}</td>
            <td>${c.pawnPrice}</td>
            <td>${c.interestRate}</td>
            <td>${c.dueDate}</td>
            <td>${c.returnDate}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <p>📭 Chưa có hợp đồng nào!</p>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>

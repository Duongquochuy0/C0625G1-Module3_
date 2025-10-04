<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Thêm hợp đồng cầm đồ</title>
  <link href="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>+ Thêm hợp đồng cầm đồ</h2>

<form action="${pageContext.request.contextPath}/pawn-contracts" method="post">
  <input type="hidden" name="action" value="create"/>

  <div class="mb-3">
    <label for="customerId" class="form-label">Khách hàng</label>
    <input type="number" name="customerId" class="form-control" id="customerId" required>
  </div>

  <div class="mb-3">
    <label for="employeeId" class="form-label">Nhân viên</label>
    <input type="number" name="employeeId" class="form-control" id="employeeId" required>
  </div>

  <div class="mb-3">
    <label for="productId" class="form-label">Sản phẩm</label>
    <input type="number" name="productId" class="form-control" id="productId" required>
  </div>

  <div class="mb-3">
    <label for="pawnDate" class="form-label">Ngày cầm</label>
    <input type="date" name="pawnDate" class="form-control" id="pawnDate" required>
  </div>

  <div class="mb-3">
    <label for="pawnPrice" class="form-label">Giá trị cầm</label>
    <input type="number" name="pawnPrice" class="form-control" id="pawnPrice" required>
  </div>

  <div class="mb-3">
    <label for="interestRate" class="form-label">Lãi suất (%)</label>
    <input type="number" step="0.01" name="interestRate" class="form-control" id="interestRate" required>
  </div>

  <div class="mb-3">
    <label for="dueDate" class="form-label">Ngày đáo hạn</label>
    <input type="date" name="dueDate" class="form-control" id="dueDate">
  </div>

  <div class="mb-3">
    <label for="returnDate" class="form-label">Ngày trả thực tế</label>
    <input type="date" name="returnDate" class="form-control" id="returnDate">
  </div>

  <button type="submit" class="btn btn-success">Lưu hợp đồng</button>
  <a href="${pageContext.request.contextPath}/pawn-contracts" class="btn btn-secondary">Hủy</a>
</form>

<script src="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

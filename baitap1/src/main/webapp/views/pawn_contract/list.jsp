<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách hợp đồng cầm đồ</title>
    <link href="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<h2 class="mb-4 text-center">📄 Danh sách hợp đồng cầm đồ</h2>

<p class="text-muted">🔎 Số hợp đồng lấy được: <strong>${pawnContracts.size()}</strong></p>

<!-- Form tìm kiếm -->
<form action="${pageContext.request.contextPath}/pawn-contracts" method="get" class="row g-3 mb-4">
    <div class="col-md-4">
        <input type="text" name="customerName" value="${customerName}" class="form-control" placeholder="Tên khách hàng">
    </div>
    <div class="col-md-4">
        <input type="text" name="employeeName" value="${employeeName}" class="form-control" placeholder="Tên nhân viên">
    </div>
    <div class="col-md-3">
        <input type="text" name="productName" value="${productName}" class="form-control" placeholder="Tên sản phẩm">
    </div>
    <div class="col-md-1">
        <button type="submit" class="btn btn-primary w-100">🔍 Tìm</button>
    </div>
</form>

<c:choose>
    <c:when test="${not empty pawnContracts}">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Khách hàng</th>
                <th>Nhân viên</th>
                <th>Sản phẩm</th>
                <th>Giá trị cầm</th>
                <th>Lãi suất</th>
                <th>Ngày cầm</th>
                <th>Ngày đáo hạn</th>
                <th>Ngày chuộc</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${pawnContracts}">
                <tr>
                    <td>${c.pawnContractId}</td>
                    <td>${c.customerName}</td>
                    <td>${c.employeeName}</td>
                    <td>${c.productName}</td>
                    <td><fmt:formatNumber value="${c.pawnPrice}" type="currency" currencySymbol="₫"/></td>
                    <td>${c.interestRate != null ? c.interestRate : 'N/A'}%</td>
                    <td><fmt:formatDate value="${c.pawnDate}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${c.dueDate}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${c.returnDate}" pattern="dd/MM/yyyy"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="text-center mt-5">
            <h4>📭 Chưa có hợp đồng cầm đồ nào!</h4>
            <a href="${pageContext.request.contextPath}/pawn-contracts?action=create" class="btn btn-primary mt-3">Thêm hợp đồng mới</a>
        </div>
    </c:otherwise>
</c:choose>

<script src="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

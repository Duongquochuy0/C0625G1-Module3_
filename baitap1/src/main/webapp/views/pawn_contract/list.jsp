<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách hợp đồng cầm đồ</title>
    <link href="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2 class="mb-3">📄 Danh sách hợp đồng cầm đồ</h2>

<p>🔎 Số hợp đồng lấy được: <b>${fn:length(pawnContracts)}</b></p>

<c:if test="${not empty param.error}">
    <div class="alert alert-danger">❌ Lỗi: ${param.error}</div>
</c:if>
<c:if test="${not empty param.success}">
    <div class="alert alert-success">✅ ${param.success}</div>
</c:if>

<div class="mb-3">
    <a href="${pageContext.request.contextPath}/pawn-contracts?action=create" class="btn btn-success">+ Thêm hợp đồng</a>
</div>

<form action="${pageContext.request.contextPath}/pawn-contracts" method="get" class="row g-2 mb-3">
    <input type="hidden" name="action" value="search"/>
    <div class="col">
        <input type="text" name="customerName" value="${param.customerName}" class="form-control" placeholder="Khách hàng">
    </div>
    <div class="col">
        <input type="text" name="employeeName" value="${param.employeeName}" class="form-control" placeholder="Nhân viên">
    </div>
    <div class="col">
        <input type="text" name="productName" value="${param.productName}" class="form-control" placeholder="Sản phẩm">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Tìm</button>
    </div>
</form>

<table class="table table-bordered table-striped table-hover align-middle">
    <thead class="table-dark text-center">
    <tr>
        <th>ID</th>
        <th>Khách hàng</th>
        <th>Nhân viên</th>
        <th>Sản phẩm</th>
        <th>Giá trị cầm</th>
        <th>Lãi suất</th>
        <th>Ngày cầm</th>
        <th>Ngày đáo hạn</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
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
            <td>${c.interestRate}%</td>
            <td>${c.pawnDate}</td>
            <td><c:out value="${c.dueDate != null ? c.dueDate : '-'}"/></td>
            <td class="text-center">
                <c:choose>
                    <c:when test="${c.returnDate == null}">
                        <span class="badge bg-success">Đang cầm</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge bg-secondary">Đã tất toán</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="text-center">
                <a href="${pageContext.request.contextPath}/pawn-contracts?action=edit&id=${c.pawnContractId}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="${pageContext.request.contextPath}/pawn-contracts?action=delete&id=${c.pawnContractId}"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn có chắc muốn xóa hợp đồng này không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty pawnContracts}">
    <div class="alert alert-info">📭 Chưa có hợp đồng nào!</div>
</c:if>

<script src="${pageContext.request.contextPath}/bootstrap-5.3.8-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

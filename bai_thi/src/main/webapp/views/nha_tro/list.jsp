<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Nhà trọ</title>
    <link rel="stylesheet" href="../../bootstrap520/css/bootstrap.min.css">
</head>
<body class="container mt-4">
<h2 class="text-center mb-4">Danh sách Nhà trọ</h2>

<form action="/nhaTro" method="post" class="row g-3 mb-4">
    <input type="hidden" name="action" value="search">
    <div class="col-md-3">
        <input type="text" name="maNhaTro" class="form-control" placeholder="Mã nhà trọ">
    </div>
    <div class="col-md-3">
        <input type="text" name="ten" class="form-control" placeholder="Tên nhà trọ">
    </div>
    <div class="col-md-3">
        <input type="text" name="soDienThoai" class="form-control" placeholder="Số điện thoại">
    </div>
    <div class="col-md-3 d-flex">
        <button type="submit" class="btn btn-primary me-2">Tìm kiếm</button>
        <a href="/nhaTro?action=create" class="btn btn-success">+ Thêm mới</a>
    </div>
</form>

<table class="table table-bordered table-striped align-middle">
    <thead class="table-dark text-center">
    <tr>
        <th>STT</th>
        <th>Mã nhà trọ</th>
        <th>Tên người thuê trọ</th>
        <th>Số điện thoại</th>
        <th>Ngày bắt đầu thuê</th>
        <th>Hình thức thanh toán</th>
        <th>Ghi chú</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="nt" items="${listNhaTro}" varStatus="status">
        <tr>
            <td class="text-center">${status.index + 1}</td>
            <td>${nt.maNhaTro}</td>
            <td>${nt.ten}</td>
            <td>${nt.soDienThoai}</td>
            <td class="text-center">
                <c:out value="${nt.ngayBatDauThue.dayOfMonth}" />/
                <c:out value="${nt.ngayBatDauThue.monthValue}" />/
                <c:out value="${nt.ngayBatDauThue.year}" />
            </td>
            <td>${nt.hinhThucThanhToan}</td>
            <td>${nt.ghiChu}</td>
            <td class="text-center">
                <button type="button"
                        class="btn btn-danger btn-sm"
                        data-bs-toggle="modal"
                        data-bs-target="#deleteModal"
                        data-id="${nt.id}"
                        data-ten="${nt.ten}">
                    Xóa
                </button>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${empty listNhaTro}">
        <tr>
            <td colspan="8" class="text-center text-muted">Không có dữ liệu</td>
        </tr>
    </c:if>
    </tbody>
</table>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Bạn có chắc muốn xóa <strong id="tenNhaTroXoa"></strong> không?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <a href="#" id="btnDeleteConfirm" class="btn btn-danger">Xóa</a>
            </div>
        </div>
    </div>
</div>

<script src="../../bootstrap520/js/bootstrap.bundle.min.js"></script>
<script>
    const deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const id = button.getAttribute('data-id');
        const ten = button.getAttribute('data-ten');

        document.getElementById('tenNhaTroXoa').textContent = ten;
        document.getElementById('btnDeleteConfirm').href = '/nhaTro?action=delete&id=' + id;
    });
</script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý heo</title>
  <link rel="stylesheet" href="../../bootstrap520/css/bootstrap.min.css">
  <style>
    th {
      background-color: yellow;
      text-align: center;
    }
    td, th {
      text-align: center;
      vertical-align: middle;
    }
  </style>
</head>
<body class="container mt-4">
<h2 class="text-center mb-4">Quản lý heo</h2>
<form action="/heo" method="post" class="row mb-3 align-items-end">
  <input type="hidden" name="action" value="search">

  <div class="col-md-3">
    <select name="tinhTrang" class="form-select">
      <option value="">Tình trạng heo</option>
      <option value="Chưa bán">Chưa bán</option>
      <option value="Đã bán">Đã bán</option>
    </select>
  </div>

  <div class="col-md-3">
    <input type="text" name="maHeo" class="form-control" placeholder="Tìm theo Mã heo">
  </div>

  <div class="col-md-3">
    <select name="xuatXu" class="form-select">
      <option value="">Chọn xuất xứ</option>
      <c:forEach var="xx" items="${listXuatXu}">
        <option value="${xx.ten}">${xx.ten}</option>
      </c:forEach>
    </select>
  </div>
  <div class="col-md-3 d-flex">
    <button type="submit" class="btn btn-dark me-2 flex-fill">Tìm kiếm</button>
    <select id="limitSelect" class="form-select me-2" style="max-width: 120px;">
      <option value="10">Top 10</option>
      <option value="20">Top 20</option>
    </select>
    <button type="button" class="btn btn-success" onclick="showTopHeo()">Xem Top</button>
  </div>
  <script>
    function showTopHeo() {
      const limit = document.getElementById("limitSelect").value;
      window.location.href = `/heo?action=top&limit=${limit}`;
    }
  </script>
</form>

<table class="table table-bordered table-striped">
  <thead>
  <tr>
    <th>STT</th>
    <th>Mã số heo</th>
    <th>Ngày nhập chuồng</th>
    <th>Trọng lượng khi nhập</th>
    <th>Thời gian xuất chuồng</th>
    <th>Trọng lượng khi xuất</th>
    <th>Xuất xứ</th>
    <th>Xuất chuồng</th>
    <th>Detail</th>
    <th>Delete</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="heo" items="${listHeo}" varStatus="status">
    <tr>
      <td>${status.index + 1}</td>
      <td>${heo.maHeo}</td>
      <td>${heo.ngayNhap}</td>
      <td>${heo.trongLuongNhap}</td>
      <td>
        <c:choose>
          <c:when test="${heo.ngayXuat != null}">
            ${heo.ngayXuat}
          </c:when>
          <c:otherwise>Chưa đến ngày xuất</c:otherwise>
        </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${heo.trongLuongXuat != null}">
            ${heo.trongLuongXuat}
          </c:when>
          <c:otherwise>--</c:otherwise>
        </c:choose>
      </td>
      <td>${heo.xuatXu}</td>
      <td>
        <c:choose>
          <c:when test="${heo.ngayXuat != null}">
            <button class="btn btn-success btn-sm" disabled>Đã bán</button>
          </c:when>
          <c:otherwise>
            <button class="btn btn-warning btn-sm">Bán</button>
          </c:otherwise>
        </c:choose>
      </td>
      <td>
        <a href="/heo?action=edit&id=${heo.id}" class="btn btn-info btn-sm">Xem</a>
      </td>
      <td>
        <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                data-bs-target="#deleteModal${heo.id}">Xóa</button>
      </td>
    </tr>
    <div class="modal fade" id="deleteModal${heo.id}" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title">Xác nhận xóa</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            Bạn có chắc muốn xóa heo <strong>${heo.maHeo}</strong> không?
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
            <a href="/heo?action=delete&id=${heo.id}" class="btn btn-danger">Xóa</a>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
  </tbody>
</table>

<script src="../../bootstrap520/js/bootstrap.bundle.min.js"></script>
</body>
</html>

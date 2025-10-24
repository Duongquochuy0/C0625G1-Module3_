<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết heo</title>
    <link rel="stylesheet" href="../../bootstrap520/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-4">

<h3 class="text-center mb-4">Chi tiết heo - ${heo.maHeo}</h3>

<form id="updateForm">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${heo.id}">

    <div class="row mb-3">
        <div class="col-md-6">
            <label class="form-label fw-bold">Mã số heo</label>
            <input type="text" name="maHeo" class="form-control" value="${heo.maHeo}" required>
            <div class="invalid-feedback" id="maHeoError">Mã số heo không được để trống!</div>
        </div>
        <div class="col-md-6">
            <label class="form-label fw-bold">Xuất xứ</label>
            <select name="idXuatXu" class="form-select" required>
                <option value="" ${heo.idXuatXu == null ? 'selected' : ''}>Chọn xuất xứ</option>
                <option value="1" ${heo.idXuatXu == 1 ? 'selected' : ''}>Việt Nam</option>
                <option value="2" ${heo.idXuatXu == 2 ? 'selected' : ''}>Thái Lan</option>
                <option value="3" ${heo.idXuatXu == 3 ? 'selected' : ''}>Mỹ</option>
                <option value="4" ${heo.idXuatXu == 4 ? 'selected' : ''}>Hàn Quốc</option>
            </select>
            <div class="invalid-feedback" id="idXuatXuError">Vui lòng chọn xuất xứ!</div>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-md-6">
            <label class="form-label fw-bold">Ngày nhập chuồng</label>
            <input type="date" name="ngayNhap" class="form-control" value="${heo.ngayNhap}" required>
            <div class="invalid-feedback" id="ngayNhapError">Ngày nhập chuồng không được để trống!</div>
        </div>
        <div class="col-md-6">
            <label class="form-label fw-bold">Ngày xuất chuồng</label>
            <input type="date" name="ngayXuat" class="form-control" value="${heo.ngayXuat}">
            <div class="invalid-feedback" id="ngayXuatError">Ngày xuất chuồng phải sau hoặc bằng ngày nhập chuồng!</div>
        </div>
    </div>

    <div class="row mb-4">
        <div class="col-md-6">
            <label class="form-label fw-bold">Trọng lượng khi nhập (kg)</label>
            <input type="number" name="trongLuongNhap" class="form-control"
                   value="${heo.trongLuongNhap}" required min="10">
            <div class="invalid-feedback" id="trongLuongNhapError">Trọng lượng nhập phải lớn hơn hoặc bằng 10!</div>
        </div>
        <div class="col-md-6">
            <label class="form-label fw-bold">Trọng lượng khi xuất (kg)</label>
            <input type="number" name="trongLuongXuat" class="form-control"
                   value="${heo.trongLuongXuat}">
            <div class="invalid-feedback" id="trongLuongXuatError">Trọng lượng xuất phải lớn hơn hoặc bằng trọng lượng nhập!</div>
        </div>
    </div>

    <div class="text-center">
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/heo'">Đóng</button>
        <button type="button" class="btn btn-primary" id="btnUpdate">Lưu</button>
    </div>
</form>

<script>
    function validateMaHeo() {
        const maHeoInput = $("input[name='maHeo']");
        const maHeo = maHeoInput.val().trim();
        if (maHeo === "") {
            maHeoInput.addClass("is-invalid");
            $("#maHeoError").show();
            return false;
        } else {
            maHeoInput.removeClass("is-invalid");
            $("#maHeoError").hide();
            return true;
        }
    }

    function validateIdXuatXu() {
        const idXuatXuSelect = $("select[name='idXuatXu']");
        const idXuatXu = idXuatXuSelect.val();
        if (idXuatXu === "") {
            idXuatXuSelect.addClass("is-invalid");
            $("#idXuatXuError").show();
            return false;
        } else {
            idXuatXuSelect.removeClass("is-invalid");
            $("#idXuatXuError").hide();
            return true;
        }
    }

    function validateNgayNhap() {
        const ngayNhapInput = $("input[name='ngayNhap']");
        const ngayNhap = ngayNhapInput.val();
        if (ngayNhap === "") {
            ngayNhapInput.addClass("is-invalid");
            $("#ngayNhapError").show();
            return false;
        } else {
            ngayNhapInput.removeClass("is-invalid");
            $("#ngayNhapError").hide();
            return true;
        }
    }

    function validateNgayXuat() {
        const ngayXuatInput = $("input[name='ngayXuat']");
        const ngayXuat = ngayXuatInput.val();
        if (ngayXuat === "") {
            ngayXuatInput.removeClass("is-invalid");
            $("#ngayXuatError").hide();
            return true;
        }
        const ngayNhap = $("input[name='ngayNhap']").val();
        if (ngayNhap === "") {
            return true;
        }
        const dateXuat = new Date(ngayXuat);
        const dateNhap = new Date(ngayNhap);
        if (dateXuat < dateNhap) {
            ngayXuatInput.addClass("is-invalid");
            $("#ngayXuatError").show();
            return false;
        } else {
            ngayXuatInput.removeClass("is-invalid");
            $("#ngayXuatError").hide();
            return true;
        }
    }

    function validateTrongLuongNhap() {
        const trongLuongNhapInput = $("input[name='trongLuongNhap']");
        const trongLuongNhapStr = trongLuongNhapInput.val();
        const trongLuongNhap = parseInt(trongLuongNhapStr);
        if (isNaN(trongLuongNhap) || trongLuongNhap < 10) {
            trongLuongNhapInput.addClass("is-invalid");
            $("#trongLuongNhapError").show();
            return false;
        } else {
            trongLuongNhapInput.removeClass("is-invalid");
            $("#trongLuongNhapError").hide();
            return true;
        }
    }

    function validateTrongLuongXuat() {
        const trongLuongXuatInput = $("input[name='trongLuongXuat']");
        const trongLuongXuatStr = trongLuongXuatInput.val();
        if (trongLuongXuatStr === "") {
            trongLuongXuatInput.removeClass("is-invalid");
            $("#trongLuongXuatError").hide();
            return true;
        }
        const trongLuongXuat = parseInt(trongLuongXuatStr);
        const trongLuongNhapStr = $("input[name='trongLuongNhap']").val();
        const trongLuongNhap = parseInt(trongLuongNhapStr);
        if (isNaN(trongLuongXuat) || (!isNaN(trongLuongNhap) && trongLuongXuat < trongLuongNhap)) {
            trongLuongXuatInput.addClass("is-invalid");
            $("#trongLuongXuatError").show();
            return false;
        } else {
            trongLuongXuatInput.removeClass("is-invalid");
            $("#trongLuongXuatError").hide();
            return true;
        }
    }
    $("input[name='maHeo']").on("input", validateMaHeo);
    $("select[name='idXuatXu']").on("change", validateIdXuatXu);
    $("input[name='ngayNhap']").on("change", function() {
        validateNgayNhap();
        validateNgayXuat();
    });
    $("input[name='ngayXuat']").on("change", validateNgayXuat);
    $("input[name='trongLuongNhap']").on("input", function() {
        validateTrongLuongNhap();
        validateTrongLuongXuat();
    });
    $("input[name='trongLuongXuat']").on("input", validateTrongLuongXuat);
    $(".invalid-feedback").hide();

    $("#btnUpdate").click(function () {
        const isMaHeoValid = validateMaHeo();
        const isIdXuatXuValid = validateIdXuatXu();
        const isNgayNhapValid = validateNgayNhap();
        const isNgayXuatValid = validateNgayXuat();
        const isTrongLuongNhapValid = validateTrongLuongNhap();
        const isTrongLuongXuatValid = validateTrongLuongXuat();

        if (!isMaHeoValid || !isIdXuatXuValid || !isNgayNhapValid || !isNgayXuatValid || !isTrongLuongNhapValid || !isTrongLuongXuatValid) {
            return;
        }

        $.ajax({
            url: "/heo",
            type: "POST",
            data: $("#updateForm").serialize(),
            success: function () {
                alert("Cập nhật thành công!");
                window.location.href = "/heo";
            },
            error: function () {
                alert("Cập nhật thất bại!");
            }
        });
    });
</script>
<script src="../../bootstrap520/js/bootstrap.bundle.min.js"></script>
</body>
</html>
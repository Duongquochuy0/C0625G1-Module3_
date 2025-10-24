<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm Nhà Trọ Mới</title>
    <link rel="stylesheet" href="../../bootstrap520/css/bootstrap.min.css">
    <script>
        function validateForm() {
            const maNhaTro = document.getElementById("maNhaTro").value.trim();
            const ten = document.getElementById("ten").value.trim();
            const soDienThoai = document.getElementById("soDienThoai").value.trim();
            const ngayBatDau = document.getElementById("ngayBatDau").value;
            const ghiChu = document.getElementById("ghiChu").value.trim();
            let isValid = true;
            let errorMsg = "";

            if (maNhaTro === "") {
                errorMsg += "- Mã nhà trọ không được để trống\n";
                isValid = false;
            }

            if (ten === "") {
                errorMsg += "- Tên nhà trọ không được để trống\n";
                isValid = false;
            }

            const regexPhone = /^[0-9]{9,11}$/;
            if (!regexPhone.test(soDienThoai)) {
                errorMsg += "- Số điện thoại phải là số, từ 9-11 chữ số\n";
                isValid = false;
            }

            if (ngayBatDau === "") {
                errorMsg += "- Ngày bắt đầu thuê không được để trống\n";
                isValid = false;
            } else {
                const today = new Date();
                today.setHours(0, 0, 0, 0);
                const ngayChon = new Date(ngayBatDau);
                if (ngayChon < today) {
                    errorMsg += "- Ngày bắt đầu thuê không được bé hơn ngày hiện tại\n";
                    isValid = false;
                }
            }

            if (ghiChu.length > 200) {
                errorMsg += "- Ghi chú không được quá 200 ký tự\n";
                isValid = false;
            }

            if (!isValid) {
                alert("Vui lòng kiểm tra lại thông tin:\n" + errorMsg);
            }

            return isValid;
        }
    </script>

</head>
<body class="container mt-4">
<h2 class="text-center mb-4">Thêm Nhà Trọ Mới</h2>

<form action="/nhaTro?action=create" method="post" onsubmit="return validateForm()" class="border p-4 rounded shadow-sm bg-light">
    <div class="mb-3">
        <label for="maNhaTro" class="form-label">Mã Nhà Trọ:</label>
        <input type="text" class="form-control" id="maNhaTro" name="maNhaTro">
    </div>

    <div class="mb-3">
        <label for="ten" class="form-label">Tên Nhà Trọ:</label>
        <input type="text" class="form-control" id="ten" name="ten">
    </div>

    <div class="mb-3">
        <label for="soDienThoai" class="form-label">Số Điện Thoại:</label>
        <input type="text" class="form-control" id="soDienThoai" name="soDienThoai">
    </div>

    <div class="mb-3">
        <label for="ngayBatDau" class="form-label">Ngày Bắt Đầu Thuê:</label>
        <input type="date" class="form-control" id="ngayBatDau" name="ngayBatDau">
    </div>

    <div class="mb-3">
        <label for="hinhThucThanhToan" class="form-label">Hình Thức Thanh Toán:</label>
        <select id="hinhThucThanhToan" name="hinhThucThanhToanId" class="form-select">
            <option value="">-- Chọn hình thức thanh toán --</option>
            <c:forEach var="ht" items="${listHTTT}">
                <option value="${ht.id}">${ht.ten}</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label for="ghiChu" class="form-label">Ghi Chú:</label>
        <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3"></textarea>
    </div>

    <div class="text-center">
        <button type="submit" class="btn btn-success px-4">Thêm Mới</button>
        <a href="/nhaTro" class="btn btn-secondary px-4">Quay Lại</a>
    </div>
</form>
</body>
</html>

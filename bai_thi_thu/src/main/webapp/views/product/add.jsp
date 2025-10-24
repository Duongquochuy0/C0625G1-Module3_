<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm mới</title>
    <link href="${pageContext.request.contextPath}/bootstrap520/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
<h2>Thêm sản phẩm mới</h2>

<form id="addProductForm" action="product" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="add">

    <div class="mb-3">
        <label for="code" class="form-label">Mã sản phẩm (MHH-XXXX):</label>
        <input type="text" name="code" id="code" class="form-control" required>
    </div>

    <div class="mb-3">
        <label for="name" class="form-label">Tên sản phẩm:</label>
        <input type="text" name="name" id="name" class="form-control" required>
    </div>

    <div class="mb-3">
        <label for="unit" class="form-label">Đơn vị:</label>
        <select name="unit" id="unit" class="form-select" required>
            <option value="">--Chọn đơn vị--</option>
            <option value="Bó">Bó</option>
            <option value="Kg">Kg</option>
            <option value="Cái">Cái</option>
        </select>
    </div>

    <div class="mb-3">
        <label for="price" class="form-label">Giá (VNĐ, >=1000):</label>
        <input type="number" name="price" id="price" class="form-control" required min="1000" step="1">
    </div>

    <div class="mb-3">
        <label for="categoryId" class="form-label">Loại sản phẩm:</label>
        <select name="categoryId" id="categoryId" class="form-select" required>
            <option value="">--Chọn loại--</option>
            <c:forEach var="c" items="${categories}">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-success">Thêm sản phẩm</button>
    <a href="product" class="btn btn-secondary">Quay lại</a>
</form>

<script>
    function validateForm() {
        const code = document.getElementById('code').value.trim();
        const codePattern = /^MHH-[A-Z0-9]{4}$/;
        if (!codePattern.test(code)) {
            alert("Mã hàng hoá phải đúng định dạng MHH-XXXX (X là chữ hoa hoặc số).");
            return false;
        }
        const name = document.getElementById('name').value.trim();
        if (name === "") {
            alert("Tên sản phẩm không được để trống.");
            return false;
        }
        const unit = document.getElementById('unit').value;
        if (unit === "") {
            alert("Vui lòng chọn đơn vị.");
            return false;
        }
        const price = parseInt(document.getElementById('price').value);
        if (isNaN(price) || price < 1000) {
            alert("Giá phải là số nguyên dương và >= 1.000 VNĐ.");
            return false;
        }
        const category = document.getElementById('categoryId').value;
        if (category === "") {
            alert("Vui lòng chọn loại sản phẩm.");
            return false;
        }

        return true;
    }
</script>
</body>
</html>

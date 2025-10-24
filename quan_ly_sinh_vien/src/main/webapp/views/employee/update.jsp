<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật nhân viên</title>
    <link rel="stylesheet" href="/bootstrap520/css/bootstrap.min.css">
</head>
<body class="container mt-4">
<h2>Cập nhật nhân viên</h2>
<form action="/employee?action=edit" method="post">
    <input type="hidden" name="id" value="${employee.id}">

    <div class="mb-3">
        <label for="code" class="form-label">Mã nhân viên</label>
        <input type="text" class="form-control" id="code" name="code" value="${employee.code}" required>
    </div>

    <div class="mb-3">
        <label for="name" class="form-label">Tên nhân viên</label>
        <input type="text" class="form-control" id="name" name="name" value="${employee.name}" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Giới tính</label>
        <div>
            <input type="radio" id="male" name="gender" value="Nam"
            ${employee.gender == 'Nam' ? 'checked' : ''}>
            <label for="male">Nam</label>

            <input type="radio" id="female" name="gender" value="Nữ"
            ${employee.gender == 'Nữ' ? 'checked' : ''}>
            <label for="female">Nữ</label>
        </div>
    </div>

    <div class="mb-3">
        <label for="salary" class="form-label">Lương</label>
        <input type="number" step="0.01" class="form-control" id="salary" name="salary"
               value="${employee.salary}" required>
    </div>

    <div class="mb-3">
        <label for="departmentId" class="form-label">Phòng ban</label>
        <select class="form-select" id="departmentId" name="departmentId" required>
            <option value="">-- Chọn phòng ban --</option>
            <c:forEach var="dept" items="${listDepartment}">
                <option value="${dept.id}"
                    ${dept.id == employee.departmentId ? 'selected' : ''}>
                        ${dept.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhật</button>
    <a href="/employee" class="btn btn-secondary">Hủy</a>
</form>
</body>
</html>

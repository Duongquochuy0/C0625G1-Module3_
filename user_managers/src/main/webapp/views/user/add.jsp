<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Thêm User Mới</h2>

    <form action="/users?action=add" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input name="name" type="text" class="form-control" id="name" placeholder="Nhập tên" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input name="email" type="email" class="form-control" id="email" placeholder="Nhập email" required>
        </div>

        <div class="mb-3">
            <label for="country" class="form-label">Country</label>
            <input name="country" type="text" class="form-control" id="country" placeholder="Nhập quốc gia" required>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="/users?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

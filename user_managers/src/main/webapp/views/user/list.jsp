<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; font-family: 'Nunito', sans-serif; }
        .container { max-width: 950px; margin-top: 50px; }
        .card { border-radius: 12px; box-shadow: 0 10px 25px rgba(0,0,0,0.08); padding: 25px; background-color: #fff; }
        h2 { font-weight: 700; color: #333; margin-bottom: 30px; text-align: center; }
        .table thead { background-color: #4e73df; color: #fff; font-weight: 600; }
        .table td, .table th { vertical-align: middle; }
        .btn { border-radius: 8px; font-size: 0.85rem; padding: 4px 10px; }
        .btn-warning { background-color: #f6c23e; border-color: #f6c23e; color: #fff; }
        .btn-warning:hover { background-color: #dda20a; border-color: #dda20a; }
        .btn-danger { background-color: #e74a3b; border-color: #e74a3b; color: #fff; }
        .btn-danger:hover { background-color: #c0392b; border-color: #c0392b; }
        .btn-primary { background-color: #1cc88a; border-color: #1cc88a; color: #fff; }
        .btn-primary:hover { background-color: #17a673; border-color: #17a673; }
        .table-hover tbody tr:hover { background-color: #f8f9fc; }
        .actions a { margin-right: 5px; }
        .form-inline { display: flex; gap: 10px; margin-bottom: 15px; }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <h2>Danh sách User</h2>

        <form class="form-inline" method="get" action="/users">
            <input type="hidden" name="action" value="list"/>
            <input type="text" name="country" class="form-control" placeholder="Search by Country" value="${param.country}"/>
            <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Search</button>
            <a href="/users?action=list" class="btn btn-secondary">Reset</a>
        </form>

        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle text-center">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${userList}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.country}"/></td>
                        <td class="actions">
                            <a href="/users?action=update&id=${user.id}" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit"></i> Update
                            </a>
                            <a href="/users?action=delete&id=${user.id}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Bạn có chắc muốn xóa user này?');">
                                <i class="fas fa-trash"></i> Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="/users?action=add" class="btn btn-primary mb-3 float-end"><i class="fas fa-plus"></i> Thêm User</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/js/all.min.js"></script>
</body>
</html>

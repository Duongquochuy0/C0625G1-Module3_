<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Update User</h2>
    <form action="/users?action=update" method="post">
        <input type="hidden" name="id" value="${user.id}"/>
        <div class="mb-3">
            <label>Name</label>
            <input type="text" class="form-control" name="name" value="${user.name}"/>
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="email" class="form-control" name="email" value="${user.email}"/>
        </div>
        <div class="mb-3">
            <label>Country</label>
            <input type="text" class="form-control" name="country" value="${user.country}"/>
        </div>
        <button type="submit" class="btn btn-success">Update</button>
        <a href="/users?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>

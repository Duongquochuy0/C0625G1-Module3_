<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>User List</title></head>
<body>
<h1>Danh sách User</h1>
<table border="1">
  <tr><th>ID</th><th>Name</th><th>Email</th><th>Country</th></tr>
  <c:forEach var="user" items="${users}">
    <tr>
      <td>${user.id}</td>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${user.country}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

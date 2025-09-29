<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Kết quả</title></head>
<body>
<h1>Kết quả</h1>

<c:if test="${not empty error}">
  <p style="color:red;">Lỗi: ${error}</p>
</c:if>

<c:if test="${empty error}">
  <p>
      ${firstOperand} ${operator} ${secondOperand} =
    <strong>${result}</strong>
  </p>
</c:if>

<a href="${pageContext.request.contextPath}/index.jsp">Quay lại</a>
</body>
</html>

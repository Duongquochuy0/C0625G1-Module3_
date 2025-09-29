<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

<h1 class="text-center mb-4">Máy tính của Huy</h1>

<form method="post" action="${pageContext.request.contextPath}/calculate"
      class="border p-4 rounded shadow-sm bg-light">
    <fieldset>
        <legend>Calculator</legend>
        <table class="table table-borderless">
            <tr>
                <td>First operand:</td>
                <td>
                    <input name="first-operand" type="number" step="any"
                           class="form-control" required/>
                </td>
            </tr>
            <tr>
                <td>Operator:</td>
                <td>
                    <select name="operator" class="form-select">
                        <option value="+">Addition</option>
                        <option value="-">Subtraction</option>
                        <option value="*">Multiplication</option>
                        <option value="/">Division</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Second operand:</td>
                <td>
                    <input name="second-operand" type="number" step="any"
                           class="form-control" required/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit" class="btn btn-primary">Calculate</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>

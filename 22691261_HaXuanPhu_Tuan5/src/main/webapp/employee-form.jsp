<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 9/22/2025
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h2>${employee != null ? "Edit Employee" : "New Employee"}</h2>
    <form action="employee-controller" method="post">
        <input type="hidden" name="id" value="${employee.id}">
        <div class="mb-3">
            <label>Name:</label>
            <input type="text" name="name" value="${employee.name}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Salary:</label>
            <input type="number" step="0.01" name="salary" value="${employee.salary}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Department:</label>
            <label>
                <select name="departmentId" class="form-select">
                    <c:forEach var="d" items="${departments}">
                        <option value="${d.id}" ${employee.departmentId == d.id ? "selected" : ""}>
                                ${d.name}
                        </option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <button type="submit" class="btn btn-success">Save</button>
        <a href="employee-controller" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>

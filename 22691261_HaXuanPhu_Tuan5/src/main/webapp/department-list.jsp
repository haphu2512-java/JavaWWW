<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 9/22/2025
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
    <title>Department Management</title>
</head>
<body>
<div name="header">
    <banner>
        <img src="images/hr.png">
    </banner>
</div>
<div name="middle">
    <h2>Department List</h2>
    <a href="department-controller?action=new">Add New Department</a>
    <br/><br/>
    <table border="1" width="50%">
        <tr>
            <th>Department ID</th>
            <th>Department Name</th>
            <th>Actions</th>
        </tr>

        <!-- Sửa phần này -->
        <c:forEach items="${departments}" var="dept">
            <tr>
                <td>${dept.id}</td>
                <td>${dept.name}</td>
                <td>
                    <a href="department-controller?action=edit&id=${dept.id}">Edit</a> |
                    <a href="department-controller?action=delete&id=${dept.id}">Delete</a> |
                    <a href="employee-controller?action=listByDept&deptId=${dept.id}">Employees</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <!-- Debug: Kiểm tra dữ liệu -->
    <p>Total departments: ${departments.size()}</p>
</div>
</body>
</html>
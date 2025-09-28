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
    <div name="header">
        <banner>
            <img src="images/hr.png">
        </banner>
    </div>
    <div name="middle">
        <h2>Employee List</h2>
        <a href="employee-controller?action=new">Add New Department</a>
        <br/><br/>
        <table border="1" width="50%">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Dept</th>
                <th>Action</th>
            </tr>

            <!-- Sửa phần này -->
            <c:forEach items="${employees}" var="emp">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.salary}</td>
                    <td>.</td>
                    <td>
                        <a href="employee-controller?action=edit&id=${emp.id}">Edit</a> |
                        <a href="employee-controller?action=delete&id=${emp.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    <br/>
        <!-- Debug: Kiểm tra dữ liệu -->
        <a href="department-controller">Department</a>
    </div>
</body>
</html>

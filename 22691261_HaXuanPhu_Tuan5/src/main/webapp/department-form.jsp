<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 9/22/2025
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <banner>
        <img src="images/hr.png">
    </banner>

    <br/>
    <h2>Departments Information</h2>
    <form action="department-controller" method="post">
        <input type="hidden" name="id" >
        Name: <input type="text" name="name" >
        <br/>
        <input type="submit" value="Save">
    </form>
</body>
</html>

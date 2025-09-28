<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 8/25/2025
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File DB</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/uploadFileDB" method="post" enctype="multipart/form-data">
        FirstName: <input type="text" name="firstName"/><br><br>
        LastName: <input type="text" name="lastName"/><br><br>
        Photo: <input type="file" name="photo"/><br><br>
        <input type="submit" value="Save"/>
    </form>
</body>
</html>

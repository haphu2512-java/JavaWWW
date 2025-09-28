<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration Form</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f4f4f4;
        }
        .form-container {
            width: 350px;
            margin: 100px auto;
            padding: 30px;
            background: white;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }
        .form-container input[type="text"],
        .form-container input[type="email"],
        .form-container input[type="password"],
        .form-container select {
            width: 100%;
            padding: 10px;
            margin: 6px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .gender {
            margin: 10px 0;
        }
        .gender label {
            margin-right: 10px;
        }
        .form-container input[type="submit"] {
            width: 100%;
            padding: 12px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .form-container input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>User Registration Form</h2>
    <form action="${pageContext.request.contextPath}/registerform" method="post">
        <input type="text" name="firstName" placeholder="First Name" required />
        <input type="text" name="lastName" placeholder="Last Name" required />
        <input type="email" name="email" placeholder="Your Email" required />
        <input type="password" name="password" placeholder="Password" required />

        <label>Birthday</label><br />
        <select name="month">
            <option value="">Month</option>
            <% for(int i=1; i<=12; i++) { %>
            <option value="<%= i %>"><%= i %></option>
            <% } %>
        </select>
        <select name="day">
            <option value="">Day</option>
            <% for(int i=1; i<=31; i++) { %>
            <option value="<%= i %>"><%= i %></option>
            <% } %>
        </select>
        <select name="year">
            <option value="">Year</option>
            <% for(int i=2025; i>=1900; i--) { %>
            <option value="<%= i %>"><%= i %></option>
            <% } %>
        </select>


        <input type="submit" value="Sign Up" />
    </form>
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Status</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }

        .container {
            background-color: white;
            border: 2px solid #4a90e2;
            padding: 30px;
            max-width: 800px;
            margin: 0 auto;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .success {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .error {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .title {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        .registration-details {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin: 20px 0;
        }

        .detail-row {
            display: flex;
            margin-bottom: 10px;
            border-bottom: 1px solid #e9ecef;
            padding-bottom: 8px;
        }

        .detail-label {
            font-weight: bold;
            width: 150px;
            color: #495057;
        }

        .detail-value {
            flex: 1;
            color: #212529;
        }

        .hobbies-list {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .hobby-item {
            background-color: #e9ecef;
            padding: 3px 8px;
            border-radius: 15px;
            font-size: 12px;
        }

        .action-buttons {
            text-align: center;
            margin-top: 30px;
        }

        .btn {
            padding: 10px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
        }

        .btn-primary {
            background-color: #4a90e2;
            color: white;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }

        .btn:hover {
            opacity: 0.8;
        }

        .registration-id {
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            color: #856404;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="title">Registration Successful!</div>

    <div class="success">
        <strong>Congratulations!</strong> Your registration has been processed successfully.
    </div>

    <div class="registration-details">
        <h3>Registration Details</h3>

        <div class="detail-row">
            <div class="detail-label">Full Name:</div>
            <div class="detail-value">${student.firstName} ${student.lastName}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Date of Birth:</div>
            <div class="detail-value">${student.dob}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Email:</div>
            <div class="detail-value">${student.email}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Mobile:</div>
            <div class="detail-value">${student.mobile}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Gender:</div>
            <div class="detail-value">${student.gender}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Address:</div>
            <div class="detail-value">${student.address}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">City:</div>
            <div class="detail-value">${student.city}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Pin Code:</div>
            <div class="detail-value">${student.pinCode}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">State:</div>
            <div class="detail-value">${student.state}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Country:</div>
            <div class="detail-value">${student.country}</div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Hobbies:</div>
            <div class="detail-value">
              <ul>
                  <c:forEach var="h" items="${student.hobbies}">
                      <li>${h}</li>
                  </c:forEach>
              </ul>
            </div>
        </div>

        <div class="detail-row">
            <div class="detail-label">Course:</div>
            <div class="detail-value">${student.course}</div>
        </div>
    </div>



</div>

</body>
</html>
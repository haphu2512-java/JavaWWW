<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gửi Mail</title>
    <style>
        body {
            background-color: #1e1e1e;
            color: white;
            font-family: Arial, sans-serif;
        }
        label {
            display: inline-block;
            width: 100px;
            margin-bottom: 8px;
        }
        input[type="text"], textarea {
            width: 300px;
            padding: 5px;
            margin-bottom: 10px;
        }
        textarea {
            height: 120px;
        }
        .button {
            background-color: #444;
            border: none;
            color: white;
            padding: 8px 16px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #666;
        }
    </style>
</head>
<body>
<h2>Form gửi mail</h2>
<form action="sendMail" method="post" enctype="multipart/form-data">
    <div>
        <label for="to">Người nhận:</label>
        <input type="text" name="to" id="to" required>
    </div>
    <div>
        <label for="subject">Tiêu đề:</label>
        <input type="text" name="subject" id="subject" required>
    </div>
    <div>
        <label for="content">Nội dung:</label><br>
        <textarea name="content" id="content"></textarea>
    </div>
    <div>
        <label for="file">File đính kèm:</label>
        <input type="file" name="file" id="file">
    </div>
    <br>
    <button type="submit" class="button">Gửi mail</button>
</form>
</body>
</html>

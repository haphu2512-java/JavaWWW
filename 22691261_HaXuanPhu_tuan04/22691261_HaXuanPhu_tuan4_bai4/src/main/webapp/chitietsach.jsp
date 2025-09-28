<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 9/15/2025
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="fit.se.haxuanphu_tuan4_bai4.beans.Book"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="book" scope="request" type="fit.se.haxuanphu_tuan4_bai4.beans.Book" />
<!DOCTYPE html>
<html>
<head>
    <title>ChiTiet</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<header
        class="container d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/book-controller"
       class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <span class="fs-4">IUH Bookstore</span>
    </a>

    <ul class="nav nav-pills">
        <li class="nav-item"><a href="#" class="nav-link active"
                                aria-current="page">Home</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Examples</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Services</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Products</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
    </ul>
</header>
<div class="container">
    <div class="row gx-5">
        <div class="col-4">
            <div class="card">
                <div class="card-header">About us</div>
                <div class="card-body">
                    <h5 class="card-title">IUH Bookstore</h5>
                    <p class="card-text">
                        IUH Bookstore is a website that provides books for students at
                        IUH. We have a lot of books for you to choose from <a href="#"
                                                                              class="btn-link">Read more</a>
                    </p>
                </div>
            </div>
            <div class="card mt-4">
                <div class="card-header">Search site</div>
                <div class="card-body">
                    <h5 class="card-title">Search for your favorite book</h5>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="card p-3 d-flex flex-row align-items-start" style="max-width: 700px;">

                <!-- Hình bên trái -->
                <div style="flex: 1; max-width: 40%;">
                    <img src="${pageContext.request.contextPath}/resources/${book.imgURL}"
                         class="img-fluid rounded" alt="${book.title}">
                </div>

                <!-- Thông tin bên phải -->
                <div class="card-body" style="flex: 2; margin-left: 20px;">
                    <h5 class="card-title">${book.title}</h5>
                    <p class="card-text"><strong>Giá:</strong> ${book.price}đ</p>
                    <p class="card-text"><strong>Số lượng:</strong> ${book.quantity}</p>
                    <p class="card-text"><strong>Mô tả:</strong> ${book.description}</p>

                    <!-- Nút Trở về -->
                    <div class="mt-3">
                        <a href="book-controller" class="btn btn-primary">Trở về</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

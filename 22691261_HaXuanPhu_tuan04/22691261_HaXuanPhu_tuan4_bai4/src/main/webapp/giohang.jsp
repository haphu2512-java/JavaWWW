<%--
  Created by IntelliJ IDEA.
  User: haxua
  Date: 9/15/2025
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">

    <title>Document</title>

    <style>
        .quantity-control {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .quantity-btn {
            color: black;
            border: none !important;
            border-radius: 50%;
            width: 32px;
            height: 32px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            padding: 0;
            margin: 0 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .table td, .table th {
            vertical-align: middle;
            text-align: center;
        }


    </style>

</head>
<body>
<div class="container container-fluid">
    <header
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="book-controller"
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
    <main>
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
                <c:if test="${not empty cart && cart.cartItems.size() > 0}">
                    <h3 class="text-center">Giỏ hàng của bạn</h3>
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Tên sách</th>
                            <th scope="col">Giá tiền</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Tổng tiền</th>
                            <th scope="col">Hành động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cart.cartItems}" var="item">
                            <tr>
                                <th scope="row">${item.book.id}</th>
                                <td>${item.book.title}</td>
                                <td>${item.book.price}</td>
                                <td>
                                    <div class="quantity-control">
                                        <a href="${pageContext.request.contextPath}/cart?action=decrease&id=${item.book.id}"
                                           class="quantity-btn">-</a>
                                        <span style="min-width:30px; text-align:center;">${item.quantity}</span>
                                        <a href="${pageContext.request.contextPath}/cart?action=increase&id=${item.book.id}"
                                           class="quantity-btn">+</a>
                                    </div>
                                </td>

                                <td>${item.book.price * item.quantity}</td>
                                <td><a
                                        href="${pageContext.request.contextPath}/cart?action=delete&id=${item.book.id}"
                                        class="btn btn-danger">Xóa</a></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4" align="right">Tổng tiền:</td>
                            <td>${cart.orderTotal}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div>
                        <a href="book-controller" class="btn btn-primary">Trở về</a>
                        <a href="thanhtoan.jsp" class="btn btn-danger">Thanh toán</a>
                    </div>
                </c:if>
                <c:if test="${empty cart || cart.cartItems.size() == 0}">
                    <h3 class="text-center">Giỏ hàng của bạn trống</h3>
                </c:if>
            </div>
        </div>
    </main>
</div>

</body>
</html>
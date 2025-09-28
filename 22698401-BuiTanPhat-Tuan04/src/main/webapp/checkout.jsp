<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 9/15/2025
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:useBean id="cart" scope="session" type="com.se.buitanphattuan04.beans.Cart" />

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
</head>
<body>
<div class="container container-fluid">
    <header
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
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
            <c:if test="${not empty sessionScope.cart}">
                <li class="nav-item"><a href="cart.jsp"
                                        class="btn btn-primary">Giỏ hàng</a></li>
            </c:if>
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
            <div class="col-8 border border-2 pt-2">

                    <div class="mb-3 row">
                        <label for="fullname" class="col-sm-2 col-form-label">Fullname:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="fullname"
                                   placeholder="Enter your full name">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label for="shippingAddress" class="col-sm-2 col-form-label">Shipping
                            address:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="shippingAddress"
                                   placeholder="Enter your shipping address">
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label for="totalPrice" class="col-sm-2 col-form-label">Total
                            price:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="totalPrice"
                                   value="${cart.orderTotal}" readonly>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label class="col-sm-2 col-form-label">Payment:</label>
                        <div class="col-sm-10">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="paymentMethod" id="paypal" value="Paypal"> <label
                                    class="form-check-label" for="paypal">Paypal</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="paymentMethod" id="atm" value="ATM Debit"> <label
                                    class="form-check-label" for="atm">ATM Debit</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio"
                                       name="paymentMethod" id="visa" value="Visa/Master card">
                                <label class="form-check-label" for="visa">Visa/Master
                                    card</label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-10 offset-sm-2">
                            <a href="cart?action=checkout" class="btn btn-primary">Save</a>
                            <a href="book-controller" class="btn btn-secondary">Cancel</a>
                        </div>
                    </div>
            </div>
        </div>

    </main>
</div>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
</body>
</html>

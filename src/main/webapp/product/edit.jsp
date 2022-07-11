<%--
  Created by IntelliJ IDEA.
  User: Tran Hiep
  Date: 11/07/2022
  Time: 2:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <!-- Page Meta Tags-->
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="">

    <!-- Custom Google Fonts-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@500;600&family=Roboto:wght@300;400;700&display=auto"
          rel="stylesheet">

    <!-- Favicon -->
    <link rel="apple-touch-icon" sizes="180x180" href="../assets/images/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../assets/images/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../assets/images/favicon/favicon-16x16.png">
    <link rel="mask-icon" href="./assets/images/favicon/safari-pinned-tab.svg" color="#5bbad5">
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#ffffff">

    <!-- Vendor CSS -->
    <link rel="stylesheet" href="../assets/css/libs.bundle.css" />

    <!-- Main CSS -->
    <link rel="stylesheet" href="../assets/css/theme.bundle.css" />

    <!-- Fix for custom scrollbar if JS is disabled-->
    <noscript>
        <style>
            /**
                * Reinstate scrolling for non-JS clients
                */
            .simplebar-content-wrapper {
                overflow: auto;
            }
        </style>
    </noscript>

    <!-- Page Title -->
    <title>OldSkool | Bootstrap 5 HTML Template</title>
</head>

<body class=" bg-light">

<!-- Main Section-->
<section
        class="mt-0 overflow-hidden  vh-100 d-flex justify-content-center align-items-center p-4">
    <!-- Page Content Goes Here -->

    <!-- Login Form-->
    <div class="col col-md-8 col-lg-6 col-xxl-5">
        <!-- Logo-->
        <a class="navbar-brand fw-bold fs-3 flex-shrink-0 order-0 align-self-center justify-content-center d-flex mx-0 px-0" href="../index.jsp">
            <div class="d-flex align-items-center">
                <svg class="f-w-7" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 77.53 72.26"><path d="M10.43,54.2h0L0,36.13,10.43,18.06,20.86,0H41.72L10.43,54.2Zm67.1-7.83L73,54.2,68.49,62,45,48.47,31.29,72.26H20.86l-5.22-9L52.15,0H62.58l5.21,9L54.06,32.82,77.53,46.37Z" fill="currentColor" fill-rule="evenodd"/></svg>
            </div>
        </a>
        <!-- / Logo-->
        <div class="shadow-xl p-4 p-lg-5 bg-white">
            <form action="/product-managements" method="post">
                <table>
                    <tr>
                        <td><label class="form-label">Name:</label></td>
                        <td><input type="text" name="name" value="${product.name}"></td>
                    </tr>
                    <tr>
                        <td><label class="form-label">Price:</label></td>
                        <td><input type="number" name="price" value="${product.price}"></td>
                    </tr>
                    <tr>
                        <td><label class="form-label">Description:</label></td>
                        <td><input type="text" name="description" value="${product.description}"></td>
                    </tr>
                    <tr>
                        <td><label class="form-label">Image:</label></td>
                        <td><input type="text" name="image" value="${product.image}"></td>
                    </tr>
                    <tr>
                        <td><label class="form-label">Category:</label></td>
                        <td>
                            <select name="categoryId" id="categoryId">
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button type="submit" class="btn btn-dark d-block w-100 my-4">Change Product</button>
            </form>
        </div>

    </div>
    <!-- / Login Form-->

    <!-- /Page Content -->
</section>
<!-- / Main Section-->


<!-- Theme JS -->
<!-- Vendor JS -->
<script src="../assets/js/vendor.bundle.js"></script>

<!-- Theme JS -->
<script src="../assets/js/theme.bundle.js"></script>
</body>
</html>

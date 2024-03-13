<%-- 
    Document   : account
    Created on : Feb 28, 2024, 11:21:11 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="./assets/css/style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="my-account">
                <div class="left-content">
                    <h1 class="block-title" style="color:red">TÀI KHOẢN</h1>
                    <div class="block-cotent">
                        <ul style="padding:0">
                            <li style="background-color:${backgroundColorMain}">
                                <a href="#" style="color: ${colorMain}"
                                   >
                                    THÔNG TIN CHUNG
                                </a>
                            </li>
                            <li style="background-color:${backgroundColorSecond}">
                                <a style="color: ${colorSecond}" href="update">
                                CHI TIẾT TÀI KHOẢN
                                </a>
                            </li>
                            <li style="background-color:${backgroundColorSecond}">
                                <a style="color: ${colorSecond}"  href="historyPayment?userID=${sessionScope.account.getUserID()}">
                                LỊCH SỬ GIAO DỊCH
                                </a>
                            </li>
                            <c:if test="${sessionScope.account.role.name.equals('admin')}">
                                <li style="background-color:${backgroundColorSecond}">
                                    <a style="color: ${colorSecond}"  href="addNewMovie">
                                        THÊM PHIM MỚI
                                    </a>
                                </li>
                                <li style="background-color:${backgroundColorSecond}">
                                    <a style="color: ${colorSecond}"  href="detailUsers">
                                        QUẢN LÝ NGƯỜI DÙNG
                                    </a>
                                </li>
                            </c:if>
                        </ul>

                    </div>
                </div>
                <div class="dashboard">
                    <div class="page-title">
                        <h1>THÔNG TIN CHUNG</h1>
                    </div>
                    <div class="format-profile">
                        <div class="welcome-msg">
                            <p class="hello">Xin chào <strong>${sessionScope.account.displayName}</strong></p>
                            <p>Với trang này, bạn sẽ quản lý được tất cả thông tin tài khoản của mình.</p>
                        </div>
                        <div class="box-account box-info">
                            <div class="box-head" style="font-weight: bold">Thông tin tài khoản</div>
                            <div class="box" style="display: inline-block">
                                <div class="box-title">
                                    <h3 class="title-my-account">LIÊN HỆ</h3>
                                    <a href="update">Thay đổi</a>
                                </div>
                                <div class="box-content">
                                    <span>Tên: ${sessionScope.account.displayName}</span>
                                    <span>Tài Khoản: ${sessionScope.account.username}</span>
                                    <span>Chức danh: ${sessionScope.account.role.name}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>

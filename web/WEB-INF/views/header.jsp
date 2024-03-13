<%-- 
    Document   : header
    Created on : Jan 11, 2024, 11:35:33 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- <link rel="stylesheet" type="text/css" href="assets/style1.css" /> -->
        <!-- <link rel="stylesheet" type="text/css" href="style3.css" /> -->
        <!-- <link rel="stylesheet" type="text/css" href="./style3.css" /> -->
        <!-- <link rel="stylesheet" type="text/css" href="assets/style1.css" /> -->
        <!-- <link rel="stylesheet" href="assets/css/style.css" />
        <link rel="stylesheet"  href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet"  href="assets/css/bootstrap.min.css.map" /> -->

        <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
        <link rel="stylesheet"  type="text/css" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet"  type="text/css" href="assets/css/bootstrap.min.css.map" />
    </head>
    <body>
        <div class="header-language-background">
            <div class="header-container">
                <div class="header-option">
                    <div class="header-information">
                        <p>
                            <i class="fa-solid fa-circle-info fa-xl"></i>
                            <a href="#">TIN MỚI & ƯU ĐÃI</a>
                        </p>
                    </div>
                    <div class="header-my-ticket">
                        <p>
                            <i class="fa-solid fa-ticket fa-xl"></i>
                            <a href="historyPayment?userID=${sessionScope.account.getUserID()}">VÉ CỦA TÔI</a>
                        </p>
                    </div>
                    <div class="header-account" style="display: contents">
                        <p>
                            <i class="fa-solid fa-user fa-xl" style="padding-right: 10px"></i>
                            <c:if test="${sessionScope.account == null}">
                                <a href="./signin">ĐĂNG NHẬP/ ĐĂNG KÝ</a>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                                <a href="account" style="font-weight: 700;overflow: hidden;text-overflow: ellipsis">${sessionScope.account.displayName.toUpperCase()}</a>
                                <a href="logout" style="font-weight:700; color: red">| ĐĂNG XUẤT</a>
                            </c:if>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <header class="page-header">
            <div class="page-header-container">
                <!-- LOGO chuyen toi trang chu -->
                <a class="logo" href="home">
                    <img src="./assets/images/LogoFPT.png" alt="logo"/>
                </a>
                <div class="header-nav-bar">
                    <ol class="nav-primary">
                        <li class="level0-nav-1-parent">
                            <a class="level0-has-children" href="#">PHIM</a>
                            <ul class="level0">
                                <li class="level1-nav1-1">
                                    <a href="home" class="level1">Phim Đang Chiếu</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="commingSoon" class="level1">Phim Sắp Chiếu</a>
                                </li>
                                <li class="level1-nav1-2"></li>
                            </ul>
                        </li>
                        <li class="level0-nav-1-parent">
                            <a class="level0-has-children" href="#">THỂ LOẠI</a>
                            <ul class="level0">
                                <li class="level1-nav1-1">
                                    <a href="genres?genreID=1" class="level1">Hành Động</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=2" class="level1">Phưu Lưu</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=3" class="level1">Tâm Lý</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=4" class="level1">Tình Cảm</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=5" class="level1">Kinh Dị</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=6" class="level1">Gia Đình</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=7" class="level1">Hài</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=8" class="level1">Hoạt Hình</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="genres?genreID=9" class="level1">Hồi Hộp</a>
                                </li>
                                
                            </ul>
                        </li>
                        <li class="level0-nav-1-parent">
                            <a class="level0-has-children" href="#">THÀNH VIÊN</a>
                            <ul class="level0">
                                <li class="level1-nav1-1">
                                    <a href="#" class="level1">TÀI KHOẢN CGV</a>
                                </li>
                                <li class="level1-nav1-2">
                                    <a href="#" class="level1">QUYỀN LỢI</a>
                                </li>
                                <li class="level1-nav1-2"></li>
                            </ul>
                        </li>
                    </ol>
                </div>
                <div class="header-buy-ticket">
                    <p>
                        <a class="ticket" href="#">
                            <img alt="ticket" src="./assets/images/hd-tickets-49018.png" />
                        </a>
                    </p>
                </div>
            </div>
        </header>
    </body>
</html>

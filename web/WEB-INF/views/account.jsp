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
                            <div class="box">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>

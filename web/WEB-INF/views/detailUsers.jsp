<%-- 
    Document   : detailUsers
    Created on : Mar 13, 2024, 11:09:44 PM
    Author     : bquoc
--%>
<%-- 
    Document   : updateAccount
    Created on : Mar 1, 2024, 2:51:48 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

    </style>
    <body>
        <%@include file="header.jsp" %>
        <form action="update" method="post">
            <div class="container">
                <div class="my-account">
                    <div class="left-content">
                        <h1 class="block-title" style="color:red">TÀI KHOẢN</h1>
                        <div class="block-cotent">
                            <ul style="padding:0">
                                <li style="background-color:${backgroundColorSecond}">
                                    <a href="account" style="color: ${colorSecond}"
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
                                    <li style="background-color:${backgroundColorMain}">
                                        <a style="color: ${colorMain}"  href="detailUsers">
                                            QUẢN LÝ NGƯỜI DÙNG
                                        </a>
                                    </li>
                                </c:if>
                            </ul>

                        </div>
                    </div>
                    <div class="dashboard">
                        <div class="page-title">
                            <h1>QUẢN LÝ NGƯỜI DÙNG</h1>
                        </div>
                        <h6 id="error" style="color: red">${requestScope.error}</h6>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>UserID</th>
                                    <th>Tên người dùng</th>
                                    <th>Tên chức danh</th>
                                    <th>Số lượng vé đã mua</th>
                                    <th>Số tiền</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="user" items="${listTotalTicketOfUser}">
                                    <tr>
                                        <td>${user.getUserID()}</td>
                                        <td>${user.getUsername()}</td>
                                        <td>${user.getName()}</td>
                                        <td>${user.getTotalTickets()}</td>
                                        <td>${user.getTotalTickets()*75000 } đồng</td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
            </div>

        </form>
        <%@include file="footer.jsp" %>

    </body>
</html>


<%-- 
    Document   : historyPayment
    Created on : Mar 10, 2024, 9:30:47 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        body{
            background:#eee;
        }
        .card {
            box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            width: 1110px;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid rgba(0,0,0,.125);
            border-radius: 1rem !important;
        }

        .text-reset {
            --bs-text-opacity: 1;
            color: inherit!important;
        }
        a {
            color: #5465ff;
            text-decoration: none;
        }
        .item p span {
            font-weight: bold; /* Làm đậm cho các từ "Rạp", "Suất chiếu", "Phòng Cinema", "Ghế" */
            color: black; /* Đổi màu chữ */
        }
        .pagination-wrapper {
            border-radius: 5px;
            overflow: hidden;
        }


    </style>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="my-account">
                <div class="left-content" style="min-width:20%">
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
                            <li style="background-color:${backgroundColorMain}">
                                <a style="color: ${colorMain}"  href="historyPayment?userID=${sessionScope.account.getUserID()}">
                                    LỊCH SỬ GIAO DỊCH
                                </a>
                            </li>
                            <c:if test="${sessionScope.account.role.name.equals('admin')}">
                                <li style="background-color:${backgroundColorSecond}">
                                    <a style="color: ${colorSecond}"  href="addNewMovie">
                                        THÊM PHIM MỚI
                                    </a>
                                </li>
                            </c:if>
                        </ul>

                    </div>
                </div>
                <div class="dashboard">
                    <div class="container-fluid">
                        <c:choose>
                            <c:when test="${listTickets.isEmpty()}">
                                <h1>You don't have any tickets</h1>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${listTickets}" var="ticket">
                                    <div class="container" style="margin-top:0px; padding-bottom: 0px">
                                        <!-- Main content -->
                                        <div class="row" style="width:810px">
                                            <div class="card">
                                                <div class="card-body">

                                                    <table class="table table-borderless">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <div class="d-flex mb-2">
                                                                        <div class="flex-shrink-0" style="margin-right:20px; box-shadow: 0 20px 27px 0 rgb(0 0 0 / 34%);height:109px">
                                                                            <img src="./assets/images/posterImages/${ticket.getMovieID().getPosterImage()}" alt="" width="74px" height="108px" class="img-fluid">
                                                                        </div>
                                                                        <div class="flex-lg-grow-1 ms-3">
                                                                            <h4 class="mb-0">${ticket.getMovieID().getTitle().toUpperCase()}</h4>
                                                                            <ul>
                                                                                <li class="item" style="width: 210px; word-break: break-word;margin-right: 0;width: 300px">
                                                                                    <p><span>Rạp:</span> ${ticket.getCinemaID().getName()}</p>
                                                                                    <p><span>Suất chiếu:</span> <fmt:formatDate value="${ticket.getSeatID().getScreeningID().getStartTime()}" pattern="HH:mm"/>, <fmt:formatDate value="${ticket.getSeatID().getScreeningID().getStartTime()}" pattern="yyyy-MM-dd" /></p>
                                                                                <p><span>Phòng Cinema: </span>Cinemas ${ticket.getSeatID().getScreeningID().getTheaterID().getTheaterNumber()}</p>
                                                                                <p><span>Ghế:</span> <span id="selectedSeats">${ticket.getSeatID().getSeatNumber()}</span></p>
                                                                                </li>
                                                                            </ul>

                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td style="font-size: 20px; font-weight: bold">1</td>
                                                                <td class="text-end" style="font-size: 20px; font-weight: bold">75000 đồng</td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <nav aria-label="...">
                        <div class="pagination-wrapper">
                            <ul class="pagination" style="margin-right:15px; margin-top: 20px; justify-content: end;">
                                <li class="page-item ${tag == 1 ? "disabled" : ""}">
                                    <a class="page-link" href="historyPayment?userID=${account.getUserID()}&index=${tag - 1}" >Previous</a>
                                </li>
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <li class="page-item ${tag == i ? "active" : ""}"><a class="page-link" href="historyPayment?userID=${account.getUserID()}&index=${i}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${tag == endPage ? "disabled" : ""}">
                                    <a class="page-link" href="historyPayment?userID=${account.getUserID()}&index=${tag + 1}">Next</a>
                                </li>
                            </ul>
                        </div>
                    </nav>


                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>

    </body>
</html>

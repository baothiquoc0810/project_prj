<%-- 
    Document   : paymentDetail
    Created on : Mar 9, 2024, 5:01:47 PM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    </style>
    <%@include file="header.jsp" %>

    <body>
        <div class="container-fluid">

            <div class="container">
                <!-- Main content -->
                <div class="row">
                    <div class="card">
                        <div class="card-body">

                            <table class="table table-borderless">
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="d-flex mb-2">
                                                <div class="flex-shrink-0" style="margin-right:20px; box-shadow: 0 20px 27px 0 rgb(0 0 0 / 34%);height:109px">
                                                    <img src="./assets/images/posterImages/${screeningTime.getMovieID().getPosterImage()}" alt="" width="74px" height="108px" class="img-fluid">
                                                </div>
                                                <div class="flex-lg-grow-1 ms-3">
                                                    <h4 class="mb-0">${screeningTime.getMovieID().getTitle().toUpperCase()}</h4>
                                                    <ul>
                                                        <li class="item" style="width: 210px; word-break: break-word;margin-right: 0;width: 300px">
                                                            <p><span>Rạp:</span> ${screeningTime.getTheaterID().getCinemaID().getName()}</p>
                                                            <p><span>Suất chiếu:</span> <fmt:formatDate value="${screeningTime.getStartTime()}" pattern="HH:mm"/>, <fmt:formatDate value="${screeningTime.getStartTime()}" pattern="yyyy-MM-dd" /></p>
                                                            <p><span>Phòng Cinema: </span>Cinemas ${screeningTime.getTheaterID().getTheaterNumber()}</p>
                                                            <p><span>Ghế:</span> <span id="selectedSeats">${selectedSeats}</span></p>
                                                        </li>
                                                    </ul>

                                                </div>
                                            </div>
                                        </td>
                                        <td style="font-size: 20px; font-weight: bold">${count}</td>
                                        <td class="text-end" style="font-size: 20px; font-weight: bold">75000 đồng</td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr class="fw-bold">
                                        <td colspan="2" style="font-size: 20px; font-weight: bold">TOTAL</td>
                                        <td class="text-end" style="font-size: 20px; font-weight: bold">${totalPrice} đồng</td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@include file="footer.jsp" %>

</html>

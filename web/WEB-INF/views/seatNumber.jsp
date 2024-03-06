<%-- 
    Document   : seatNumber
    Created on : Mar 6, 2024, 3:39:52 PM
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
    <body>
        <%@include file="header.jsp" %>
        <div class="main-container-seat-booking">
            <div class="container">
                <div class="booking-progress">
                    <div class="page-title">
                        <h3 >BOOKING ONLINE</h3>
                    </div>
                    <div class="top-content">
                        <div class="products-list">
                            <c:set var="count" value="0" />
                            <c:forEach items="${seats}" var="seat">
                                <c:choose>
                                    <c:when test="${seat.getSeatType() == 'Chưa đặt'}">
                                        <c:set var="count" value="${count + 1}" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <p>${seats[0].getScreeningID().getTheaterID().getCinemaID().getName()} | Cinema ${seats[0].getScreeningID().getTheaterID().getTheaterNumber()} | Số ghế (${100 - count}/${count})</p>
                            <p><fmt:formatDate value="${seats[0].getScreeningID().getStartTime()}" pattern="yyyy:MM:dd HH:mm" /> ~ <fmt:formatDate value="${seats[0].getScreeningID().getEndTime()}" pattern="yyyy:MM:dd HH:mm" /></p>
                        </div>
                    </div>
                    <div class="main-content">
                        <div class="progress-content" style="position:relative;">
                            <div class="ticketbox">
                                <div class="screen"></div>
                                <div class="row">
                                    <form style="width:97%" id="seatForm" action="seatNumber">
                                        <div class="seats-container">

                                            <c:forEach var="row" items="${'ABCDEFGHIJ'.split('')}">
                                                <c:forEach var="col" begin="1" end="10">
                                                    <c:set var="seatCode" value="${row}${String.format('%02d', col)}" />
                                                    <input type="hidden" name="selectedSeats" id="${seatCode}" value="false">
                                                    <c:if test="${listSeatID.contains(seatCode)}">
                                                        <label class="seat seat-occupied active" loc="${seatCode}" price="75000">${seatCode}</label>
                                                    </c:if>
                                                    <c:if test="${!listSeatID.contains(seatCode)}">
                                                        <label class="seat seat-standard" onclick="toggleSeat(this)" loc="${seatCode}" price="75000">${seatCode}</label>
                                                    </c:if>
                                                </c:forEach>
                                                <br>
                                            </c:forEach>
                                        </div>
                                        <br>
                                        <div class="ticketbox-notice">
                                            <div class="iconlist">
                                                <div class="icon checked">Checked</div>
                                            </div>
                                            <div class="iconlist">
                                                <div class="icon occupied">Đã chọn</div>
                                            </div>
                                        </div>

                                        <div class="bottom-content">
                                            <div class="minicart-wrapper">
                                                <ul>
                                                    <li class="item-first">
                                                        <div class="product-detail">
                                                            <img src="https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/thumbnail/dc33889b0f8b5da88052ef70de32f1cb/p/o/poster_dune_2_bb_3_no_qr_1_.jpg" width="74px" height="108px" alt="alt"/>
                                                            <p>DUNE: HÀNH TINH CÁT - PHẦN HAI</p>
                                                        </div>
                                                    </li>
                                                    <li class="item" style="width: 210px; word-break: break-word;margin-right: 0;width: 300px">
                                                        <p>Rạp ${seats[0].getScreeningID().getTheaterID().getCinemaID().getName()}</p>
                                                        <p>Suất chiếu <fmt:formatDate value="${seats[0].getScreeningID().getStartTime()}" pattern="HH:mm"/>, <fmt:formatDate value="${seats[0].getScreeningID().getStartTime()}" pattern="yyyy:MM:dd" /></p>
                                                        <p>Phòng Cinema ${seats[0].getScreeningID().getTheaterID().getTheaterNumber()}</p>
                                                        <p>Ghế: <span id="selectedSeats"></span></p>
                                                    </li>
                                                    <li class="item">
                                                        <p>Tổng tiền: <span id="totalPrice">0</span></p>
                                                    </li>
                                                </ul> 
                                                <input style="font-weight: bold; font-size: 20px" type="submit" value="ĐẶT VÉ">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script>
                        function toggleSeat(seat) {
                            var checkbox = document.getElementById(seat.getAttribute('loc'));
                            checkbox.value = checkbox.value === 'true' ? 'false' : 'true';
                            seat.classList.toggle('seat-selected');

                            // Hiển thị ghế đã chọn
                            var selectedSeats = document.getElementById("selectedSeats");
                            var selectedSeatCodes = document.querySelectorAll("input[name='selectedSeats'][value='true']");
                            var seatCodesString = "";
                            selectedSeatCodes.forEach(function (seatCode) {
                                seatCodesString += seatCode.id + ", ";
                            });
                            seatCodesString = seatCodesString.slice(0, -2); // Loại bỏ dấu ', ' ở cuối
                            selectedSeats.innerHTML = seatCodesString;

                            // Tính tổng tiền
                            var totalPrice = document.getElementById("totalPrice");
                            var selectedSeatsCount = selectedSeatCodes.length;
                            var pricePerSeat = 75000; // Giá tiền cho mỗi ghế
                            var total = selectedSeatsCount * pricePerSeat;
                            totalPrice.innerHTML = formatMoney(total);

                            // Hàm định dạng số tiền
                            function formatMoney(amount) {
                                return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
                            }
                        }
                    </script>

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>

</html>

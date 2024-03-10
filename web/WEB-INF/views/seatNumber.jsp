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
                            <c:set var="count" value="${SWS.size()}" />

                            <p>${screeningTimes.getTheaterID().getCinemaID().getName()} | Phòng Cinema ${screeningTimes.getTheaterID().getTheaterNumber()} | Số ghế (${100 - count}/100)</p>
                            <p><fmt:formatDate value="${screeningTimes.getStartTime()}" pattern="yyyy:MM:dd HH:mm" /> ~ <fmt:formatDate value="${screeningTimes.getEndTime()}" pattern="yyyy-MM-dd HH:mm" /></p>
                        </div>
                    </div>
                    <div class="main-content">
                        <div class="progress-content" style="position:relative;">
                            <div class="ticketbox">
                                <div class="screen"></div>
                                <div class="row">
                                    <form style="width:97%" id="seatForm" action="payment" onsubmit="return validateForm()">
                                        <div class="seats-container">

                                            <c:forEach var="row" items="${'ABCDEFGHIJ'.split('')}">
                                                <c:forEach var="col" begin="1" end="10">
                                                    <c:set var="seatCode" value="${row}${String.format('%02d', col)}" />
                                                    <c:set var="seatFound" value="false" />
                                                    <c:forEach var="s" items="${SWS}">
                                                        <c:if test="${s.getSeatID().getSeatNumber().equals(seatCode)}">
                                                            <label class="seat seat-occupied active" loc="${seatCode}" price="75000">${seatCode}</label>
                                                            <c:set var="seatFound" value="true" />
                                                        </c:if>
                                                        <c:if test="${seatFound}">
                                                            <c:out value="" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${!seatFound}">
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
                                                            <img src="./assets/images/posterImages/${screeningTimes.getMovieID().getPosterImage()}" width="74px" height="108px" alt="alt"/>
                                                            <p>${screeningTimes.getMovieID().getTitle().toUpperCase()}</p>
                                                        </div>
                                                    </li>
                                                    <li class="item" style="width: 210px; word-break: break-word;margin-right: 0;width: 300px">
                                                        <p>Rạp ${screeningTimes.getTheaterID().getCinemaID().getName()}</p>
                                                        <p>Suất chiếu <fmt:formatDate value="${screeningTimes.getStartTime()}" pattern="HH:mm"/>, <fmt:formatDate value="${seats[0].getScreeningID().getStartTime()}" pattern="yyyy-MM-dd" /></p>
                                                        <p>Phòng Cinema ${screeningTimes.getTheaterID().getTheaterNumber()}</p>
                                                        <p>Ghế: <span id="selectedSeats"></span></p>
                                                    </li>
                                                    <li class="item">
                                                        <p>Tổng tiền: <span id="totalPrice">0</span></p>
                                                    </li>
                                                </ul> 
                                                <!--<input type="hidden" name=""-->
                                                <input type="hidden" name="selectedSeats" id="hiddenSelectedSeats">
                                                <input type="hidden" name="totalPrice" id="hiddenTotalPrice">
                                                <input type="hidden" name="screeningID" value="${screeningTimes.getScreeningID()}">

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
                            seat.classList.toggle('seat-selected');

                            // Get all selected seats
                            var selectedSeats = document.querySelectorAll('.seat-selected');

                            // Create an array to store the seat codes
                            var seatCodes = [];

                            // Loop through the selected seats and add their codes to the array
                            selectedSeats.forEach(function (seat) {
                                seatCodes.push(seat.getAttribute('loc'));
                            });

                            // Join the seat codes with a comma and set the value of the hidden input field
                            document.getElementById('hiddenSelectedSeats').value = seatCodes.join(', ');

                            document.getElementById('selectedSeats').textContent = seatCodes.join(', ');

                            // Calculate the total price
                            var totalPrice = document.getElementById("totalPrice");
                            var hiddenTotalPrice = document.getElementById("hiddenTotalPrice");
                            var selectedSeatsCount = seatCodes.length;
                            var pricePerSeat = 75000; // Price per seat
                            var total = selectedSeatsCount * pricePerSeat;
                            totalPrice.innerHTML = formatMoney(total);
                            hiddenTotalPrice.value = total; // Set the value of the hidden input field

                            // Function to format money
                            function formatMoney(amount) {
                                return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
                            }
                        }
                        function validateForm() {
                            var hiddenSelectedSeats = document.getElementById('hiddenSelectedSeats');
                            if (hiddenSelectedSeats.value === '') {
                                alert('Please choose your seats first.');
                                return false;
                            } else {
                                return confirm('Do you agree to book this seat?');
                            }
                        }
                    </script>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>

</html>

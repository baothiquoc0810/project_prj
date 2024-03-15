<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Calendar</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f0f0f0;
            }
            .calendar {
                display: grid;
                grid-template-columns: repeat(7, 1fr);
                gap: 5px;
            }
            .day {
                padding: 10px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .day:hover {
                background-color: #f0f0f0;
            }
            .today {
                /* font-weight: bold; */
                background-color: #ffcccc; /* Màu nền của ngày hôm nay */
            }
            .active {
                font-weight: bold;
                background-color: #cdd2d4 !important;
            }

            .selected {
                background-color: red !important;
            }


        </style>
    </head>

    <body>

        <%
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int today = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        java.util.List<Integer> daysOfMonth = new java.util.ArrayList<>();
        java.util.List<String> dayOfWeeks = new java.util.ArrayList<>();
        java.util.Date date = new java.util.Date();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        for (int i = 0; i < 10; i++) {
            int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
            daysOfMonth.add(day);
            int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
            String dayOfWeekName = "";
            switch (dayOfWeek) {
                case java.util.Calendar.SUNDAY:
                    dayOfWeekName = "Chủ nhật";
                    break;
                case java.util.Calendar.MONDAY:
                    dayOfWeekName = "Thứ hai";
                    break;
                case java.util.Calendar.TUESDAY:
                    dayOfWeekName = "Thứ ba";
                    break;
                case java.util.Calendar.WEDNESDAY:
                    dayOfWeekName = "Thứ tư";
                    break;
                case java.util.Calendar.THURSDAY:
                    dayOfWeekName = "Thứ năm";
                    break;
                case java.util.Calendar.FRIDAY:
                    dayOfWeekName = "Thứ sáu";
                    break;
                case java.util.Calendar.SATURDAY:
                    dayOfWeekName = "Thứ bảy";
                    break;
            }
            dayOfWeeks.add(dayOfWeekName);
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
        }
        %>
        <%@include file="header.jsp" %>
        <div class="container">

            <div class="date-movie" style="width:100%">
                <h2 id="calendarTitle">Lịch chiếu phim của các ngày tiếp theo</h2>
                <div class="calendar" id="calendar">
                    <% for (int i = 0; i < 5; i++) { %>
                    <input type="hidden" name="mainDate" value="<%= String.format("%tF", cal.getTime()) %>">
                    <a href="booking?movieid=${movieID}&date=<%= daysOfMonth.get(i) %>" class="day-link">
                        <div class="day <%= (Integer.parseInt(String.valueOf(request.getAttribute("date"))) == daysOfMonth.get(i) ? "active" : "") %>">
                            <div class="date"><%= daysOfMonth.get(i) %></div>
                            <div class="day-of-week"><%= dayOfWeeks.get(i) %></div>
                        </div>
                    </a>


                    <% } %>
                </div>
            </div>
            <div class="address">
                <ul style="display:flex; padding:0 ">
                    <div>
                        <% String direction1 = "2"; %>
                        <li class="direction <%= (direction1.equals(request.getParameter("direction")) ? "active" : "") %>">
                            <span><a href="booking?movieid=${movieID}&date=<%= request.getParameter("date") %>&direction=2">Hồ Chí Minh</a></span>
                        </li>
                    </div>
                    <div>
                        <% String direction2 = "1"; %>
                        <li class="direction <%= (direction2.equals(request.getParameter("direction")) ? "active" : "") %>">
                            <span><a href="booking?movieid=${movieID}&date=<%= request.getParameter("date") %>&direction=1">Hà Nội</a></span>
                        </li>
                    </div>
                    <div>
                        <% String direction3 = "3"; %>
                        <li class="direction <%= (direction3.equals(request.getParameter("direction")) ? "active" : "") %>">
                            <span><a href="booking?movieid=${movieID}&date=<%= request.getParameter("date") %>&direction=3">Đà Nẵng</a></span>
                        </li>
                    </div>

                </ul>
            </div>
            <div class="tabcontent showtimes">
                <c:choose>
                    <c:when test="${empty cinemaScreeningTimes}">
                        <h1 style="color:red">Rất tiếc không có suất chiếu nào!</h1>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${cinemaScreeningTimes}" var="cinemaEntry">
                            <div class="site">
                                <div class="title">
                                    <h3>${cinemaEntry.key}</h3>
                                </div>
                                <ul class="products-grid-movie">
                                    <c:forEach items="${cinemaEntry.value}" var="screeningTimesList">
                                        <c:forEach items="${screeningTimesList}" var="screeningTime">
                                            <li class="item">
                                                <a href="seatNumber?screeningID=${screeningTime.getScreeningID()}">
                                                    <span><fmt:formatDate value="${screeningTime.getStartTime()}" pattern="HH:mm" /></span>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>


        </div>


        <%@include file="footer.jsp" %>

    </body>
</html>

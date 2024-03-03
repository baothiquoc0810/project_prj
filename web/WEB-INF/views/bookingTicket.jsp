<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    .month-selector {
        margin-bottom: 20px;
    }
    .today {
        font-weight: bold;
        background-color: #ffcccc; /* Màu nền của ngày hôm nay */
    }
</style>
</head>
<body>

<%
java.util.Calendar calendar = java.util.Calendar.getInstance();
int month = calendar.get(java.util.Calendar.MONTH) + 1;
int year = calendar.get(java.util.Calendar.YEAR);
int today = calendar.get(java.util.Calendar.DAY_OF_MONTH);

java.util.List<Integer> daysOfMonth = new java.util.ArrayList<>();
java.util.Calendar cal = java.util.Calendar.getInstance();
cal.set(year, month - 1, 1);
int lastDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
for (int i = 1; i <= lastDay; i++) {
    daysOfMonth.add(i);
}
%>

<div class="month-selector">
    <label for="month">Chọn tháng:</label>
    <select name="month" id="month" onchange="showDays()">
        <option value="1" <%= (month == 1 ? "selected" : "") %>>Tháng 1</option>
        <option value="2" <%= (month == 2 ? "selected" : "") %>>Tháng 2</option>
        <option value="3" <%= (month == 3 ? "selected" : "") %>>Tháng 3</option>
        <option value="4" <%= (month == 4 ? "selected" : "") %>>Tháng 4</option>
        <option value="5" <%= (month == 5 ? "selected" : "") %>>Tháng 5</option>
        <option value="6" <%= (month == 6 ? "selected" : "") %>>Tháng 6</option>
        <option value="7" <%= (month == 7 ? "selected" : "") %>>Tháng 7</option>
        <option value="8" <%= (month == 8 ? "selected" : "") %>>Tháng 8</option>
        <option value="9" <%= (month == 9 ? "selected" : "") %>>Tháng 9</option>
        <option value="10" <%= (month == 10 ? "selected" : "") %>>Tháng 10</option>
        <option value="11" <%= (month == 11 ? "selected" : "") %>>Tháng 11</option>
        <option value="12" <%= (month == 12 ? "selected" : "") %>>Tháng 12</option>
    </select>
</div>

<h2 id="calendarTitle">Lịch tháng <%= month %> năm <%= year %>:</h2>

<div class="calendar" id="calendar">
<% for (Integer day : daysOfMonth) { %>
    <div class="day" id="day<%= day %>">
        <%= day %>
        <div class="day-of-week"></div>
    </div>
<% } %>
</div>

<script>
    function showDays() {
        var monthSelect = document.getElementById("month");
        var selectedMonth = monthSelect.value;
        var year = <%= year %>;
        var today = <%= today %>;

        var cal = new Date(year, selectedMonth - 1, 1);
        var lastDay = new Date(cal.getFullYear(), cal.getMonth() + 1, 0).getDate();

        document.getElementById("calendarTitle").innerHTML = "Lịch tháng " + selectedMonth + " năm " + year + ":";
        var calendarDiv = document.getElementById("calendar");
        calendarDiv.innerHTML = ""; // Xóa các ngày cũ trước khi thêm ngày mới
        for (var i = 1; i <= lastDay; i++) {
            var dayDiv = document.createElement("div");
            dayDiv.classList.add("day");
            if (i == today && selectedMonth == <%= month %>) {
                dayDiv.classList.add("today");
            }
            dayDiv.id = "day" + i;
            dayDiv.innerHTML = i;
            var dayOfWeekDiv = document.createElement("div");
            dayOfWeekDiv.classList.add("day-of-week");
            dayOfWeekDiv.innerHTML = getDayOfWeek(year, selectedMonth - 1, i);
            dayDiv.appendChild(dayOfWeekDiv);
            calendarDiv.appendChild(dayDiv);
        }
    }

    function getDayOfWeek(year, month, day) {
        var dayOfWeekNames = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
        var date = new Date(year, month, day);
        var dayOfWeek = date.getDay();
        return dayOfWeekNames[dayOfWeek];
    }

    // Hiển thị thứ của ngày đầu tiên khi trang được tải
    window.onload = function() {
        var monthSelect = document.getElementById("month");
        var selectedMonth = monthSelect.value;
        var year = <%= year %>;
        var cal = new Date(year, selectedMonth - 1, 1);
        var firstDayOfWeek = getDayOfWeek(year, selectedMonth - 1, 1);
        document.getElementsByClassName("day-of-week")[0].innerHTML = firstDayOfWeek;
        showDays();
    }
</script>

</body>
</html>

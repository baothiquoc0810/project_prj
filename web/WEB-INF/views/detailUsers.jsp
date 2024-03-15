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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
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
        .chartjs-render-monitor{
            width:120% !important;
            height:500px !important;
        }

    </style>
    <body>
        <%@include file="header.jsp" %>
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
                                <form action="detailUsers" style="width: 100%; padding: 10px; margin-top: 10px; margin-bottom: 10px">
                                    <select style="width: 107%; height:40px; margin-left:-10px" name="sortID" onchange="this.form.submit()">
                                        <option ${sort eq '' ? 'selected': ''} value="">Lựa chọn</option>
                                        <option ${sort eq 'ORDER BY u.userID ASC' ? 'selected' : ''} value="ORDER BY u.userID ASC">UserID tăng dần</option>
                                        <option ${sort eq 'ORDER BY u.userID DESC' ? 'selected' : ''} value="ORDER BY u.userID DESC">UserID giảm dần</option>
                                        <option ${sort eq 'ORDER BY u.username ASC' ? 'selected' : ''} value="ORDER BY u.username ASC">Tên người dùng A-Z</option>
                                        <option ${sort eq 'ORDER BY u.username DESC' ? 'selected' : ''} value="ORDER BY u.username DESC">Tên người dùng Z-A</option>
                                        <option ${sort eq 'ORDER BY totalTickets desc' ? 'selected' : ''} value="ORDER BY totalTickets desc">Người mua vé nhiều-ít</option>
                                        <option ${sort eq 'ORDER BY totalTickets' ? 'selected' : ''} value="ORDER BY totalTickets">Người mua vé ít-nhiều</option>

                                    </select>
                                </form>

                            </c:if>
                        </ul>

                    </div>
                </div>
                <div style="display:flex; flex-direction: column; width: 80%;">
                    <div class="dashboard">
                        <div class="page-title">
                            <h1>QUẢN LÝ NGƯỜI DÙNG</h1>
                        </div>
                        <h6 id="error" style="color: red">${error}</h6>

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
                    <div>
                        <canvas id="myChart" style="margin-left:-170px"></canvas>

                        <script>
                            // Lấy dữ liệu từ servlet và gán vào biến JavaScript
                            const movieAndTotalOrders = JSON.parse('${chart}');// Đảm bảo rằng 'chart' là tên bạn đã đặt cho thuộc tính trong servlet
                            const xValues = [];
                            const yValues = [];

                            // Lặp qua dữ liệu từ servlet và gán vào biến JavaScript
                            movieAndTotalOrders.forEach(function (item) {
                                xValues.push(item.title); // Giả sử movieName là tên của phim
                                yValues.push(item.totalOrders); // Giả sử totalOrders là số lượng đơn hàng
                            });

                            // Tạo biểu đồ
                            new Chart("myChart", {
                                type: "line",
                                data: {
                                    labels: xValues,
                                    datasets: [{
                                            fill: false,
                                            lineTension: 0,
                                            backgroundColor: "rgba(0,0,255,1.0)",
                                            borderColor: "rgba(0,0,255,1)",
                                            data: yValues
                                        }]
                                },
                                options: {
                                    legend: {display: false},
                                    scales: {
                                        yAxes: [{ticks: {min: 0, max: 60}}],
                                    }
                                }
                            });
                        </script>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>


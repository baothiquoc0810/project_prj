<%-- 
    Document   : addNewMovie
    Created on : Mar 11, 2024, 12:49:30 AM
    Author     : bquoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .add-new-movie {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            margin:0 auto;
            text-align: center;
            color: #333;
            font-weight: bold !important;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            font-size: 20px;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="date"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="file"] {
            width: 32%;
            padding: 10px;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>
    <%@include file="header.jsp" %>

    <body>
        <div class="container" style="flex-direction: row">
            <div class="left-content" style="min-width:20%">
                <h1 class="block-title" style="color:red">TÀI KHOẢN</h1>
                <div class="block-cotent">
                    <ul style="padding:0">
                        <li style="background-color:${backgroundColorSecond}; font-size: 20px">
                            <a href="account" style="color: ${colorSecond}"
                               >
                                THÔNG TIN CHUNG
                            </a>
                        </li>
                        <li style="background-color:${backgroundColorSecond}; font-size: 20px">
                            <a style="color: ${colorSecond}" href="update">
                                CHI TIẾT TÀI KHOẢN
                            </a>
                        </li>
                        <li style="background-color:${backgroundColorSecond}; font-size: 20px">
                            <a style="color: ${colorSecond}"  href="historyPayment?userID=${sessionScope.account.getUserID()}">
                                LỊCH SỬ GIAO DỊCH
                            </a>
                        </li>
                        <c:if test="${sessionScope.account.role.name.equals('admin')}">
                            <li style="background-color:${backgroundColorMain}; font-size: 20px">
                                <a style="color: ${colorMain}"  href="addNewMovie">
                                    THÊM PHIM MỚI
                                </a>
                            </li>
                        </c:if>
                    </ul>

                </div>
            </div>
            <div class="add-new-movie" style="width:870px">
                <h2>Add New Movie</h2>
                <form style="width:100%" action="uploadServlet" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="movieTitle">Title:</label>
                        <input type="text" id="movieTitle" name="movieTitle" required />
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="releaseDate">Release Date:</label>
                        <input type="date" id="releaseDate" name="releaseDate" required />
                    </div>
                    <div class="form-group">
                        <label style="display:block;" for="posterImage">Poster Image:</label>
                        <input style="background-color: #d1cab8" type="file" id="posterImage" name="posterImage" accept="image/*" required />
                    </div>
                    <div class="form-group">
                        <label for="duration">Duration (minutes):</label>
                        <input type="number" id="duration" name="duration" required>
                    </div>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>

</html>

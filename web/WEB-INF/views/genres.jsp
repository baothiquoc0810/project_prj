<%-- 
    Document   : genres
    Created on : Mar 13, 2024, 10:20:45 PM
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
        <%@include file="header.jsp" %>

    <body>
        <div class="page" >
            <div class="main-container">
                <div class="main">
                    <div class="home-movie-selection">
                        <div>
                            <h1 style="padding-bottom: 20px; border-bottom: 2px solid black;">Phim ${genres}</h1>
                        </div>
                    </div>
                    <div class="tab-content">
                        <ul class="curr-list">
                            <% int movieNumber = 1; %>
                            <c:forEach items="${movies}" var="movie">
                                <li>
                                    <div class="curr-box">
                                        <span class="num"><%= movieNumber++ %></span>
                                        <span class="img">
                                            <a href="detail-movie?movieID=${movie.movieID}">
                                                <img src="./assets/images/posterImages/${movie.posterImage}" />
                                            </a>
                                        </span>
                                    </div>
                                    <dl class="list-text">
                                        <dt>
                                            <a href="detail-movie?movieID=">

                                                <span>${movie.title.toUpperCase()}</span>
                                            </a>
                                        </dt>
                                        <dd>
                                            <span class="rate">${movie.duration} phút</span>
                                            <span class="grade">
                                                <em>${movie.releaseDate}</em>
                                            </span>
                                        </dd>
                                    </dl>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <!--Footer-->
            <%@include file="footer.jsp" %>
        </div>

    </body>
</html>

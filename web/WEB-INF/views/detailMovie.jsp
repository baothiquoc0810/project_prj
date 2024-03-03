<%-- 
    Document   : detailMovie
    Created on : Mar 3, 2024, 1:43:10 PM
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
    <body>
        <%@include file="header.jsp" %>
        <div class="container" style="width:980px">
            <div class="row">
                <div class="col-3">
                    <div class="poster-image" style="" >
                        <img style="width: 181px; height:259px; margin-bottom: 10px;border-radius: 8px" src="./assets/images/posterImages/${movie.posterImage}" alt="alt"/>
                    </div>
                </div>
                <div class="col-7">
                    <div class="product-name">
                        <h4 style="font-weight: bold">${movie.title.toUpperCase()}</h4>
                    </div>
                    <div>
                        <div class="product-detail">Thể Loại: 
                            <c:forEach items="${listGenres}" var="genres" varStatus="status">
                                <span class="detail-movie" style="">
                                    <c:out value="${genres.genreID.getName()}"/>
                                    <c:if test="${!status.last}">, </c:if>
                                    </span> 
                            </c:forEach>
                        </div>
                        <div class="product-detail"> Khởi Chiếu: <span class="detail-movie">${movie.getReleaseDate()}</span></div>
                        <div class="product-detail">Thời Lượng: <span class="detail-movie">${movie.duration} Phút</span></div>
                    </div>
                    <button><a style="text-decoration: none; color:#fff" href="booking">Mua Vé</a></button>
                </div>
            </div>
            <div class="description" style="width: 900px; margin-top: 40px">
                <p>${movie.description}</p>
            </div>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>

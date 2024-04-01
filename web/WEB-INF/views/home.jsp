
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <!-- load local css in assets folder -->

        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"
        ></script>
    </head>
    <%@include file="header.jsp" %>
    <body>
        <div class="page" >
            <div class="main-container">
                <div class="main">
                    <!-- banner -->
                    <div
                        id="carouselExampleSlidesOnly"
                        class="carousel slide"
                        data-ride="carousel"
                        data-interval="4000"
                        >
                        <div class="carousel-inner" style="height:551px">
                            <div class="carousel-item active">
                                <img
                                    class="d-block"
                                    src="https://images.bauerhosting.com/empire/2023/08/empoct23-dune-part-two-crop-cover.jpg?ar=16%3A9&fit=crop&crop=top&auto=format&w=undefined&q=80"
                                    alt="First slide"
                                    />
                            </div>
                            <div class="carousel-item">
                                <img
                                    class="d-block"
                                    src="https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/6/4/640x396-q-m.jpg"
                                    alt="Second slide"
                                    />
                            </div>

                            <div class="carousel-item">
                                <img
                                    class="d-block"
                                    src="https://daknong.1cdn.vn/thumbs/1200x630/2024/03/08/cdn.galaxycine.vn-media-2024-2-23-_kungfu-panda-4-750_1708659196070.jpg"
                                    alt="Third slide"
                                    />
                            </div>
                        </div>
                    </div>

                    <div class="home-movie-selection">
                        <div class="home-title">
                            <h2>MOVIE SELECTION</h2>
                        </div>
                    </div>
                    <div class="tab-content">
                        <ul class="curr-list">
                            <% int movieNumber = 1; %>
                            <c:forEach items="${listPoster}" var="poster">
                                <c:if test="${account.getRole().getRoleID() != 1}">
                                    <c:if test="${poster.display == 1}">
                                        <li>
                                            <div class="curr-box">
                                                <span class="num"><%= movieNumber++ %></span>
                                                <span class="img">
                                                    <a href="detail-movie?movieID=${poster.movieID}">
                                                        <img src="./assets/images/posterImages/${poster.posterImage}" />
                                                    </a>
                                                </span>
                                            </div>
                                            <dl class="list-text">
                                                <dt>
                                                    <a href="detail-movie?movieID=">
                                                        <span>${poster.title.toUpperCase()}</span>
                                                    </a>
                                                </dt>
                                                <dd>
                                                    <span class="rate">${poster.duration} phút</span>
                                                    <span class="grade">
                                                        <em>${poster.releaseDate}</em>
                                                    </span>
                                                </dd>
                                            </dl>
                                        </li>
                                    </c:if>
                                </c:if>
                                <c:if test="${account.getRole().getRoleID() == 1}">
                                    <li>
                                        <div class="curr-box">
                                            <span class="num"><%= movieNumber++ %></span>
                                            <span class="img">
                                                <a href="detail-movie?movieID=${poster.movieID}">
                                                    <img src="./assets/images/posterImages/${poster.posterImage}" />
                                                </a>
                                            </span>
                                        </div>
                                        <dl class="list-text">
                                            <dt>
                                                <a href="detail-movie?movieID=">
                                                    <span>${poster.title.toUpperCase()}</span>
                                                </a>
                                            </dt>
                                            <dd>
                                                <span class="rate">${poster.duration} phút</span>
                                                <span class="grade">
                                                    <em>${poster.releaseDate}</em>
                                                </span>
                                            </dd>
                                        </dl>
                                    </li>
                                </c:if>
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

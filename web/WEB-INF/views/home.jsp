
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
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img
                                    class="d-block w-100"
                                    src="https://i.ytimg.com/vi/rZYVIK0R6sg/maxresdefault.jpg"
                                    alt="First slide"
                                    />
                            </div>
                            <div class="carousel-item">
                                <img
                                    class="d-block w-100"
                                    src="https://www.cgv.vn/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/1/9/1920x1080_8_.jpg"
                                    alt="Second slide"
                                    />
                            </div>

                            <div class="carousel-item">
                                <img
                                    class="d-block w-100"
                                    src="https://cdn.popsww.com/blog/sites/2/2023/07/doraemon-movie-43.jpg"
                                    alt="Third slide"
                                    />
                            </div>
                        </div>
                    </div>
                    <!--  -->
                    <div class="home-movie-selection">
                        <div class="home-title">
                            <h2>MOVIE SELECTION</h2>
                        </div>
                    </div>
                    <div class="tab-content">
                        <ul class="curr-list">
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                            <li>
                                <div class="curr-box">
                                    <span class="num">1</span>
                                    <span class="img">
                                        <a href="#">
                                            <img
                                                src="https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202312/11310_103_100002.jpg"
                                                />
                                        </a>
                                    </span>
                                </div>
                                <dl class="list-text">
                                    <dt>
                                        <a href="#">
                                            <span>TRÊN BÀN NHẬU DƯỚI BÀN MƯU</span>
                                        </a>
                                    </dt>
                                    <dd>
                                        <span class="rate">116 Phút</span>
                                        <span class="grade">
                                            <em>29/12/2013</em>
                                        </span>
                                    </dd>
                                </dl>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--Footer-->
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>

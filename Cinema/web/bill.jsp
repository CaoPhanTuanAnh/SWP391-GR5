<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style-starter.css">
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <title>Chọn chi nhánh</title>
        <style>
            .img-branch {
                height: 400px;
            }

            .branch-item {
                margin-bottom: 50px;
            }

            .combo-card {
                border: 2px solid transparent;
                transition: all 0.3s ease-in-out;
                position: relative;
            }

            .combo-card:hover {
                border-color: gray;
            }

            .combo-card input {
                display: none;
            }

            .combo-card .radio-label {
                display: block;
                width: 20px;
                height: 20px;
                border: 2px solid gray;
                border-radius: 50%;
                margin: 0 auto;
                cursor: pointer;
            }

            .combo-card input:checked + .radio-label {
                background-color: red;
                border-color: red;
            }

        </style>


    </head>

    <body>
        <!-- nav bar -->
        <header id="site-header" class="w3l-header fixed-top">
            <!--/nav-->
            <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                <div class="container">
                    <h1><a class="navbar-brand" href="HomePage.jsp"><span class="fa fa-play icon-log"
                                                                          aria-hidden="true"></span>
                            MyShowz </a></h1>
                    <!-- if logo is image enable this   
                                            <a class="navbar-brand" href="#index.jsp">
                                                    <img src="image-path" alt="Your logo" title="Your logo" style="height:35px;" />
                                            </a> -->
                    <button class="navbar-toggler collapsed" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <!-- <span class="navbar-toggler-icon"></span> -->
                        <span class="fa icon-expand fa-bars"></span>
                        <span class="fa icon-close fa-times"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="HomePage.jsp">Home</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="movies.jsp">Movies</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="about.jsp">About</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Contact_Us.jsp">Contact</a>
                            </li>
                        </ul>

                        <!--/search-right-->
                        <!--/search-right-->
                        <div class="search-right">
                            <a href="#search" class="btn search-hny mr-lg-3 mt-lg-0 mt-4" title="search">Search <span
                                    class="fa fa-search ml-3" aria-hidden="true"></span></a>
                            <!-- search popup -->
                            <div id="search" class="pop-overlay">
                                <div class="popup">
                                    <form action="#" method="post" class="search-box">
                                        <input type="search" placeholder="Search your Keyword" name="search"
                                               required="required" autofocus="">
                                        <button type="submit" class="btn"><span class="fa fa-search"
                                                                                aria-hidden="true"></span></button>
                                    </form>
                                    <div class="browse-items">
                                        <h3 class="hny-title two mt-md-5 mt-4">Browse all:</h3>
                                        <ul class="search-items">
                                            <li><a href="movies.jsp">Action</a></li>
                                            <li><a href="movies.jsp">Drama</a></li>
                                            <li><a href="movies.jsp">Family</a></li>
                                            <li><a href="movies.jsp">Thriller</a></li>
                                            <li><a href="movies.jsp">Commedy</a></li>
                                            <li><a href="movies.jsp">Romantic</a></li>
                                            <li><a href="movies.jsp">Tv-Series</a></li>
                                            <li><a href="movies.jsp">Horror</a></li>
                                            <li><a href="movies.jsp">Action</a></li>
                                            <li><a href="movies.jsp">Drama</a></li>
                                            <li><a href="movies.jsp">Family</a></li>
                                            <li><a href="movies.jsp">Thriller</a></li>
                                            <li><a href="movies.jsp">Commedy</a></li>
                                            <li><a href="movies.jsp">Romantic</a></li>
                                            <li><a href="movies.jsp">Tv-Series</a></li>
                                            <li><a href="movies.jsp">Horror</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <a class="close" href="#close">×</a>
                            </div>
                            <!-- /search popup -->
                            <!--/search-right-->
                        </div>
                        <div class="Login_SignUp" id="login"
                             style="font-size: 2rem ; display: inline-block; position: relative;">
                            <!-- <li class="nav-item"> -->
                            <a class="nav-link" href="sign_in.jsp"><i class="fa fa-user-circle-o"></i></a>
                            <!-- </li> -->
                        </div>

                    </div>
                    <!-- toggle switch for light and dark theme -->
                    <div class="mobile-position">
                        <nav class="navigation">
                            <div class="theme-switch-wrapper">
                                <label class="theme-switch" for="checkbox">
                                    <input type="checkbox" id="checkbox">
                                    <div class="mode-container">
                                        <i class="gg-sun"></i>
                                        <i class="gg-moon"></i>
                                    </div>
                                </label>
                            </div>
                        </nav>
                    </div>
                </div>
            </nav>
        </header>
        <!-- end of navbar -->

        <!-- movie selections-->
        <br>
        <br>
        <br>

        <div class="container">
            <h2>Thanh toán hóa đơn</h2>
            <br>
            <div style="display:flex">
                <table>
                    <tr>
                        <th><b>Tên Phim: </b></th>
                        <th>${movie.getTitle()}</th>
                    </tr>
                    <tr>
                        <th><b>Tên Chi Nhánh:</b></th>
                        <th>${brand.getTheater_name()}</th>
                    </tr>
                    <tr>
                        <th><b>Giờ Chiếu:</b></th>
                        <th>${startTime}</th>
                    </tr>
                    <tr>
                        <th><b>Ngày Chiếu:</b></th>
                        <th>${startDate}</th>
                    </tr>
                    <tr>
                        <th><b>Tên Phòng:</b></th>
                        <th>${room.getRoom_name()}</th>
                    </tr>
                    <tr>
                        <th><b>Số Vé:</b></th>
                        <th>${selectedSeats ne null ? fn:length(selectedSeats) : 0}</th>
                    </tr>
                    <tr>
                        <th><b>Tiền Vé Đơn:</b></th>
                        <th>65000</th>
                    </tr>
                    <tr>
                        <th><b>Tổng:</b></th>
                        <th>${65000 * fn:length(selectedSeats)}</th>
                    </tr>
                </table>
                <div style="margin-left:50px">
                    <img src="${movie.getPoster_url()}" >
                </div>
                <div style="margin-left:50px; height: 300px;">
                    <img style="height: 100%;" src="${brand.getImg()}" alt="">
                </div>
            </div>
            <br>
            <!--            <form action="ApplyCombo" method="get">
                            <div class="input-group input-group-sm">
                                <input name="coupon" class="form-control" placeholder="Nhập mã giảm giá" type="text" value="${coupon}">
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-theme">Áp dụng</button>
                                </div>
                            </div>
                        </form>-->

            <h3>Chọn Combo</h3>
            <div class="row">
                <c:forEach var="combo" items="${e}">
                    <div class="col-md-4">
                        <div class="card combo-card">
                            <div class="card-header text-center">
                                <input type="radio" name="comboId" value="${combo.getCombo_id()}" 
                                       data-price="${combo.getCombo_price()}" 
                                       id="combo${combo.getCombo_id()}" onclick="updatePaymentLink()">
                                <label for="combo${combo.getCombo_id()}" class="radio-label"></label>
                            </div>
                            <div class="card-body text-center">
                                <h5 class="card-title">${combo.getCombo_name()}</h5>
                                <p class="card-text">${combo.getCombo_price()} VND</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <a id="paymentLink" href="payment-VNPay?amount=${65000 * fn:length(selectedSeats)}
               &date=${startDate}&time=${fn:escapeXml(startTime)}&seat=${fn:join(selectedSeats, ',')}
               &mid=${movie.getMovie_id()}&branch=${brand.getTheater_id()}&room=${room.getRoom_id()}
               &combo=0" class="btn btn-outline-danger btn-block">Thanh Toán</a>

        </div>
        <br>




        <!-- end of movie selections -->
        <br>
        <br>
        <%@ include file="footer.jsp" %>
    </body>

</html>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<!--/theme-change-->
<script src="assets/js/theme-change.js"></script>
<script src="assets/js/owl.carousel.js"></script>
<script>
                    $(document).ready(function () {
                        $('.owl-four').owlCarousel({
                            loop: true,
                            margin: 20,
                            nav: false,
                            responsiveClass: true,
                            autoplay: false,
                            autoplayTimeout: 5000,
                            autoplaySpeed: 1000,
                            autoplayHoverPause: false,
                            responsive: {
                                0: {
                                    items: 1,
                                    nav: false
                                },
                                480: {
                                    items: 2,
                                    nav: true
                                },
                                667: {
                                    items: 2,
                                    nav: true
                                },
                                1000: {
                                    items: 2,
                                    nav: true
                                }
                            }
                        })
                    })
</script>
<script>
    $(document).ready(function () {
        $('.owl-two').owlCarousel({
            loop: true,
            margin: 40,
            nav: false,
            responsiveClass: true,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplaySpeed: 1000,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 1,
                    nav: false
                },
                480: {
                    items: 2,
                    nav: true
                },
                667: {
                    items: 2,
                    margin: 20,
                    nav: true
                },
                1000: {
                    items: 3,
                    nav: true
                }
            }
        })
    })
</script>
<!-- script for owlcarousel -->
<!-- disable body scroll which navbar is in active -->
<script>
    $(function () {
        $('.navbar-toggler').click(function () {
            $('body').toggleClass('noscroll');
        })
    });
</script>
<!-- disable body scroll which navbar is in active -->

<!--/MENU-JS-->
<script>
    $(window).on("scroll", function () {
        var scroll = $(window).scrollTop();

        if (scroll >= 80) {
            $("#site-header").addClass("nav-fixed");
        } else {
            $("#site-header").removeClass("nav-fixed");
        }
    });

    //Main navigation Active Class Add Remove
    $(".navbar-toggler").on("click", function () {
        $("header").toggleClass("active");
    });
    $(document).on("ready", function () {
        if ($(window).width() > 991) {
            $("header").removeClass("active");
        }
        $(window).on("resize", function () {
            if ($(window).width() > 991) {
                $("header").removeClass("active");
            }
        });
    });
</script>
<script src="assets/js/easyResponsiveTabs.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //Horizontal Tab
        $('#parentHorizontalTab').easyResponsiveTabs({
            type: 'default', //Types: default, vertical, accordion
            width: 'auto', //auto or any width like 600px
            fit: true, // 100% fit in a container
            tabidentify: 'hor_1', // The tab groups identifier
            activate: function (event) { // Callback function if tab is switched
                var $tab = $(this);
                var $info = $('#nested-tabInfo');
                var $name = $('span', $info);
                $name.text($tab.text());
                $info.show();
            }
        });
    });
</script>

<script>
    function updatePaymentLink() {
        let selectedCombo = document.querySelector('input[name="comboId"]:checked');
        let comboPrice = selectedCombo ? selectedCombo.getAttribute("data-price") : 0;

        let paymentLink = document.getElementById("paymentLink");
        let baseUrl = "payment-VNPay?amount=${65000 * fn:length(selectedSeats)}" +
                "&date=${startDate}&time=${fn:escapeXml(startTime)}" +
                "&seat=${fn:join(selectedSeats, ',')}&mid=${movie.getMovie_id()}" +
                "&branch=${brand.getTheater_id()}&room=${room.getRoom_id()}";

        paymentLink.href = baseUrl + "&combo=" + comboPrice;
    }
</script>



<script src="assets/js/bootstrap.min.js"></script>
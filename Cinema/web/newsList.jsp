
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zxx">

    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Home</title>

        <link rel="stylesheet" href="assets/css/style-starter.css">
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">
        <style>
            /* CSS cho menu */
            ul {
                list-style-type: none;
                padding: 0;
                margin: 0;
            }

            li a:hover {
                background: #0056b3;
                width: 100%;
            }

            /* CSS cho dropdown menu */
            .dropdown {
                display: none;
                position: absolute;
                top: 65%;
                left: 58vw;
                background: white;
                border: 1px solid #ccc;
                width: 150px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.2);
            }



            /* Hiển thị dropdown khi hover */
            li:hover .dropdown {
                display: block;
            }
            .dropdown li a {
                display: block;
                color: black; /* Màu chữ mặc định */
                background: white;
                padding: 10px;
                transition: color 0.1s ease; /* Hiệu ứng chuyển đổi màu */
            }
            .dropdown li a:hover {
                color: var(--theme-rose); /* Chuyển sang màu theme rose khi hover */
                background: #f0f0f0;
            }
        </style>
    </head>

    <body>
        <%@ include file="header_manage.jsp" %>
        <!-- main-slider -->
        <section class="w3l-main-slider position-relative" id="home">
            <div class="companies20-content">
                <div class="owl-one owl-carousel owl-theme">
                    <div class="item">
                        <li>
                            <div class="slider-info banner-view bg bg2">
                                <div class="banner-info">
                                    <h3>Latest Movie Trailers</h3>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.<span class="over-para">
                                            Consequuntur hic odio
                                            voluptatem tenetur consequatur.</span></p>
                                    <a href="#small-dialog1" class="popup-with-zoom-anim play-view1">
                                        <span class="video-play-icon">
                                            <span class="fa fa-play"></span>
                                        </span>
                                        <h6>Watch Trailer</h6>
                                    </a>
                                    <div id="small-dialog1" class="zoom-anim-dialog mfp-hide">
                                        <iframe src="https://player.vimeo.com/video/358205676" allow="autoplay; fullscreen"
                                                allowfullscreen=""></iframe>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </div>
                    <div class="item">
                        <li>
                            <div class="slider-info  banner-view banner-top1 bg bg2">
                                <div class="banner-info">
                                    <h3>Latest Online Movies</h3>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.<span class="over-para">
                                            Consequuntur hic odio
                                            voluptatem tenetur consequatur.</span></p>
                                    <a href="#small-dialog2" class="popup-with-zoom-anim play-view1">
                                        <span class="video-play-icon">
                                            <span class="fa fa-play"></span>
                                        </span>
                                        <h6>Watch Trailer</h6>
                                    </a>
                                    <div id="small-dialog2" class="zoom-anim-dialog mfp-hide">
                                        <iframe src="https://player.vimeo.com/video/395376850" allow="autoplay; fullscreen"
                                                allowfullscreen=""></iframe>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </div>
                    <div class="item">
                        <li>
                            <div class="slider-info banner-view banner-top2 bg bg2">
                                <div class="banner-info">
                                    <h3>Latest Movie Trailers</h3>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.<span class="over-para">
                                            Consequuntur hic odio
                                            voluptatem tenetur consequatur.</span></p>
                                    <a href="#small-dialog3" class="popup-with-zoom-anim play-view1">
                                        <span class="video-play-icon">
                                            <span class="fa fa-play"></span>
                                        </span>
                                        <h6>Watch Trailer</h6>
                                    </a>
                                    <div id="small-dialog3" class="zoom-anim-dialog mfp-hide">
                                        <iframe src="https://player.vimeo.com/video/389969665" allow="autoplay; fullscreen"
                                                allowfullscreen=""></iframe>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </div>
                    <div class="item">
                        <li>
                            <div class="slider-info banner-view banner-top3 bg bg2">
                                <div class="banner-info">
                                    <h3>Latest Online Movies</h3>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.<span class="over-para">
                                            Consequuntur hic odio
                                            voluptatem tenetur consequatur.</span></p>
                                    <a href="#small-dialog4" class="popup-with-zoom-anim play-view1">
                                        <span class="video-play-icon">
                                            <span class="fa fa-play"></span>
                                        </span>
                                        <h6>Watch Trailer</h6>
                                    </a>
                                    <div id="small-dialog4" class="zoom-anim-dialog mfp-hide">
                                        <iframe src="https://player.vimeo.com/video/323491174" allow="autoplay; fullscreen"
                                                allowfullscreen=""></iframe>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </div>
                </div>
            </div>
        </section>
        <!-- main-slider -->
        <!--grids-sec1-->
        <section class="w3l-grids">
            <div class="grids-main py-5">
                <div class="container py-lg-3">
                    <div class="headerhny-title">
                        <div class="w3l-title-grids">
                            <div class="headerhny-left">
                                <h3 class="hny-title">Popular Movies</h3>
                            </div>
                            <div class="headerhny-right text-lg-right">
                                <h4><a class="show-title" href="ViewAllController">Show all</a></h4>
                            </div>
                        </div>
                    </div>

                    <div class="w3l-populohny-grids">
                        <c:forEach items="${requestScope.listPopulars}" var="LP">
                            <div class="item vhny-grid">
                                <div class="box16">
                                    <a href="movies.jsp">
                                        <figure>
                                            <img class="img-fluid" src="${LP.getPoster_url()}" alt="">
                                        </figure>
                                        <div class="box-content">
                                            <h3 class="title">${LP.getTitle()}</h3>
                                            <h4> <span class="post"><span class="fa fa-clock-o"> </span> ${LP.getDuration()}min

                                                </span>

                                                <span class="post fa fa-heart text-right"></span>
                                            </h4>
                                        </div>
                                        <span class="fa fa-play video-icon" aria-hidden="true"></span>
                                    </a>
                                </div>
                                <div class="button-center text-center mt-4">
                                    <a href="movies.jsp" class="btn watch-button">Buy Ticket</a>
                                    <a href="movies.jsp" class="btn watch-button">Detail Movie</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </section>
        <!--//grids-sec1-->
        <!--grids-sec2-->
        <section class="w3l-grids">
            <div class="grids-main py-5">
                <div class="container py-lg-3">
                    <div class="headerhny-title">
                        <div class="w3l-title-grids">
                            <div class="headerhny-left">
                                <h3 class="hny-title">New Releases</h3>
                            </div>
                            <div class="headerhny-right text-lg-right">
                                <h4><a class="show-title" href="ViewAllController">Show all</a></h4>
                            </div>
                        </div>
                    </div>
                    <div class="owl-three owl-carousel owl-theme">

                        <c:forEach items="${requestScope.listNew}" var="LP">
                            <div class="item vhny-grid">
                                <div class="box16 mb-0">
                                    <a href="movies.jsp">
                                        <figure>
                                            <img class="img-fluid" src="${LP.getPoster_url()}" alt="">
                                        </figure>
                                        <div class="box-content">
                                            <h4> <span class="post"><span class="fa fa-clock-o"> </span> ${LP.getDuration()}min

                                                </span>

                                                <span class="post fa fa-heart text-right"></span>
                                            </h4>
                                        </div>
                                        <span class="fa fa-play video-icon" aria-hidden="true"></span>
                                    </a>
                                </div>
                                <h3> <a class="title-gd" href="movies.jsp"></a>${LP.getTitle()}</h3>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit</p>
                                <div class="button-center text-center mt-4">
                                    <a href="movies.jsp" class="btn watch-button">Buy Ticket</a>
                                    <a href="movies.jsp" class="btn watch-button">Detail Movie</a>
                                </div>

                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>
        </section>
        <!--grids-sec2-->
        <!--mid-slider -->
    <head>
        <title>Danh sách bài viết</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="text-center mb-4">Danh sách bài viết</h2>
            <div class="row">
                <c:forEach var="news" items="${newsList}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <img src="${news.photoUrl}" class="card-img-top" alt="${news.title}">
                            <div class="card-body">
                                <h5 class="card-title">${news.title}</h5>
                                <p class="card-text">Ngày đăng: ${news.createdDate}</p>
                                <a href="NewsDetailServlet?id=${news.postId}" class="btn btn-primary">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
    <!-- footer-66 -->
    <footer class="w3l-footer">
        <section class="footer-inner-main">
            <div class="footer-hny-grids py-5">
                <div class="container py-lg-4">
                    <div class="text-txt">
                        <div class="right-side">
                            <div class="row footer-about">
                                <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                    <a href="movies.jsp
                                       "><img class="img-fluid" src="assets/images/banner1.jpg"
                                           alt=""></a>
                                </div>
                                <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                    <a href="movies.jsp
                                       "><img class="img-fluid" src="assets/images/banner2.jpg"
                                           alt=""></a>
                                </div>
                                <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                    <a href="movies.jsp
                                       "><img class="img-fluid" src="assets/images/banner3.jpg"
                                           alt=""></a>
                                </div>
                                <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                    <a href="movies.jsp
                                       "><img class="img-fluid" src="assets/images/banner4.jpg"
                                           alt=""></a>
                                </div>
                            </div>
                            <div class="row footer-links">


                                <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                    <h6>Movies</h6>
                                    <ul>
                                        <li><a href="#">Movies</a></li>
                                        <li><a href="#">Videos</a></li>
                                        <li><a href="#">English Movies</a></li>
                                        <li><a href="#">Tailor</a></li>
                                        <li><a href="#">Upcoming Movies</a></li>
                                        <li><a href="Contact_Us.jsp
                                               ">Contact Us</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                    <h6>Information</h6>
                                    <ul>
                                        <li><a href="index.jsp
                                               ">Home</a> </li>
                                        <li><a href="about.jsp
                                               ">About</a> </li>
                                        <li><a href="#">Tv Series</a> </li>
                                        <li><a href="#">Blogs</a> </li>
                                        <li><a href="sign_in.jsp
                                               ">Login</a></li>
                                        <li><a href="Contact_Us.jsp
                                               ">Contact</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                    <h6>Locations</h6>
                                    <ul>
                                        <li><a href="movies.jsp
                                               ">Asia</a></li>
                                        <li><a href="movies.jsp
                                               ">France</a></li>
                                        <li><a href="movies.jsp
                                               ">Taiwan</a></li>
                                        <li><a href="movies.jsp
                                               ">United States</a></li>
                                        <li><a href="movies.jsp
                                               ">Korea</a></li>
                                        <li><a href="movies.jsp
                                               ">United Kingdom</a></li>
                                    </ul>
                                </div>
                                <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                    <h6>Newsletter</h6>
                                    <form action="#" class="subscribe mb-3" method="post">
                                        <input type="email" name="email" placeholder="Your Email Address" required="">
                                        <button><span class="fa fa-envelope-o"></span></button>
                                    </form>
                                    <p>Enter your email and receive the latest news, updates and special offers from us.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="below-section">
                <div class="container">
                    <div class="copyright-footer">
                        <div class="columns text-lg-left">
                            <p>&copy; 2021 MyShowz. All rights reserved</p>
                        </div>

                        <ul class="social text-lg-right">
                            <li><a href="#facebook"><span class="fa fa-facebook" aria-hidden="true"></span></a>
                            </li>
                            <li><a href="#linkedin"><span class="fa fa-linkedin" aria-hidden="true"></span></a>
                            </li>
                            <li><a href="#twitter"><span class="fa fa-twitter" aria-hidden="true"></span></a>
                            </li>
                            <li><a href="#google"><span class="fa fa-google-plus" aria-hidden="true"></span></a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <!-- move top -->
            <button onclick="topFunction()" id="movetop" title="Go to top">
                <span class="fa fa-arrow-up" aria-hidden="true"></span>
            </button>
            <script>
                // When the user scrolls down 20px from the top of the document, show the button
                window.onscroll = function () {
                    scrollFunction()
                };

                function scrollFunction() {
                    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                        document.getElementById("movetop").style.display = "block";
                    } else {
                        document.getElementById("movetop").style.display = "none";
                    }
                }

                // When the user clicks on the button, scroll to the top of the document
                function topFunction() {
                    document.body.scrollTop = 0;
                    document.documentElement.scrollTop = 0;
                }
            </script>
            <!-- /move top -->

        </section>
    </footer>
</body>

</html>
<!-- responsive tabs -->
<script src="assets/js/jquery-1.9.1.min.js"></script>
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
<!--/theme-change-->
<script src="assets/js/theme-change.js"></script>
<script src="assets/js/owl.carousel.js"></script>
<!-- script for banner slider-->
<script>
                    $(document).ready(function () {
                        $('.owl-one').owlCarousel({
                            stagePadding: 280,
                            loop: true,
                            margin: 20,
                            nav: true,
                            responsiveClass: true,
                            autoplay: true,
                            autoplayTimeout: 5000,
                            autoplaySpeed: 1000,
                            autoplayHoverPause: false,
                            responsive: {
                                0: {
                                    items: 1,
                                    stagePadding: 40,
                                    nav: false
                                },
                                480: {
                                    items: 1,
                                    stagePadding: 60,
                                    nav: true
                                },
                                667: {
                                    items: 1,
                                    stagePadding: 80,
                                    nav: true
                                },
                                1000: {
                                    items: 1,
                                    nav: true
                                }
                            }
                        })
                    })
</script>
<script>
    $(document).ready(function () {
        $('.owl-three').owlCarousel({
            loop: true,
            margin: 20,
            nav: false,
            responsiveClass: true,
            autoplay: true,
            autoplayTimeout: 5000,
            autoplaySpeed: 1000,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 2,
                    nav: false
                },
                480: {
                    items: 2,
                    nav: true
                },
                667: {
                    items: 3,
                    nav: true
                },
                1000: {
                    items: 5,
                    nav: true
                }
            }
        })
    })
</script>
<script>
    $(document).ready(function () {
        $('.owl-mid').owlCarousel({
            loop: true,
            margin: 0,
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
                    items: 1,
                    nav: false
                },
                667: {
                    items: 1,
                    nav: true
                },
                1000: {
                    items: 1,
                    nav: true
                }
            }
        })
    })
</script>
<!-- script for owlcarousel -->
<script src="assets/js/jquery.magnific-popup.min.js"></script>
<script>
    $(document).ready(function () {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',

            fixedContentPos: false,
            fixedBgPos: true,

            overflowY: 'auto',

            closeBtnInside: true,
            preloader: false,

            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in'
        });

        $('.popup-with-move-anim').magnificPopup({
            type: 'inline',

            fixedContentPos: false,
            fixedBgPos: true,

            overflowY: 'auto',

            closeBtnInside: true,
            preloader: false,

            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-slide-bottom'
        });
    });
</script>
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

<script src="assets/js/bootstrap.min.js"></script>

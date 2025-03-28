<%-- 
    Document   : about
    Created on : Feb 8, 2025, 3:59:58 PM
    Author     : bolac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zxx">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>About</title>

        <link rel="stylesheet" type="text/css" href="assets/css/swiper.min.css">

        <link rel="stylesheet" type="text/css" href="assets/css/style-starter.css">
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">

    </head>

    <body>
        <!-- header -->
        <header id="site-header" class="w3l-header fixed-top">
            <!--/nav-->
            <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                <div class="container">
                    <h1><a class="navbar-brand" href="HomePageController"><span class="fa fa-play icon-log"
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
                                <a class="nav-link" href="HomePageController">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ViewAllController">movies</a>
                            </li>

                            <li class="nav-item active">
                                <a class="nav-link" href="about.jsp">About</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="NewsListServlet">News</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="Contact_Us.jsp">Contact</a>
                            </li>
                        </ul>
                        

                        <!--/search-right-->
                        <!--/search-right-->

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
        <!--/breadcrumbs -->
        <div class="w3l-breadcrumbs">
            <nav id="breadcrumbs" class="breadcrumbs">
                <div class="container page-wrapper">
                    <a href="HomePageController">Home</a> » <span class="breadcrumb_last" aria-current="page">About</span>
                </div>
            </nav>
        </div>
        <!-- /about-->
        <div class="w3l-ab-grids py-5">

            <div class="container py-lg-4">
                <div class="row ab-grids-sec align-items-center">
                    <div class="col-lg-6 ab-right">
                        <img class="img-fluid" src="assets/images/banner1.jpg">
                    </div>
                    <div class="col-lg-6 ab-left pl-lg-4 mt-lg-0 mt-5">
                        <h3 class="hny-title">SWP391 Group 5 Assemble</h3>
                        <p class="mt-3">Chung toi la nhung nguoi linh tre. Rat yeu doi va luon mo uoc. Uoc mo duoc giu trong tim minh. Nhung thang nam dong doi ben nhau.</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- **************** -->
        <!-- Image Slider for MEET US Section-->
        <div style="padding-bottom: 280px" class="swiper-container" style="background-color: var(--theme-bg);">
            <h1 id="swiper-container-h1">Meet Our Team</h1>
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="imgBx">
                        <img src="assets/images/gao-bac.jpg" style="width :100%; height: 100%">
                    </div>
                    <div class="details">
                        <h3 id="details-h3-1">Nguyen Tuan Kiet<br></h3>
                        <div class="p-sm">
                            <a href="https://www.linkedin.com/in/kishan-patel-a95827176/"><i class="fa fa-linkedin"></i></a>
                            <a href="https://www.instagram.com/kishann_20/"><i class="fa fa-instagram"></i></a>
                            <a href="https://github.com/Kishan207p"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="imgBx">
                        <img src="assets/images/gao-xanh.jpg" style="width :100%; height: 100%">
                    </div>
                    <div class="details">
                        <h3 id="details-h3-2">Nguyen Trong Vu (cuu thanh vien)<br></h3>
                        <div class="p-sm">
                            <a href="https://www.linkedin.com/in/sharvil-patel/"><i class="fa fa-linkedin"></i></a>
                            <a href="https://www.instagram.com/sharvil551/"><i class="fa fa-instagram"></i></a>
                            <a href="https://github.com/Sharvil24600"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="imgBx">
                        <img src="assets/images/gao-vang.jpg" style="width :100%; height: 100%">
                    </div>
                    <div class="details">
                        <h3 id="details-h3-3">Cao Phan Tuan Anh<br></h3>
                        <div class="p-sm">
                            <a href="https://in.linkedin.com/in/harshmange"><i class="fa fa-linkedin"></i></a>
                            <a href="https://www.instagram.com/im.h_r_s_h/"><i class="fa fa-instagram"></i></a>
                            <a href="https://github.com/harshmange44"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide" style="background-color: white">
                    <div class="imgBx">
                        <img src="assets/images/gao-do.jpg" style="width :100%; height: 100%">
                    </div>
                    <div class="details">
                        <h3 id="details-h3-4">Hoang Khoi Nguyen<br></h3>
                        <div class="p-sm">
                            <a href="https://www.linkedin.com/in/yugamsinh-chavda-2b06ab18b/"><i class="fa fa-linkedin"></i></a>
                            <a href="https://www.instagram.com/yugamsinh_chavda/"><i class="fa fa-instagram"></i></a>
                            <a href="https://github.com/YugamsinhChavda"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="imgBx">
                        <img src="assets/images/gao-den.jpg" style="width :100%; height: 100%">
                    </div>
                    <div class="details">
                        <h3 id="details-h3-5">Phan Quang Giap<br></h3>
                        <div class="p-sm">
                            <a href="https://www.linkedin.com/in/yugamsinh-chavda-2b06ab18b/"><i class="fa fa-linkedin"></i></a>
                            <a href="https://www.instagram.com/yugamsinh_chavda/"><i class="fa fa-instagram"></i></a>
                            <a href="https://github.com/YugamsinhChavda"><i class="fa fa-github"></i></a>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
        </div>

        <%@ include file="footer.jsp" %>
    </body>

</html>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<!-- stats -->
<script src="assets/js/jquery.waypoints.min.js"></script>
<script src="assets/js/jquery.countup.js"></script>
<script type="text/javascript" src='assets/js/swiper.min.js'></script>
<script>
    $('.counter').countUp();
</script>
<!--/theme-change-->
<script src="assets/js/theme-change.js"></script>
<script>
    const toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
    const currentTheme = localStorage.getItem('theme');

    if (currentTheme) {
        document.documentElement.setAttribute('data-theme', currentTheme);

        switchTextColor(currentTheme);

        if (currentTheme === 'dark') {
            toggleSwitch.checked = true;
        }
    }

    function switchTextColor(currTheme) {

        if (currTheme === 'light') {
            document.getElementById("swiper-container-h1").style.color = 'black';
            document.getElementById("details-h3-1").style.color = 'black';
            document.getElementById("details-h3-2").style.color = 'black';
            document.getElementById("details-h3-3").style.color = 'black';
            document.getElementById("details-h3-4").style.color = 'black';
            document.getElementById("details-h3-5").style.color = 'black';
            document.getElementById("details-h3-6").style.color = 'black';
        } else {
            document.getElementById("swiper-container-h1").style.color = 'white';
            document.getElementById("details-h3-1").style.color = 'white';
            document.getElementById("details-h3-2").style.color = 'white';
            document.getElementById("details-h3-3").style.color = 'white';
            document.getElementById("details-h3-4").style.color = 'white';
            document.getElementById("details-h3-5").style.color = 'white';
            document.getElementById("details-h3-6").style.color = 'white';
        }
    }
    toggleSwitch.addEventListener('change', switchTheme, false);
</script>
<script src="assets/js/owl.carousel.js"></script>
<!-- script for banner slider-->
<script>
    $(document).ready(function () {
        $('.owl-team').owlCarousel({
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
                    items: 4,
                    nav: true
                }
            }
        })
    })
</script>


<script>
    var swiper = new Swiper('.swiper-container', {
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: 'auto',
        coverflowEffect: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
            slideShadows: true,
        },
        pagination: {
            el: '.swiper-pagination',
        },
    });
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
                    items: 6,
                    nav: true
                }
            }
        })
    })
</script>
<!-- for tesimonials carousel slider -->
<script>
    $(document).ready(function () {
        $(".owl-clients").owlCarousel({
            loop: true,
            margin: 40,
            responsiveClass: true,
            responsive: {
                0: {
                    items: 1,
                    nav: true
                },
                736: {
                    items: 2,
                    nav: false
                },
                1000: {
                    items: 3,
                    nav: true,
                    loop: false
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
<script src="assets/js/bootstrap.min.js"></script>

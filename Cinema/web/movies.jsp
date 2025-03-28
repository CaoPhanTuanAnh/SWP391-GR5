<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zxx">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Movies</title>
        <link rel="stylesheet" href="assets/css/style-starter.css">
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">
        <style>
            .box16 figure img {
                width: 100%; /* Đảm bảo ảnh rộng đầy khung */
                height: 300px; /* Cố định chiều cao */
                object-fit: cover; /* Cắt ảnh cho vừa khung mà không méo */
                border-radius: 10px; /* Làm mềm góc ảnh nếu cần */
            }

            .item.vhny-grid {
                width: 100%;
                max-width: 250px; /* Đặt kích thước tối đa cho mỗi phim */
            }
            .box16 {
                height: 400px; /* Đảm bảo chiều cao đồng nhất */
            }

        </style>
    </head>

    <body>
        <!-- header -->
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
        <!--/breadcrumbs -->
        <div class="w3l-breadcrumbs">
            <nav id="breadcrumbs" class="breadcrumbs">
                <div class="container page-wrapper">
                    <a href="HomePage.jsp">Home</a> » <span class="breadcrumb_last" aria-current="page">movies</span>
                </div>
            </nav>
        </div>
        <!--/movies -->
        <!--grids-sec1-->
        <section class="w3l-search py-5">
            <div class="container py-md-4">
                <div class="search-grid">
                    <h3 class="hny-title mb-3 text-center">Search Movies</h3>
                    <form action="FilterController" method="post" class="search-form">
                        <div class="row">
                            <!-- Search by Director or Actor -->
                            <div class="col-md-6 mb-3">
                                <input type="text" id="productName" name="name" class="form-control" 
                                       placeholder="Enter movie name" >
                            </div>


                            <div class="col-md-3 mb-3">
                                <label for="showTimeFrom" class="form-label">Show Time From</label>
                                <input type="time" id="showTimeFrom" name="showTimeFrom" class="form-control">
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="showTimeTo" class="form-label">Show Time To</label>
                                <input type="time" id="showTimeTo" name="showTimeTo" class="form-control">
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="showDate" class="form-label">Show Date</label>
                                <input type="date" id="showDate" name="showDate" class="form-control">
                            </div>

                        </div>

                        <!-- Genre Selection -->
                        <div class="row">
                            <div class="col-12 mb-3">
                                <h4 class="mb-2">Genre</h4>
                                <select name="cid" class="form-control" multiple>
                                    <c:forEach items="${categorys}" var="L">
                                        <option value="${L.genre_id}">${L.genre_name}</option>
                                    </c:forEach>
                                </select>
                                <small class="text-muted">Hold Ctrl (Windows) / Command (Mac) to select multiple genres.</small>
                            </div>
                        </div>


                        <!-- Search Button -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary px-4">SEARCH</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>


        <!--/movies -->
        <!--grids-sec1-->

        <!--grids-sec1-->
        <section class="w3l-grids">
            <div class="grids-main py-5">
                <div class="container py-lg-4">
                    <div class="headerhny-title">
                        <div class="w3l-title-grids">
                            <div class="headerhny-left">
                                <h3 class="hny-title">List Movies</h3>
                            </div>

                        </div>
                    </div>
                    <div class="w3l-populohny-grids">
                        <c:forEach items="${requestScope.listMovies}" var="LP">
                            <div class="item vhny-grid">
                                <div class="box16 mb-0">
                                    <figure>
                                        <img class="img-fluid" src="${LP.getPoster_url()}" alt="">
                                    </figure>
                                    <a href="#modal-${LP.getMovie_id()}" data-toggle="modal">
                                        <div class="box-content">
                                            <h3 class="title">${LP.getTitle()}</h3>
                                            <h4> <span class="post"><span class="fa fa-clock-o"> </span> ${LP.getDuration()}min

                                                </span>

                                                <span class="post fa fa-heart text-right"></span>
                                            </h4>
                                        </div>
                                    </a>

                                </div>
                                <div class="button-center text-center mt-4">
                                    <a href="BranchController?mid=${LP.getMovie_id()}" class="btn watch-button">Buy Ticket</a>

                                </div>

                                <div class="modal fade" id="modal-${LP.getMovie_id()}" tabindex="-1" role="dialog" aria-hidden="true">

                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content" id="mymodalcontent">
                                            <div class="modal-header">
                                                <h4 class="modal-title" id="exampleModalLongTitle">DETAILS</h4>
                                                <button type="button" class="closebtn" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body" id="dynamic-content">
                                                <img src="${LP.getPoster_url()}" class="img-fluid modalimg" alt="" />
                                                <p>
                                                <h3>Release Date&nbsp;:${LP.getRelease_date()} </h3>

                                                </p>
                                                <h4>About Movie</h4>
                                                <p>
                                                    ${LP.getDescription()}
                                                </p>
                                                <h4>Star Cast</h4>
                                                <h3>Diễn viên</h3>
                                                <c:if test="${not empty LP.getParts()}">
                                                    <ul>
                                                        <c:forEach items="${LP.getParts()}" var="actor">
                                                            <c:if test="${actor.role == 'Actor' ||  actor.role == 'Actress'}"> <!-- Kiểm tra vai trò là Diễn viên -->
                                                                <li>
                                                                    <a href="DetailParticipantController?pid=${actor.participant_id}">
                                                                        <img src="${actor.portrait_url}" alt="${actor.participant_name}" width="50">
                                                                        ${actor.participant_name}
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>

                                                <c:if test="${empty LP.getParts()}">
                                                    <p>Chưa có thông tin diễn viên.</p>
                                                </c:if>

                                                <h3>Đạo diễn</h3>
                                                <c:if test="${not empty LP.getParts()}">
                                                    <ul>
                                                        <c:forEach items="${LP.getParts()}" var="director">
                                                            <c:if test="${director.role == 'Director'}"> <!-- Kiểm tra vai trò là Đạo diễn -->
                                                                <li>
                                                                    <a href="DetailParticipantController?pid=${director.participant_id}">
                                                                        <img src="${director.portrait_url}" alt="${director.participant_name}" width="50">
                                                                        ${director.participant_name}
                                                                    </a>
                                                                </li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </c:if>

                                                <c:if test="${empty LP.getParts()}">
                                                    <p>Chưa có thông tin đạo diễn.</p>
                                                </c:if>




                                            </div>
                                            <form action="BranchController" method="post">
                                                <input type="hidden" name="movieId" value="${LP.getMovie_id()}">
                                                <button type="submit" class="btn btn-success">Book</button>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>



                        </c:forEach>
                    </div>

                </div>


                <nav class="d-flex justify-content-center" aria-label="Page navigation">
                    <ul class="pagination">
                        <c:url var="prepagelink" value="ViewAllController">
                            <c:param name="pagenumber" value="${page - 1}"></c:param>

                        </c:url>
                        <li class="page-item ${page == 1 ? "disabled" : ""}"><a class="page-link" href="${prepagelink}">Previous</a></li>
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="i">
                                <c:url var="curpagelink" value="ViewAllController">
                                    <c:param name="pagenumber" value="${i}"></c:param>

                            </c:url>
                            <li class="page-item ${i == page ? "active" : ""}"><a class="page-link" href="${curpagelink}">${i}</a></li>
                            </c:forEach>
                            <c:url var="nextpagelink" value="ViewAllController">
                                <c:param name="pagenumber" value="${page + 1}"></c:param>

                        </c:url>
                        <li class="page-item ${page == totalPage ? "disabled" : ""}"><a class="page-link" href="${nextpagelink}">Next</a></li>
                    </ul>
                </nav>

            </div>
        </section>


        <!--grids-sec2-->
        <!-- footer-66 -->
        <footer class="w3l-footer">
            <section class="footer-inner-main">
                <div class="footer-hny-grids py-5">
                    <div class="container py-lg-4">
                        <div class="text-txt">
                            <div class="right-side">
                                <div class="row footer-about">
                                    <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                        <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner1.jpg"
                                                                  alt=""></a>
                                    </div>
                                    <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                        <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner2.jpg"
                                                                  alt=""></a>
                                    </div>
                                    <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                        <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner3.jpg"
                                                                  alt=""></a>
                                    </div>
                                    <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                        <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner4.jpg"
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
                                            <li><a href="Contact_Us.jsp">Contact Us</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                        <h6>Information</h6>
                                        <ul>
                                            <li><a href="index.jsp">Home</a> </li>
                                            <li><a href="about.jsp">About</a> </li>
                                            <li><a href="#">Tv Series</a> </li>
                                            <li><a href="#">Blogs</a> </li>
                                            <li><a href="sign_in.jsp">Login</a></li>
                                            <li><a href="Contact_Us.jsp">Contact</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-3 col-sm-6 sub-two-right mt-5">
                                        <h6>Locations</h6>
                                        <ul>
                                            <li><a href="movies.jsp">Asia</a></li>
                                            <li><a href="movies.jsp">France</a></li>
                                            <li><a href="movies.jsp">Taiwan</a></li>
                                            <li><a href="movies.jsp">United States</a></li>
                                            <li><a href="movies.jsp">Korea</a></li>
                                            <li><a href="movies.jsp">United Kingdom</a></li>
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

<script src="assets/js/bootstrap.min.js"></script>

<%-- 
    Document   : user_profile
    Created on : Feb 12, 2025, 11:22:21 PM
    Author     : GIGABYTE
--%>

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

            .maincontent{
                color:black;
                font-size: medium;
                margin-top: 90px;
                display: flex;
                justify-content: center; /* aligns the items horizontally */
                align-items: baseline;
                flex-wrap: wrap;
            }
            .maincontent table{
                box-shadow: 10px 0px 15px lightgrey;
                margin: 20px 0 20px 0;
                height: 30vh;
                width: 55vw;
            }
            .sidenav{

            }
            .navoption{
                display: block;
                padding: 10px 15px;
                border-width: 3px 0px 0px 5px;
                border-color: #c01050;
                border-style: solid;
                border-radius: 5px 0px 0px 5px;
                background-color: white;
            }
            .mess{
                width: 100vw;
                text-align: center;
                height: 28px;
            }
            .form-button{
                display: inline-block;
                width: 5em;
                text-align: center;
                padding: 4px;
                border-width: 2px;
                border-style: solid;
                border-radius: 5px;
                color: #fff;
                background-color: #df0e62;
                border-color: #c01050;
            }
            .form-button:hover, .form-button:focus {
                color: #fff;
                background-color: #ff2e82;
                border-color: #df0e62;
            }
            .form-button:active{
                color: #fff;
                background-color: #c01050;
                border-color: #ff2e82;
            }
            .field-name{
                padding: 25px 0px 0px 30px;
            }
            .field{
                padding: 5px 25px 0px 30px;
            }
            .field-value{
                width: 100%;
                padding: 4px 6px 4px 6px;
                color: grey;
                background-color: lightgray;
                border: 2px solid grey;
                border-radius: 7px;
            }

        </style>
    </head>

    <body>

        <!-- header -->
        <header id="site-header" class="w3l-header fixed-top">
            <!--/nav-->
            <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                <div class="container">
                    <h1><a class="navbar-brand" href="index.jsp"><span class="fa fa-play icon-log"
                                                                       aria-hidden="true"></span>
                            MyShowz</a></h1>
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
                            <li class="nav-item active">
                                <a class="nav-link" href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="movies.jsp">Movies</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="about.jsp">About</a>
                            </li>


                            <li class="nav-item">
                                <a class="nav-link" href="Contact_Us.jsp">Contact</a>
                            </li>
                            <c:if test="${sessionScope.acc.role_id == 1}">
                                <li class="nav-item">
                                    <a class="nav-link" >Manage</a>
                                    <ul class="dropdown">
                                        <li><a href="city_control">Manage City</a></li>
                                        <li><a href="ManageTheater">Manage Theater</a></li>
                                        <li><a href="#">Manage Account</a></li>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc.role_id == 2}">
                                <li class="nav-item">
                                    <a class="nav-link" >Manage</a>
                                    <ul class="dropdown">
                                        <li><a href="#">Manage Room</a></li>
                                        <li><a href="#">Manage Seat</a></li>
                                        <li><a href="#">Manage Show Time</a></li>
                                    </ul>
                                </li>
                            </c:if>

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

                        <c:choose>
                            <c:when test="${sessionScope.acc != null}">
                                <div class="header__top__right__auth">
                                    <a href="logout"><i class="fa fa-user"></i> Logout</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="header__top__right__auth">
                                    <a href="sign_in.jsp"><i class="fa fa-user"></i> Login / Sign up</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="Login_SignUp" id="login"
                             style="font-size: 2rem ; display: inline-block; position: relative;">
                            <!-- <li class="nav-item"> -->
                            <c:choose>
                                <c:when test="${sessionScope.acc != null}">
                                    <a class="nav-link" href="user_profile?service=editProfile"><i class="fa fa-user-circle-o"></i></a>
                                    </c:when>
                                    <c:otherwise>
                                    <a class="nav-link" href="sign_in.jsp"><i class="fa fa-user-circle-o"></i></a>
                                    </c:otherwise>
                                </c:choose>
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

        <div class="maincontent">
            <c:choose>
                <c:when test="${requestScope.mess != null}">
                    <p class="mess">${requestScope.mess}</p>
                </c:when>
                <c:otherwise>
                    <p class="mess">     </p>
                </c:otherwise>
            </c:choose>
            <div class="sidenav" style="display:inline-block">
                <a class="navoption" id="currentnavoption" href="user_profile?service=editProfile">User Profile</a>
                <a class="navoption" href="user_profile?service=listUserBooking">Booking History</a>
            </div>
            <form action="user_profile" method="post" style="display:inline-block">
                <input type="hidden" name="service" value="editProfile">
                <table>
                    <tbody>
                        <tr>
                            <td class="field-name">Full name</td>
                            <td class="field-name">Birth Date</td>
                        </tr>
                        <tr>
                            <td class="field"><input class="field-value" type="text" name="fullName" value="${sessionScope.acc.getFullname()}" readonly required></td>
                            <td class="field"><input class="field-value" type="text" name="address" value="${sessionScope.acc.getBirth_date()}" readonly required></td>
                        </tr>
                        <tr>
                            <td class="field-name">Email</td>
                            <td class="field-name">Phone number</td>
                        </tr>
                        <tr>
                            <td class="field"><input class="field-value" type="email" name="email" value="${sessionScope.acc.getEmail()}" readonly required></td>
                            <td class="field"><input class="field-value" type="number" name="phone" value="${sessionScope.acc.getPhone()}" readonly required></td>
                        </tr>
                        <tr>
                            <td class="field-name">Username</td>
                            <td class="field-name">Password</td>
                        </tr>
                        <tr>
                            <td class="field"><p class="field-value">${sessionScope.acc.getUsername()}</p></td>
                            <td class="field"><a class="form-button" style="width: 9em;" href="change_password.jsp">Change password</a></td>
                        </tr>
                        <tr>
                            <td class="field-name" colspan="2">
                                <c:choose>
                                    <c:when test="${sessionScope.acc.role_id == 3}">
                                        <span>Favor Theater</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>Working Theater</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="field">
                                <select name="theater_id" class="field-value" disabled required>
                                    <c:forEach var="theater" items="${theaterList}">
                                        <c:choose>
                                            <c:when test="${sessionScope.acc.getTheater_id() == theater.getTheater_id()}">
                                                <option value="${theater.getTheater_id()}" selected>${theater.getAddress()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${theater.getTheater_id()}">${theater.getAddress()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="text-align: right; padding: 10px 25px 15px 0px">
                                <button class="form-button" hidden>Submit</button>
                                <div class="form-button" onclick="editOrCancel(this)">Edit</div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>

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

<!-- Allow edit -->
<script>
    let info = []
            , unlock = "color: black; background-color: white; border-color: black;"
            , lock = "color: grey; background-color: lightgrey; border-color: grey;"
            , infoSelect;
    function editOrCancel(button) {
        let submit = document.querySelector(".maincontent button");
        let oldInfo = document.querySelectorAll(".maincontent input");
        let oldInfoSelect = document.querySelectorAll(".maincontent select");
        if (button.innerHTML === "Edit") {
            for (let i = 1; i < 5; i++) {
                info[i] = oldInfo[i].value;
                oldInfo[i].readOnly = false;
                oldInfo[i].style = unlock;
            }
            infoSelect = oldInfoSelect[0].value;
            oldInfoSelect[0].disabled = false;
            oldInfoSelect[0].style = unlock;
            button.innerHTML = "Cancel";
            submit.hidden = false;
        } else {
            for (let i = 1; i < 5; i++) {
                oldInfo[i].value = info[i];
                oldInfo[i].readOnly = true;
                oldInfo[i].style = lock;
            }
            oldInfoSelect[0].value = infoSelect;
            oldInfoSelect[0].disabled = true;
            oldInfoSelect[0].style = lock;
            button.innerHTML = "Edit";
            submit.hidden = true;
        }
    }
</script>

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
    });</script>
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
    });</script>
<!-- disable body scroll which navbar is in active -->
<script>
    $(function () {
        $('.navbar-toggler').click(function () {
            $('body').toggleClass('noscroll');
        })
    });</script>
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
    });</script>

<script src="assets/js/bootstrap.min.js"></script>


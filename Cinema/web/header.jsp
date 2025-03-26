<%-- 
    Document   : header
    Created on : Mar 6, 2025, 11:11:03 PM
    Author     : GIGABYTE
--%>



<!-- header -->
<header id="site-header" class="w3l-header fixed-top">
    <!--/nav-->
    <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
        <div class="container">
            <h1><a class="navbar-brand" href="HomePageController
                   "><span class="fa fa-play icon-log"
                        aria-hidden="true"></span>
                    MyShowz</a></h1>
            <!-- if logo is image enable this   
                            <a class="navbar-brand" href="#index.jsp
            ">
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
                        <a class="nav-link" href="HomePageController">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ViewAllController">Movies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.jsp">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="NewsListServlet">News</a>
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
                                <li><a href="ManageAccount">Manage Account</a></li>
                                <li><a href="movie_participant_control">Manage Movie Participant</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.acc.role_id == 2}">
                        <li class="nav-item">
                            <a class="nav-link" >Manage</a>
                            <ul class="dropdown">
                                <li><a href="ManageRoom">Manage Room</a></li>
                                <li><a href="ManageSeat">Manage Seat</a></li>
                                <li><a href="ManageNews">Manage News</a></li>
                                <li><a href="combo_control">Manage Combo</a></li>
                                <li><a href="coupons">Manage Coupon</a></li>
                                <li><a href="participant_control">Manage Participant</a></li>
                                <li><a href="ShowtimeURL?service=listShowtimeByRoom">Manage Show Time</a></li>
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
                                    <li><a href="movies.jsp
                                           ">Action</a></li>
                                    <li><a href="movies.jsp
                                           ">Drama</a></li>
                                    <li><a href="movies.jsp
                                           ">Family</a></li>
                                    <li><a href="movies.jsp
                                           ">Thriller</a></li>
                                    <li><a href="movies.jsp
                                           ">Commedy</a></li>
                                    <li><a href="movies.jsp
                                           ">Romantic</a></li>
                                    <li><a href="movies.jsp
                                           ">Tv-Series</a></li>
                                    <li><a href="movies.jsp
                                           ">Horror</a></li>
                                    <li><a href="movies.jsp
                                           ">Action</a></li>
                                    <li><a href="movies.jsp
                                           ">Drama</a></li>
                                    <li><a href="movies.jsp
                                           ">Family</a></li>
                                    <li><a href="movies.jsp
                                           ">Thriller</a></li>
                                    <li><a href="movies.jsp
                                           ">Commedy</a></li>
                                    <li><a href="movies.jsp
                                           ">Romantic</a></li>
                                    <li><a href="movies.jsp
                                           ">Tv-Series</a></li>
                                    <li><a href="movies.jsp
                                           ">Horror</a></li>
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
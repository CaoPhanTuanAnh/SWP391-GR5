<%-- 
    Document   : header_manage
    Created on : Mar 23, 2025, 9:17:01 PM
    Author     : GIGABYTE
--%>


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
                    <c:if test="${sessionScope.acc.getRole_id() == 1}">
                        <li class="nav-item active">
                            <a class="nav-link" href="HomePageController">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ManageAccount">Manage Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="city_control">Manage City</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ManageTheater">Manage Theater</a>
                        </li>
                        <li class="nav-item"><a class="nav-link"  href="movie_participant_control">Manage Movie Participant</a></li>
                        <li class="nav-item"><a class="nav-link" href="ViewRevenue">View Revenue</a></li>

                    </c:if>

                    <c:if test="${sessionScope.acc.getRole_id() == 2}">
                        <li class="nav-item active">
                            <a class="nav-link" href="HomePageController">Home</a>
                        </li>
                        <li class="nav-item"><a class="nav-link"  href="ManageRoom">Manage Room</a></li>
                        <li class="nav-item"><a  class="nav-link" href="ManageSeat">Manage Seat</a></li>
                        <li class="nav-item"><a class="nav-link"  href="ManageNews">Manage News</a></li>
                        <li class="nav-item"><a class="nav-link"  href="combo_control">Manage Combo</a></li>
                        <li class="nav-item"><a class="nav-link"  href="participant_control">Manage Participant</a></li>
                        <li class="nav-item"><a class="nav-link"  href="ShowtimeURL?service=listShowtimeByRoom">Manage Show Time</a></li>
                    </c:if>

                </ul>

                <c:choose>
                    <c:when test="${sessionScope.acc != null}">
                        <div class="header__top__right__auth">
                            <a style="color: #df0e62;" href="logout"><i class="fa fa-user"></i> Logout</a>
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

<%-- 
    Document   : header_manage
    Created on : Mar 23, 2025, 9:17:01 PM
    Author     : GIGABYTE
--%>

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
        left: 65vw;
        background: white;
        border: 1px solid #ccc;
        width: 150px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
    }



    /* Hi?n th? dropdown khi hover */
    li:hover .dropdown {
        display: block;
    }
    .dropdown li a {
        display: block;
        color: black; /* Màu ch? m?c ??nh */
        background: white;
        padding: 10px;
        transition: color 0.1s ease; /* Hi?u ?ng chuy?n ??i màu */
    }
    .dropdown li a:hover {
        color: var(--theme-rose); /* Chuy?n sang màu theme rose khi hover */
        background: #f0f0f0;
    }

    .box16 figure img {
        width: 100%; /* ??m b?o ?nh r?ng ??y khung */
        height: 300px; /* C? ??nh chi?u cao */
        object-fit: cover; /* C?t ?nh cho v?a khung mà không méo */
        border-radius: 10px; /* Làm m?m góc ?nh n?u c?n */
    }

    .item.vhny-grid {
        width: 100%;
        max-width: 250px; /* ??t kích th??c t?i ?a cho m?i phim */
    }
    .box16 {
        height: 400px; /* ??m b?o chi?u cao ??ng nh?t */
    }



</style>
<!-- header -->
<header id="site-header" class="w3l-header fixed-top">
    <!--/nav-->
    <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
        <div class="container">
            <h1><a class="navbar-brand" href="HomePageController"><span class="fa fa-play icon-log"
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
                        <c:if test="${sessionScope.acc.role_id == 1}">
                            <li class="nav-item">
                                <a class="nav-link" >Manage</a>
                                <ul class="dropdown">
                                    <li><a href="city_control">Manage City</a></li>
                                    <li><a href="ManageTheater">Manage Theater</a></li>
                                    <li><a href="ManageAccount">Manage Account</a></li>
                                    <li><a href="ManageMovie">Manage Movie</a></li>
                                    <li><a href="participant_control">Manage Participant</a></li>
                                    <li><a href="movie_participant_control">Manage Movie Participant</a></li>
                                    <li><a href="ManageMovieGenre">Manage Movie Genre</a></li>
                                    <li><a href="ViewRevenue">View Revenue</a></li>
                                </ul>
                            </li>
                        </c:if>

                    </c:if>

                    <c:if test="${sessionScope.acc.getRole_id() == 2}">
                        <li class="nav-item active">
                            <a class="nav-link" href="HomePageController">Home</a>
                        </li>

                        <c:if test="${sessionScope.acc.role_id == 2}">
                            <li class="nav-item">
                                <a class="nav-link" >Manage</a>
                                <ul class="dropdown">
                                    <li><a href="ManageRoom">Manage Room</a></li>
                                    <li><a href="ManageSeat">Manage Seat</a></li>
                                    <li><a href="ManageNews">Manage News</a></li>
                                    <li><a href="combo_control">Manage Combo</a></li>
                                    <li><a href="coupons">Manage Coupon</a></li>
                                    <li><a href="ShowtimeURL?service=listShowtimeByRoom">Manage Show Time</a></li>
                                </ul>
                            </li>
                        </c:if>
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

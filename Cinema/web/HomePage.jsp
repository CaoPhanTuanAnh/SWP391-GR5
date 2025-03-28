
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

        <%@ include file="header.jsp" %>
        <!-- main-slider -->
        <section class="w3l-main-slider position-relative" id="home">
            <div class="companies20-content">
                <div class="owl-one owl-carousel owl-theme">

                    <c:forEach var="ad" begin="0" end="3" step="1" items="${listAdNews}" varStatus="adstatus">
                        <div class="item">
                            <li>
                                <c:if test="${adstatus.getCount()==0}">
                                    <div class="slider-info banner-view bg bg2">
                                    </c:if>

                                    <c:if test="${adstatus.getCount()!=0}">
                                        <div style=" background: url(${ad.getPhotoUrl()}) no-repeat center center;
                                             background-size: contain;
                                             background-color: black;
                                             width: 100%;
                                             height: 500px; /* Điều chỉnh chiều cao theo ý muốn */" 
                                             class="slider-info banner-view banner-top${adstatus.getCount()} bg bg2">
                                        </c:if>
                                        <div class="banner-info">
                                            <h3>${ad.getTitle()}</h3>
                                            <a href="#small-dialog${adstatus.getCount()}" class="popup-with-zoom-anim play-view1">
                                                <h6>=> See News's Detail</h6>
                                            </a>
                                            <div id="small-dialog${adstatus.getCount()}" class="zoom-anim-dialog mfp-hide" style="display: flex">
                                                <iframe src="${ad.getPhotoUrl()}" allow="autoplay; fullscreen"
                                                        allowfullscreen=""></iframe>
                                                <p>${ad.getContent()}</p>
                                            </div>
                                        </div>
                                    </div>
                            </li>
                        </div>
                    </c:forEach>
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
                                <h3 class="hny-title" id="presenting">Presenting Movies</h3>
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
                                    <a href="#modal-${LP.getMovie_id()}" data-toggle="modal">
                                        <figure>
                                            <img class="img-fluid" src="${LP.getPoster_url()}" alt="" >
                                        </figure>
                                        <div class="box-content">
                                            <h3 class="title">${LP.getTitle()}</h3>
                                            <h4> <span class="post"><span class="fa fa-clock-o"> </span> ${LP.getDuration()}min

                                                </span>

                                                <span class="post fa fa-heart text-right"></span>
                                            </h4>
                                        </div>
                                        <a href="${LP.getTrailer_url()}"><span class="fa fa-play video-icon" aria-hidden="true"></span></a>
                                    </a>
                                </div>
                                <div class="button-center text-center mt-4">
                                    <a href="BranchController?mid=${LP.getMovie_id()}" class="btn watch-button">Buy Ticket</a>
                                    <a href="#modal-${LP.getMovie_id()}" data-toggle="modal"class="btn watch-button">Detail Movie</a>
                                </div>
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
                                <h3 class="hny-title" id="upcoming">Upcoming Movies</h3>
                            </div>
                        </div>
                    </div>
                    <div class="owl-three owl-carousel owl-theme">

                        <c:forEach items="${requestScope.listNew}" var="LP">
                            <div class="item vhny-grid">
                                <div class="box16 mb-0">
                                    <a href="#modal-${LP.getMovie_id()}" data-toggle="modal">
                                        <figure>
                                            <img class="img-fluid" src="${LP.getPoster_url()}" alt="">
                                        </figure>
                                        <div class="box-content">
                                            <h4> <span class="post"><span class="fa fa-clock-o"> </span> ${LP.getDuration()}min

                                                </span>

                                                <span class="post fa fa-heart text-right"></span>
                                            </h4>
                                        </div>
                                        <a href="${LP.getTrailer_url()}"><span class="fa fa-play video-icon" aria-hidden="true"></span></a>
                                    </a>
                                </div>
                                <h3> <a class="title-gd" href="movies.jsp"></a>${LP.getTitle()}</h3>
                                <div class="button-center text-center mt-4">
                                    <a href="#modal-${LP.getMovie_id()}" data-toggle="modal"class="btn watch-button">Detail Movie</a>
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

            </div>
        </section>
        <!--grids-sec2-->
        
        <%@ include file="footer.jsp" %>
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

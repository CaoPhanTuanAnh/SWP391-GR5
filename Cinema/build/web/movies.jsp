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
                left: 57vw;
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
    </head>

    <body>
        <!-- header -->
        <%@ include file="header.jsp" %>
        <!--/breadcrumbs -->
        <div class="w3l-breadcrumbs">
            <nav id="breadcrumbs" class="breadcrumbs">
                <div class="container page-wrapper">
                    <a href="HomePageController">Home</a> » <span class="breadcrumb_last" aria-current="page">movies</span>
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
                                                <h3>Đạo diễn</h3>
                                                <c:if test="${empty requestScope.directors}">
                                                    <p>Chưa có thông tin đạo diễn.</p>
                                                </c:if>
                                                <ul>
                                                    <c:forEach items="${requestScope.directors}" var="director">
                                                        <li>
                                                            <a href="DetailParticipantController?pid=${director.participant_id}">
                                                                <img src="${director.portrait_url}" alt="${director.participant_name}" width="50">
                                                                ${director.participant_name}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>

                                                <h3>Diễn viên</h3>
                                                <c:if test="${empty requestScope.actors}">
                                                    <p>Chưa có thông tin diễn viên.</p>
                                                </c:if>
                                                <ul>
                                                    <c:forEach items="${requestScope.actors}" var="actor">
                                                        <li>
                                                            <a href="DetailParticipantController?pid=${actor.participant_id}">
                                                                <img src="${actor.portrait_url}" alt="${actor.participant_name}" width="50">
                                                                ${actor.participant_name}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>


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

<script src="assets/js/bootstrap.min.js"></script>

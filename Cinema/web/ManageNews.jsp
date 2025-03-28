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
            .limited-text {
                align-items: center;
                white-space: nowrap;      /* Ngăn nội dung xuống dòng */
                overflow: hidden;         /* Ẩn phần nội dung dư */
                text-overflow: ellipsis;  /* Thêm dấu "..." */
                max-width: 100px;         /* Giới hạn độ rộng */
                display: block;           /* Đảm bảo hoạt động đúng */
            }
            .limited-text-2 {
                white-space: nowrap;      /* Ngăn nội dung xuống dòng */
                overflow: hidden;         /* Ẩn phần nội dung dư */
                text-overflow: ellipsis;  /* Thêm dấu "..." */
                max-width: 250px;         /* Giới hạn độ rộng */
                display: block;           /* Đảm bảo hoạt động đúng */

            }


        </style>
    </head>

    <body onload="console.log('${requestScope.mess}')">

        <%@ include file="header_manage.jsp" %>
        <div class="container mt-5" style="margin-top: 80px !important">
            <h2 class="text-center">📢 List of News</h2>
            <%-- Hiển thị thông báo error nếu có --%>
            <%String error = (String) session.getAttribute("error");
                if (error != null) { 
            %>
            <div class="alert alert-success" style="color:red !important; background-color: pink !important"><%= error %></div>
            <%
            session.removeAttribute("error"); // Xóa thông báo sau khi hiển thị
            }
            %>
            <%-- Hiển thị thông báo mess nếu có --%>
            <%String mess = (String) session.getAttribute("mess");
                if (mess != null) { 
            %>
            <div class="alert alert-success"><%= mess %></div>
            <%
            session.removeAttribute("mess"); // Xóa thông báo sau khi hiển thị
            }
            %>
            <a href="create_news.jsp" class="btn btn-success mb-3">📝 Add News</a>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Title</th>
                        <th>Image</th>
                        <th>Information</th>
                        <th>Day created</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="news" items="${newsList}">
                        <tr>
                            <td class="limited-text">${news.title}</td>
                            <td class="text-center"><img src="${news.getPhotoUrl()}" width="120px" alt="Image"></td>
                            <td class="limited-text-2">${news.content}</td>
                            <td>${news.createdDate}</td>
                            <td>
                                <a href="ManageNews?action=edit&id=${news.postId}" class="btn btn-warning btn-sm">✏️ Edit</a>
                                <a  class="btn btn-danger btn-sm" href="delete-news?id=${news.postId}" onclick="return confirm('Bạn có chắc chắn muốn xóa bài viết này?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
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

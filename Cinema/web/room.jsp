<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style-starter.css">
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <title>Chọn chi nhánh</title>
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
        <!-- movie selections-->
        <br>
        <br>
        <br>

        <h2 class="container">Chọn Phòng</h2>
        <br><!-- comment -->
        <br>

        <div class="d-flex justify-content-between flex-wrap container">
            <c:forEach items="${listrooms}" var="room">
                <div class="card branch-item" style="width:500px; margin-bottom:50px">
                    <img class="card-img-top img-branch"
                         src="${room.getImg()}"
                         alt="Card image" style="width:100%">
                    <div class="card-body">
                        <h4 class="card-title"> ${room.getRoom_name()} </h4>
                        <p class="card-text">Sức chứa: ${room.getCapacity()} người </p>
                        <a href="SeatController?mid=${requestScope.mid}&branchId=${requestScope.branchId}&startDate=${requestScope.startDate}&startTime=${requestScope.startTime}&roomId=${room.getRoom_id()}"
                           class="btn btn-outline-danger btn-block">Chọn</a>
                    </div>
                </div>
            </c:forEach>

        </div>

        <br>




        <!-- end of movie selections -->
        <br>
        <br>
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


<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>-->

<script>
    document.addEventListener("DOMContentLoaded", function () {
        updateTimes(); // Gọi khi trang tải xong
    });

    function updateTimes() {
        var selectedDate = document.getElementById("listDate").value;
        var listTimes = document.getElementById("listTimes");

        // Xóa tất cả các option cũ
        listTimes.innerHTML = "";



        console.log(showtimes);
        console.log(selectedDate);

        // Kiểm tra nếu có danh sách suất chiếu
        if (!showtimes || showtimes.length === 0) {
            return;
        }
        function formatDate(dateStr) {
            let dateObj = new Date(Date.parse(dateStr));
            return dateObj.toLocaleDateString("en-CA"); // YYYY-MM-DD
        }



        console.log(formatDate("Oct 18, 2024"));




        // Lọc danh sách giờ chiếu theo ngày đã chọn
        var filteredTimes = showtimes.filter(st => formatDate(st.date) === selectedDate);

        // Thêm các giờ chiếu vào dropdown
        filteredTimes.forEach(st => {
            var option = document.createElement("option");
            option.value = st.time;
            option.textContent = st.time;
            listTimes.appendChild(option);
        });

        console.log(filteredTimes.length);



        // Nếu không có suất chiếu nào, thêm thông báo
        if (filteredTimes.length === 0) {
            var option = document.createElement("option");
            option.textContent = "Không có suất chiếu";
            listTimes.appendChild(option);
        }
    }
</script>



<script src="assets/js/bootstrap.min.js"></script>
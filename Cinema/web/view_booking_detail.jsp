<%-- 
    Document   : booking_detail
    Created on : Mar 6, 2025, 11:10:02 PM
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
        <title>View Booking Detail</title>

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
                box-shadow:10px 0px 15px lightgrey;
                margin: 20px 0 20px 0;
                height: 30vh;
                width: 70vw;
            }
            .mess{
                width: 100vw;
                text-align: center;
                height: 28px;
            }
            .form-button{
                display: inline-block;
                width: auto;
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
                padding: 15px 25px 5px 30px;
            }
            .field-value{
                display: grid;
                grid-template-columns: auto auto auto;
                width: 100%;
                padding: 10px 6px 4px 6px;
                color: black;
                background-color: whitesmoke;
                border: 2px solid grey;
                border-radius: 7px;
                justify-content: space-evenly;
                justify-items: center;
            }
            .field-value p{
                display: inline;
                padding: 3px;
                width: 100%;
            }
            .field-value img{
                display: inline;
                padding: 3px;
                height: 30vh;
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

        </style>
    </head>

    <body>

        <%@ include file="header.jsp" %>

        <div class="maincontent">
            <c:choose>
                <c:when test="${requestScope.mess != null}">
                    <p class="mess" id="form-mess">${requestScope.mess}</p>
                </c:when>
                <c:otherwise>
                    <p class="mess">     </p>
                </c:otherwise>
            </c:choose>
            <form  action="user_profile" method="post">
                <input type="hidden" name="service" value="changePassword">
                <table>
                    <tbody>
                        <tr>
                    <a class="form-button" href="user_profile?service=listUserBooking">Back to History</a>
                    </tr>
                    <tr>
                        <td class="field">
                            <div class="field-value">
                                <img style="grid-row: 1 / span 5;" src="${userBooking.poster_url}" alt="${userBooking.title}"/>
                                <p><em style="font-size: 1.2em">${userBooking.title}</em></p>
                                <p><em>Create at:</em> ${userBooking.booking_date}</p>
                                <p><em>Theater:</em> ${userBooking.theater_name}</p>
                                <p><em>Room:</em> ${userBooking.room_name}</p>
                                <p style="grid-column: 2 / span 2"><em>Showtime:</em> ${userBooking.showtime}</p>
                                <p><em>Number of Tickets:</em> ${userBooking.num_of_tickets}</p>
                                <p><em>Number of Combos:</em> ${userBooking.num_of_combos}</p>
                                <p><em>Seats:</em> ${userBooking.seats_info}</p>
                                <p><em>Combos:</em> ${userBooking.combos_info}</p>
                                <p><em>Sub Total Amount:</em> ${userBooking.sub_total_amount}VND</p>
                                <p><em>Coupon:</em> ${userBooking.coupon_code} ${userBooking.discount_percentage}%</p>
                                <p><em>Total Amount:</em> ${userBooking.total_amount}VND</p>
                                <p style="text-align: end; align-content: center;"><em>QR Code:</em></p>
                                <div id="qrcode" style="grid-column: 2 / span 2;"></div>
                            </div> 
                        </td>
                    </tr>
                    </tbody>
                </table>

            </form>
        </div>

        <%@ include file="footer.jsp" %>
        
    </body>

</html>

<script src=
        "https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js">
</script>
<script>
    var qrcode = new QRCode("qrcode",
            "${userBooking.booking_id},${userBooking.user_id},${userBooking.showtime_id}");
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
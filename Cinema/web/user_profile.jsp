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

        <%@ include file="header.jsp" %>

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
                            <td class="field"><input class="field-value" type="date" name="birth_date" value="${sessionScope.acc.getBirth_date()}" min="1900-01-01" readonly required></td>
                        </tr>
                        <tr>
                            <td class="field-name">Email</td>
                            <td class="field-name">Phone number</td>
                        </tr>
                        <tr>
                            <td class="field"><input class="field-value" type="email" name="email" value="${sessionScope.acc.getEmail()}" readonly required></td>
                            <td class="field"><input class="field-value" type="number" name="phone" value="${sessionScope.acc.getPhone()}" minlength="10" maxlength="10" readonly required></td>
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
                                                <option value="${theater.getTheater_id()}" selected>${theater.getTheater_name()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${theater.getTheater_id()}">${theater.getTheater_name()}</option>
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

        <%@ include file="footer.jsp" %>
        
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
                if (i == 1 || i == 2 || i == 4 || i == 6) {
                    info[i] = oldInfo[i].value;
                    oldInfo[i].readOnly = false;
                    oldInfo[i].style = unlock;
                }
            }
            infoSelect = oldInfoSelect[0].value;
            oldInfoSelect[0].disabled = false;
            oldInfoSelect[0].style = unlock;
            button.innerHTML = "Cancel";
            submit.hidden = false;
        } else {
            for (let i = 1; i < 5; i++) {
                if (i == 1 || i == 2 || i == 4 || i == 6) {
                    oldInfo[i].value = info[i];
                    oldInfo[i].readOnly = true;
                    oldInfo[i].style = lock;
                }
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


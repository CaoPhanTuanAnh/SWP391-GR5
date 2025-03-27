<%-- 
    Document   : sign_in
    Created on : Feb 8, 2025, 4:00:50 PM
    Author     : bolac
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ResetPassword</title>
        <link rel="stylesheet" type="text/css" href="assets/css/as-alert-message.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style-starter.css">
        <link rel="stylesheet" type="text/css" href="assets/css/sign-in.css">
    </head>

    <body>
        <header id="site-header" class="w3l-header fixed-top">
            <!--/nav-->
            <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
                <div class="container">
                    <h1><a class="navbar-brand" href="HomePageController"><span class="fa fa-play icon-log"
                                                                                aria-hidden="true"></span>
                            MyShowz </a></h1>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
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

        <div class="container_signup_signin" id="container_signup_signin">
            <div class="form-container sign-in-container">
                <form style="color: var(--theme-title);" action="verify_otp" method="post">
                    <h2>Verify OTP</h2>
                    <c:if test="${not empty mess}">
                        <p style="color: red;">${mess}</p>
                    </c:if>
                        <input type="text" name="otp" placeholder="Enter OTP" required>
                        <button type="submit">Verify</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Check Email To Verify OTP</h1>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="assets/js/as-alert-message.min.js"></script>
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <!--/theme-change-->
        <script src="assets/js/theme-change.js"></script>
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

        <script type="text/javascript" src="assets/js/sign-in.js"></script>

    </body>

</html>
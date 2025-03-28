<%-- 
    Document   : footer
    Created on : Mar 29, 2025, 12:04:56 AM
    Author     : GIGABYTE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="w3l-footer">
    <section class="footer-inner-main">
        <div class="footer-hny-grids py-5">
            <div class="container py-lg-4">
                <div class="text-txt">
                    <div class="right-side">
                        <div class="row footer-about">
                            <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner1.jpg"
                                                          alt=""></a>
                            </div>
                            <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner2.jpg"
                                                          alt=""></a>
                            </div>
                            <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner3.jpg"
                                                          alt=""></a>
                            </div>
                            <div class="col-md-3 col-6 footer-img mb-lg-0 mb-4">
                                <a href="movies.jsp"><img class="img-fluid" src="assets/images/banner4.jpg"
                                                          alt=""></a>
                            </div>
                        </div>
                        <div class="row footer-links">
                            <div class="col-md-6 col-sm-6 sub-two-right mt-5">
                                <h6>Information</h6>
                                <ul>
                                    <li><a href="HomePageController">Home</a> </li>
                                    <li><a href="about.jsp">About</a> </li>
                                    <li><a href="sign_in.jsp">Login</a></li>
                                    <li><a href="Contact_Us.jsp">Contact Us</a></li>
                                </ul>
                            </div>
                            <div class="col-md-6 col-sm-6 sub-two-right mt-5">
                                <h6>Movies</h6>
                                <ul>
                                    <li><a href="ViewAllController">Find Movies</a></li>
                                    <li><a href="HomePageController#presenting">Presenting Movies</a></li>
                                    <li><a href="HomePageController#upcoming">Upcoming Movies</a></li>
                                    <li><a href="NewsListServlet">Movie News</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="below-section">
            <div class="container">
                <div class="copyright-footer">
                    <div class="columns text-lg-left">
                        <p>&copy; 2025 MyShowz. All rights reserved</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- move top -->
        <button onclick="topFunction()" id="movetop" title="Go to top">
            <span class="fa fa-arrow-up" aria-hidden="true"></span>
        </button>
        <script>
            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction()
            };

            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    document.getElementById("movetop").style.display = "block";
                } else {
                    document.getElementById("movetop").style.display = "none";
                }
            }

            // When the user clicks on the button, scroll to the top of the document
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
        </script>
        <!-- /move top -->

    </section>
</footer>

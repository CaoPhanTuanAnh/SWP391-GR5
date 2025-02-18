<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="">
        <meta name="author" content="">
        <meta name="robots" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Fillow : Fillow Saas Admin  Bootstrap 5 Template">
        <meta property="og:title" content="Fillow : Fillow Saas Admin  Bootstrap 5 Template">
        <meta property="og:description" content="Fillow : Fillow Saas Admin  Bootstrap 5 Template">
        <meta property="og:image" content="https://fillow.dexignlab.com/xhtml/social-image.png">
        <meta name="format-detection" content="telephone=no">

        <!-- PAGE TITLE HERE -->
        <title>Admin Dashboard</title>

        <!-- FAVICONS ICON -->
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">
        <!-- Form step -->
        <link href="vendor/jquery-smartwizard/dist/css/smart_wizard.min.css" rel="stylesheet">
        <!-- Custom Stylesheet -->
        <link href="vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

    </head>

    <body>

        <!--*******************
            Preloader start
        ********************-->
        <div id="preloader">
            <div class="lds-ripple">
                <div></div>
                <div></div>
            </div>
        </div>
        <!--*******************
            Preloader end
        ********************-->


        <!--**********************************
            Main wrapper start
        ***********************************-->
        <div id="main-wrapper">


            <c:import url="./header.jsp" />
            <c:import url="./notification.jsp" />
            <!--**********************************
                Content body start
            ***********************************-->
            <div class="content-body">
                <div class="container-fluid">
                    <div class="row page-titles">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active"><a href="javascript:void(0)">Home</a></li>
                            <li class="breadcrumb-item"><a href="javascript:void(0)">Components</a></li>
                        </ol>
                    </div>
                    <!-- row -->
                    <div class="row">
                        <div class="col-xl-12 col-xxl-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Detail</h4>

                                    <div class="col-lg-12 text-center mt-3">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal">
                                            Chỉnh sửa
                                        </button>
                                    </div>

                                </div>
                                <div class="card-body">



                                    <!-- View account detail -->
                                    <div class="row">



                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Role*</label>
                                                <input value="${userDetail.getRole()}" type="text" name="role" class="form-control" required=""readonly >
                                            </div>
                                        </div>

                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Username*</label>
                                                <input value="${userDetail.getUsername()}" type="text" name="username" class="form-control" required=""readonly >
                                            </div>
                                        </div>

                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Full Name*</label>
                                                <input value="${userDetail.getFullname()}" type="text" name="fullname" class="form-control" required=""readonly >
                                            </div>
                                        </div>

                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Email*</label>
                                                <input value="${userDetail.getEmail()}" type="email" name="email" class="form-control" required=""readonly >
                                            </div>
                                        </div>

                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Phone*</label>
                                                <input value="${userDetail.getPhone()}" type="text" name="phone" class="form-control" required=""readonly >
                                            </div>
                                        </div>

                                        <div class="col-lg-6 mb-2">
                                            <div class="mb-3">
                                                <label class="text-label form-label">Address*</label>
                                                <input value="${userDetail.getAddress()}" type="text" name="address" class="form-control" required=""readonly >
                                            </div>
                                        </div>




                                    </div>




                                    <!-- Modal edit account -->
                                    <div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editUserModalLabel">Chỉnh sửa thông tin</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form id="editUserForm" action="users?action=edit" method="post">

                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label class="form-label">Full Name</label>
                                                            <input type="text" id="editFullname" name="fullname" class="form-control" value="${userDetail.getFullname()}">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Email</label>
                                                            <input type="email" id="editEmail" name="email" class="form-control" value="${userDetail.getEmail()}">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Phone</label>
                                                            <input type="text" id="editPhone" name="phone" class="form-control" value="${userDetail.getPhone()}">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Address</label>
                                                            <input type="text" id="editAddress" name="address" class="form-control" value="${userDetail.getAddress()}">
                                                        </div>

                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                        <button  type="submit" class="btn btn-success" onclick="saveUser()">Lưu</button>
                                                    </div>
                                                </form>

                                            </div>
                                        </div>
                                    </div>



                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--**********************************
                Content body end
            ***********************************-->


            <!--**********************************
                Footer start
            ***********************************-->
            <div class="footer">
                <div class="copyright">
                    <p>Copyright © Designed &amp; Developed by <a href="../index.htm" target="_blank">DexignLab</a> 2021</p>
                </div>
            </div>
            <!--**********************************
                Footer end
            ***********************************-->

            <!--**********************************
               Support ticket button start
            ***********************************-->

            <!--**********************************
               Support ticket button end
            ***********************************-->


        </div>
        <!--**********************************
            Main wrapper end
        ***********************************-->

        <!--**********************************
            Scripts
        ***********************************-->
        <!-- Required vendors -->
        <script src="vendor/global/global.min.js"></script>

        <script src="vendor/jquery-steps/build/jquery.steps.min.js"></script>
        <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
        <!-- Form validate init -->
        <script src="js/plugins-init/jquery.validate-init.js"></script>


        <!-- Form Steps -->
        <script src="vendor/jquery-smartwizard/dist/js/jquery.smartWizard.js"></script>
        <script src="vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>

        <script src="js/custom.min.js"></script>
        <script src="js/dlabnav-init.js"></script>
        <script src="js/demo.js"></script>
        <script src="js/styleSwitcher.js"></script>
        <script>
                                                            $(document).ready(function () {
                                                                // SmartWizard initialize
                                                                $('#smartwizard').smartWizard();
                                                            });
        </script>

    </body>

</html>
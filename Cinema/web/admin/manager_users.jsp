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
        <!-- Datatable -->
        <link href="vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
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


            <!--**********************************
                Content body start
            ***********************************-->
            <div class="content-body">
                <div class="container-fluid">

                    <div class="row page-titles">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item active"><a href="javascript:void(0)">Table</a></li>
                            <li class="breadcrumb-item"><a href="javascript:void(0)">Datatable</a></li>
                        </ol>
                    </div>
                    <!-- row -->


                    <div class="row">

                        <!<!-- List account -->
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Manager Users  </h4>
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createUserModal">
                                            Create User
                                        </button>
                                    </div>

                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table id="example4" class="display" style="min-width: 845px">
                                            <thead>
                                                <tr>
                                                    <th>Roll No</th>
                                                    <th>Student Name</th>
                                                    <th>Username</th>
                                                    <th>Email</th>
                                                    <th>Phone </th>
                                                    <th>address</th>
                                                    <th>Status</th>
                                                    <th>Action</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="listUser" items="${listUser}" varStatus="loop">
                                                    <tr>
                                                        <td>${loop.index + 1}</td>
                                                        <td>${listUser.getFullname()}</td>
                                                        <td>${listUser.getUsername()}</td>
                                                        <td>${listUser.getEmail()}</td>
                                                        <td>${listUser.getPhone()}</td>
                                                        <td>${listUser.getAddress()}</td>
                                                        <td><span class="badge light badge-success">Paid</span></td>
                                                        <td>
                                                            <a href="users?action=view-detail&id=${listUser.getID()}">View Detail</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>






                            <!-- Create account User Modal -->
                            <div class="modal fade" id="createUserModal" tabindex="-1" aria-labelledby="createUserModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="createUserModalLabel">Create New User</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="users?action=create" method="post">
                                                <div class="row g-3">
                                                    <!-- Cột 1 -->
                                                    <div class="col-md-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Full Name</label>
                                                            <input type="text" name="fullname" class="form-control" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Username</label>
                                                            <input type="text" name="username" class="form-control" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Email</label>
                                                            <input type="email" name="email" class="form-control" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Phone</label>
                                                            <input type="text" name="phone" class="form-control" required>
                                                        </div>
                                                    </div>

                                                    <!-- Cột 2 -->
                                                    <div class="col-md-6">
                                                        <div class="mb-3">
                                                            <label class="form-label">Address</label>
                                                            <input type="text" name="address" class="form-control">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Role</label>
                                                            <select name="role" class="form-control">
                                                                <option value="User">User</option>
                                                                <option value="Admin">Admin</option>
                                                            </select>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Password</label>
                                                            <input type="password" name="password" class="form-control" required>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Footer -->
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Create User</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>














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
        <script src="vendor/chart.js/Chart.bundle.min.js"></script>
        <!-- Apex Chart -->
        <script src="vendor/apexchart/apexchart.js"></script>

        <!-- Datatable -->
        <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="js/plugins-init/datatables.init.js"></script>

        <script src="vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>

        <script src="js/custom.min.js"></script>
        <script src="js/dlabnav-init.js"></script>
        <script src="js/demo.js"></script>
        <script src="js/styleSwitcher.js"></script>
    </body>
</html>
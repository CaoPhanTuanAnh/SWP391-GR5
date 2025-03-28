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
        <link href="//fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;1,600&display=swap"
              rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .screen-container {
                perspective: 1000px;
                margin: 40px 0;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }

            .screen {
                background: rgb(71, 165, 209);
                height: 70px;
                width: 70%;
                margin: 15px 0;
                transform: rotateX(-45deg);
                box-shadow: 0 3px 10px rgba(19, 120, 145, 0.7);
            }

            input.largerCheckbox {
                width: 80px;
                height: 80px;
                cursor: pointer;
            }
        </style>
        <title>Chọn chi nhánh</title>
        <style>
            .img-branch {
                height: 400px;
            }

            .branch-item {
                margin-bottom: 50px;
            }
        </style>
    </head>

    <body>
        <!-- nav bar -->

        <!-- end of navbar -->

        <!-- movie selections-->
        <br>
        <br>
        <br>

        <div class="container">
            <h1>Chọn Chỗ Ngồi</h1>
        </div>
        <div class="screen-container">
            <h2>Màn Hình</h2>
            <div class="screen"></div>
            <br><br><br>
            <p style="color: red">
                ${bookedError}
            </p>
            <div class="container">
                <form action="BillController" method="post">
                    <table style="width:100%">
                        <tr>
                            <th></th>
                            <th>1</th> <th>2</th> <th>3</th> <th>4</th> <th>5</th> <th>6</th> <th>7</th> <th>8</th>
                        </tr>

                        <tr>
                            <th>A</th>
                                <c:forEach items="${listA}" var="seat">
                                    <c:choose>
                                        <c:when test="${seat.getStatus() eq 'Booked'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Unavailable'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Available'}">
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>

                        <tr>
                            <th>B</th>
                                <c:forEach items="${listB}" var="seat">
                                    <c:choose>
                                        <c:when test="${seat.getStatus() eq 'Booked'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Unavailable'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Available'}">
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>


                        <tr>
                            <th>C</th>
                                <c:forEach items="${listC}" var="seat">
                                    <c:choose>
                                        <c:when test="${seat.getStatus() eq 'Booked'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Unavailable'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Available'}">
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>

                        <tr>
                            <th>D</th>
                                <c:forEach items="${listD}" var="seat">
                                    <c:choose>
                                        <c:when test="${seat.getStatus() eq 'Booked'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Unavailable'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Available'}">
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>

                        <tr>
                            <th>E</th>
                                <c:forEach items="${listE}" var="seat">
                                    <c:choose>
                                        <c:when test="${seat.getStatus() eq 'Booked'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Unavailable'}">
                                        <th style="color: red; font-weight: bold;">❌</th>
                                        </c:when>
                                        <c:when test="${seat.getStatus() eq 'Available'}">
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:when>
                                    <c:otherwise>
                                        <th>
                                            <input type="checkbox" class="largerCheckbox" name="seats" value="${seat.getSeat_id()}">
                                        </th>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>

                        <!-- Lặp lại cho các hàng C, D, E -->
                    </table>
                    <input type="text" name="mid" value="${requestScope.mid}" style="display: none;">
                    <input type="text" name="branchId" value="${requestScope.branchId}" style="display: none;">
                    <input type="text" name="startDate" value="${requestScope.startDate}" style="display: none;">
                    <input type="text" name="startTime" value="${requestScope.startTime}" style="display: none;">
                    <input type="text" name="roomId" value="${requestScope.roomId}" style="display: none;">
                    <br>
                    <br>
                    <input type="submit" class="btn btn-outline-danger btn-block" value="Tiếp Tục"></input>
                </form>
            </div>
        </div>

    </body>

</html>

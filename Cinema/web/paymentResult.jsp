<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kết quả giao dịch</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" 
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
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

    <body style="background-color: #f4f4f4; font-family: Arial, sans-serif; margin: 0; padding: 20px;">
        
        <a href="HomePageController">Back to home page</a>

        <section style="margin-top: 50px; text-align: center;">



            <div>
                <h3 style="font-weight: bold; color: #28a745;">
                    Bạn đã giao dịch thành công! 
                    <i class="fas fa-check-circle"></i>
                </h3>
                <p style="font-size: 18px; margin-top: 15px;">Vui lòng để ý số điện thoại của nhân viên tư vấn:</p>
                <strong style="color: red; font-size: 24px;">0383459560</strong>
            </div>


        </section>

    </body>
</html>

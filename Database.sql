Create database CinemaTicketBooking
GO
USE CinemaTicketBooking;
GO
-- Bảng roles (Vai trò)
CREATE TABLE roles (
    role_id INT PRIMARY KEY IDENTITY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);
-- Bảng users (Người dùng)
CREATE TABLE users (
    user_id INT PRIMARY KEY IDENTITY,
    role_id INT FOREIGN KEY REFERENCES roles(role_id),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE()
);


-- Bảng genres (Thể loại)
CREATE TABLE genres (
    genre_id INT PRIMARY KEY IDENTITY,
    genre_name NVARCHAR(100) NOT NULL UNIQUE
);

-- Bảng movies (Phim)
CREATE TABLE movies (
    movie_id INT PRIMARY KEY IDENTITY,
    title NVARCHAR(255) NOT NULL,
    genre_id INT FOREIGN KEY REFERENCES genres(genre_id),
    director NVARCHAR(255),
    actors NVARCHAR(MAX),
    description NVARCHAR(MAX),
    trailer_url VARCHAR(255),
    poster_url VARCHAR(255),
    duration INT NOT NULL,
    release_date DATE,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE()
);


-- Bảng cities (Thành phố)
CREATE TABLE cities (
    city_id INT PRIMARY KEY IDENTITY,
    city_name NVARCHAR(100) NOT NULL UNIQUE
);
-- Bảng theaters (Rạp)
CREATE TABLE theaters (
    theater_id INT PRIMARY KEY IDENTITY,
    city_id INT FOREIGN KEY REFERENCES cities(city_id),
    theater_name NVARCHAR(255) NOT NULL,
    address NVARCHAR(255) NOT NULL
);


-- Bảng rooms (Phòng chiếu)
CREATE TABLE rooms (
    room_id INT PRIMARY KEY IDENTITY,
    theater_id INT FOREIGN KEY REFERENCES theaters(theater_id),
    room_name NVARCHAR(50) NOT NULL,
    capacity INT NOT NULL
);

-- Bảng seats (Chỗ ngồi)
CREATE TABLE seats (
    seat_id INT PRIMARY KEY IDENTITY,
    room_id INT FOREIGN KEY REFERENCES rooms(room_id),
    seat_row NVARCHAR(10) NOT NULL,
    seat_number INT NOT NULL
);

-- Bảng showtimes (Suất chiếu)
CREATE TABLE showtimes (
    showtime_id INT PRIMARY KEY IDENTITY,
    movie_id INT FOREIGN KEY REFERENCES movies(movie_id),
    room_id INT FOREIGN KEY REFERENCES rooms(room_id),
    showtime DATETIME NOT NULL
);

-- Bảng tickets (Vé)
CREATE TABLE tickets (
    ticket_id INT PRIMARY KEY IDENTITY,
    showtime_id INT FOREIGN KEY REFERENCES showtimes(showtime_id),
    seat_id INT FOREIGN KEY REFERENCES seats(seat_id),
    user_id INT FOREIGN KEY REFERENCES users(user_id),
    booking_date DATE DEFAULT GETDATE(),
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL
);
-- Bảng coupons (Mã giảm giá)
CREATE TABLE coupons (
    coupon_id INT PRIMARY KEY IDENTITY,
    coupon_code VARCHAR(50) UNIQUE NOT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL,
    expiry_date DATE NOT NULL
);

-- Bảng bookings (Đơn đặt vé)
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY IDENTITY,
    user_id INT FOREIGN KEY REFERENCES users(user_id),
    booking_date DATE DEFAULT GETDATE(),
    total_amount DECIMAL(10,2) NOT NULL,
    coupon_id INT NULL FOREIGN KEY REFERENCES coupons(coupon_id)
);

-- Bảng booking_items (Chi tiết đơn đặt vé)
CREATE TABLE booking_items (
    booking_item_id INT PRIMARY KEY IDENTITY,
    booking_id INT FOREIGN KEY REFERENCES bookings(booking_id),
    ticket_id INT FOREIGN KEY REFERENCES tickets(ticket_id),
    quantity INT NOT NULL
);



-- Bảng reviews (Đánh giá phim)
CREATE TABLE reviews (
    review_id INT PRIMARY KEY IDENTITY,
    user_id INT FOREIGN KEY REFERENCES users(user_id),
    movie_id INT FOREIGN KEY REFERENCES movies(movie_id),
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(MAX),
    created_at DATETIME DEFAULT GETDATE()
);

-- Bảng notifications (Thông báo)
CREATE TABLE notifications (
    notification_id INT PRIMARY KEY IDENTITY,
    user_id INT FOREIGN KEY REFERENCES users(user_id),
    message NVARCHAR(MAX) NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    is_read BIT DEFAULT 0
);

-- Bảng reports (Báo cáo)	
CREATE TABLE reports (
    report_id INT PRIMARY KEY IDENTITY,
    report_name NVARCHAR(255) NOT NULL,
    report_data NVARCHAR(MAX) NOT NULL
);

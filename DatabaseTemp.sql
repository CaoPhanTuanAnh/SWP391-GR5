USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'CinemaTicketBooking')
BEGIN
	ALTER DATABASE CinemaTicketBooking SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE CinemaTicketBooking SET ONLINE;
	DROP DATABASE CinemaTicketBooking;
END

GO

CREATE DATABASE CinemaTicketBooking
GO

USE CinemaTicketBooking
GO


-- Phan loai vai tro cua users
CREATE TABLE roles (
    role_id INT PRIMARY KEY IDENTITY,
    role_name NVARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO roles (role_name) VALUES
(N'Admin'),
(N'Manager'),
(N'Customer');
GO


-- Thanh pho
CREATE TABLE cities (
    city_id INT PRIMARY KEY IDENTITY,
    city_name NVARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO cities(city_name) VALUES
(N'Hà Nội'),
(N'Hồ Chí Minh'),
(N'Đà Nẵng'),
(N'Hải Phòng'),
(N'Cần Thơ');
GO


-- Rap phim
CREATE TABLE theaters (
    theater_id INT PRIMARY KEY IDENTITY,
    city_id INT FOREIGN KEY REFERENCES cities(city_id) NOT NULL,
	director_id INT, -- La 1 manager cap cao cua rap phim, Cap nhat sau khi tao bang users
    theater_name NVARCHAR(255) NOT NULL,
    address NVARCHAR(255) NOT NULL
);
INSERT INTO theaters (city_id, theater_name, address) VALUES 
-- Theaters in Ha Noi
((SELECT city_id FROM cities WHERE city_name = N'Hà Nội'), N'SWP Vincom Bà Triệu', N'191 Ba Trieu, Hai Ba Trung, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Hà Nội'), N'SWP Aeon Hà Đông', N'72 Landmark Tower, Pham Hung, Nam Tu Liem, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Hà Nội'), N'SWP Phạm Ngọc Thạch', N'2 Pham Ngoc Thach, Dong Da, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Hà Nội'), N'SWP Giảng Võ', N'16B Giang Vo, Ba Dinh, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Hà Nội'), N'SWP Long Biên', N'910A Ngo Quyen, Son Tra, Da Nang'),
-- Theaters in Ho Chi Minh
((SELECT city_id FROM cities WHERE city_name = N'Hồ Chí Minh'), N'SWP Crescent Mall', N'101 Ton Dat Tien, District 7, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Hồ Chí Minh'), N'SWP Cinema Diamond', N'34 Le Duan, District 1, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Hồ Chí Minh'), N'SWP Star Bitexco', N'2 Hai Trieu, District 1, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Hồ Chí Minh'), N'SWP Cinema Nguyễn Du', N'116 Nguyen Du, District 1, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Hồ Chí Minh'), N'SWP Hoàng Văn Thụ', N'Tầng 1 và 2, Gala Center, số 415, Hoàng Văn Thụ, Phường 2, Quận Tân Bình, TPHCM'),
-- Theaters in Da Nang
((SELECT city_id FROM cities WHERE city_name = N'Đà Nẵng'), N'SWP Dien Bien Phu', N'46 Dien Bien Phu, Thanh Khe, Da Nang'),
((SELECT city_id FROM cities WHERE city_name = N'Đà Nẵng'), N'SWP Nguyen Van Linh', N'271 Nguyen Van Linh, Hai Chau, Da Nang'),
((SELECT city_id FROM cities WHERE city_name = N'Đà Nẵng'), N'SWP Ton Duc Thang', N'171 Ton Duc Thang, Lien Chieu, Da Nang');
GO


-- Nguoi dung
CREATE TABLE users (
    user_id INT PRIMARY KEY IDENTITY,
    role_id INT FOREIGN KEY REFERENCES roles(role_id) NOT NULL DEFAULT(3),
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(16) NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    phone CHAR(10),
    birth_date DATE NOT NULL,
	theater_id INT FOREIGN KEY REFERENCES theaters(theater_id), -- Doi voi customer, day la rap phim thuong den, Doi voi manager va admin, day la noi lam viec
	status NVARCHAR(20) NOT NULL DEFAULT(N'Active') -- Active: TK con hoat dong, Banned: bi ban
);
INSERT INTO users (role_id, username, password, full_name, email, phone, birth_date, theater_id) VALUES
-- tam thoi tat ca user se tap trung tai 1 rap, tru 2 manager
--  Admins
(1, 'admin', '1', N'Nguyễn Văn Hùng', 'nguyen.hung@cinema.com', '0912345678', '2000-10-12', 1),
(1, 'mai.tran', 'Admin@1992', N'Mai Thị Trân', 'mai.tran@cinema.com', '0923456789', '2000-11-22', 1),
--  Managers
(2, 'manager', '1', N'Phạm Thúy', 'pham.thuy@cinema.com', '0934567890', '2000-01-02', 1),
(2, 'manager2', '1', N'Lê Dương', 'le.duong@cinema.com', '0945678901', '2000-04-15', 1),
(2, 'hoang.minh', 'Manager123!', N'Hoàng Minh', 'hoang.minh@cinema.com', '0956789012', '2000-10-17', 1),
(2, 'do.bao', 'Mng@1995', N'Đỗ Bảo', 'do.bao@cinema.com', '0967890123', '2000-12-22', 2),
(2, 'nguyen.hoa', 'Manager!2024', N'Nguyễn Hoa', 'nguyen.hoa@cinema.com', '0978901234', '2000-05-23', 3),
--  Customers
(3, 'customers', '1', N'Trần Khoa', 'tran.khoa@cinema.com', '0989012345', '2000-03-14', 1),
(3, 'le.ha', 'Ha@2024', N'Lê Hà', 'le.ha@cinema.com', '0990123456', '2000-10-05', 1),
(3, 'dang.trung', 'Trung!567', N'Đặng Trung', 'dang.trung@cinema.com', '0901234567', '2000-08-07', 1),
(3, 'vo.hoa', 'HoaMovie@88', N'Võ Hoa', 'vo.hoa@cinema.com', '0912345670', '2000-09-06', 1),
(3, 'bui.quang', 'QuangFan@99', N'Bùi Quang', 'bui.quang@cinema.com', '0923456781', '2000-06-12', 1),
(3, 'nguyen.tram', 'Tram!Cinema', N'Nguyễn Trâm', 'nguyen.tram@cinema.com', '0934567892', '2000-04-09', 1),
(3, 'hoang.thanh', 'ThanhMovie123', N'Hoàng Thanh', 'hoang.thanh@cinema.com', '0945678903', '2000-03-17', 1),
(3, 'pham.lan', 'Lan@Cinema!', N'Phạm Lan', 'pham.lan@cinema.com', '0956789014', '2000-10-12', 1);
GO


-- Cap nhat lai theater (them fk cho direactor)
ALTER TABLE theaters
ADD FOREIGN KEY (director_id)
REFERENCES users (user_id);
UPDATE theaters
SET director_id = 3
where city_id = 1;
UPDATE theaters
SET director_id = 6
where city_id = 2;
UPDATE theaters
SET director_id = 7
where city_id = 3;
GO


-- Phan loai rooms
CREATE TABLE types (
    type_id INT PRIMARY KEY IDENTITY,
    type_name NVARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO types(type_name) VALUES
(N'2D'),
(N'3D');
GO


-- Phong chieu
CREATE TABLE rooms (
    room_id INT PRIMARY KEY IDENTITY,
    theater_id INT FOREIGN KEY REFERENCES theaters(theater_id) NOT NULL,
	manager_id INT FOREIGN KEY REFERENCES users(user_id) NOT NULL,
    room_name NVARCHAR(50) NOT NULL,
	type_id INT FOREIGN KEY REFERENCES types(type_id) NOT NULL DEFAULT(1)
);
INSERT INTO rooms (theater_id, manager_id, room_name)
SELECT t.theater_id, t.director_id, CONCAT(N'Room ', r)
FROM theaters t, 
(SELECT 1 AS r UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION 
 SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8) AS room_numbers;
GO


-- Phan loai seats
CREATE TABLE levels (
    level_id INT PRIMARY KEY IDENTITY,
    level_name NVARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO levels(level_name) VALUES
(N'Normal'),
(N'VIP'),
(N'Deluxe');
GO


-- Ghe
CREATE TABLE seats (
    seat_id INT PRIMARY KEY IDENTITY,
    room_id INT FOREIGN KEY REFERENCES rooms(room_id),
	level_id INT FOREIGN KEY REFERENCES levels(level_id),
    seat_row NVARCHAR(10) NOT NULL,
    seat_number INT NOT NULL,
	status NVARCHAR(10) NOT NULL DEFAULT('Available') -- Available: con co the su dung, Unavailable: Bi hong
);
INSERT INTO seats (room_id, seat_row, seat_number)
SELECT r.room_id, s.seat_row, s.seat_number
FROM rooms r,
(SELECT N'A' AS seat_row, 1 AS seat_number UNION ALL SELECT N'A', 2 UNION ALL SELECT N'A', 3 UNION ALL SELECT N'A', 4 UNION ALL
 SELECT N'A', 5 UNION ALL SELECT N'A', 6 UNION ALL SELECT N'A', 7 UNION ALL SELECT N'A', 8 UNION ALL SELECT N'A', 9 UNION ALL 
 SELECT N'A', 10 UNION ALL SELECT N'A', 11 UNION ALL SELECT N'A', 12 UNION ALL

 SELECT N'B', 1 UNION ALL SELECT N'B', 2 UNION ALL SELECT N'B', 3 UNION ALL SELECT N'B', 4 UNION ALL
 SELECT N'B', 5 UNION ALL SELECT N'B', 6 UNION ALL SELECT N'B', 7 UNION ALL SELECT N'B', 8 UNION ALL
 SELECT N'B', 9 UNION ALL SELECT N'B', 10 UNION ALL SELECT N'B', 11 UNION ALL SELECT N'B', 12 UNION ALL

 SELECT N'C', 1 UNION ALL SELECT N'C', 2 UNION ALL SELECT N'C', 3 UNION ALL SELECT N'C', 4 UNION ALL
 SELECT N'C', 5 UNION ALL SELECT N'C', 6 UNION ALL SELECT N'C', 7 UNION ALL SELECT N'C', 8 UNION ALL
 SELECT N'C', 9 UNION ALL SELECT N'C', 10 UNION ALL SELECT N'C', 11 UNION ALL SELECT N'C', 12 UNION ALL

 SELECT N'D', 1 UNION ALL SELECT N'D', 2 UNION ALL SELECT N'D', 3 UNION ALL SELECT N'D', 4 UNION ALL
 SELECT N'D', 5 UNION ALL SELECT N'D', 6 UNION ALL SELECT N'D', 7 UNION ALL SELECT N'D', 8 UNION ALL
 SELECT N'D', 9 UNION ALL SELECT N'D', 10 UNION ALL SELECT N'D', 11 UNION ALL SELECT N'D', 12 UNION ALL

 SELECT N'E', 1 UNION ALL SELECT N'E', 2 UNION ALL SELECT N'E', 3 UNION ALL SELECT N'E', 4 UNION ALL
 SELECT N'E', 5 UNION ALL SELECT N'E', 6 UNION ALL SELECT N'E', 7 UNION ALL SELECT N'E', 8 UNION ALL
 SELECT N'E', 9 UNION ALL SELECT N'E', 10 UNION ALL SELECT N'E', 11 UNION ALL SELECT N'E', 12 UNION ALL

 SELECT N'F', 1 UNION ALL SELECT N'F', 2 UNION ALL SELECT N'F', 3 UNION ALL SELECT N'F', 4 UNION ALL
 SELECT N'F', 5 UNION ALL SELECT N'F', 6 UNION ALL SELECT N'F', 7 UNION ALL SELECT N'F', 8 UNION ALL
 SELECT N'F', 9 UNION ALL SELECT N'F', 10 UNION ALL SELECT N'F', 11 UNION ALL SELECT N'F', 12 UNION ALL

 SELECT N'G', 1 UNION ALL SELECT N'G', 2 UNION ALL SELECT N'G', 3 UNION ALL SELECT N'G', 4 UNION ALL
 SELECT N'G', 5 UNION ALL SELECT N'G', 6 UNION ALL SELECT N'G', 7 UNION ALL SELECT N'G', 8 UNION ALL
 SELECT N'G', 9 UNION ALL SELECT N'G', 10 UNION ALL SELECT N'G', 11 UNION ALL SELECT N'G', 12 UNION ALL

 SELECT N'H', 1 UNION ALL SELECT N'H', 2 UNION ALL SELECT N'H', 3 UNION ALL SELECT N'H', 4 UNION ALL
 SELECT N'H', 5 UNION ALL SELECT N'H', 6 UNION ALL SELECT N'H', 7 UNION ALL SELECT N'H', 8 UNION ALL
 SELECT N'H', 9 UNION ALL SELECT N'H', 10 UNION ALL SELECT N'H', 11 UNION ALL SELECT N'H', 12 UNION ALL

 SELECT N'I', 1 UNION ALL SELECT N'I', 2 UNION ALL SELECT N'I', 3 UNION ALL SELECT N'I', 4 UNION ALL
 SELECT N'I', 5 UNION ALL SELECT N'I', 6 UNION ALL SELECT N'I', 7 UNION ALL SELECT N'I', 8 UNION ALL
 SELECT N'I', 9 UNION ALL SELECT N'I', 10 UNION ALL SELECT N'I', 11 UNION ALL SELECT N'I', 12 UNION ALL

 SELECT N'J', 1 UNION ALL SELECT N'J', 2 UNION ALL SELECT N'J', 3 UNION ALL SELECT N'J', 4 UNION ALL
 SELECT N'J', 5 UNION ALL SELECT N'J', 6 UNION ALL SELECT N'J', 7 UNION ALL SELECT N'J', 8 UNION ALL
 SELECT N'J', 9 UNION ALL SELECT N'J', 10 UNION ALL SELECT N'J', 11 UNION ALL SELECT N'J', 12 UNION ALL

 SELECT N'K', 1 UNION ALL SELECT N'K', 2 UNION ALL SELECT N'K', 3 UNION ALL SELECT N'K', 4 UNION ALL
 SELECT N'K', 5 UNION ALL SELECT N'K', 6 UNION ALL SELECT N'K', 7 UNION ALL SELECT N'K', 8 UNION ALL
 SELECT N'K', 9 UNION ALL SELECT N'K', 10 UNION ALL SELECT N'K', 11 UNION ALL SELECT N'K', 12 
) AS s;
GO


-- Phan loai movies
CREATE TABLE genres (
    genre_id INT PRIMARY KEY IDENTITY,
    genre_name NVARCHAR(10) NOT NULL UNIQUE
);
INSERT INTO genres(genre_name) VALUES
('Action'),
('Romance'),
('Drama'),
('Horror'),
('Commedy'),
('Animation'),
('Adventure'),
('Thriller');
GO


-- Phim
CREATE TABLE movies (
    movie_id INT PRIMARY KEY IDENTITY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX) NOT NULL,
    trailer_url VARCHAR(255),
    poster_url VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    release_date DATE NOT NULL,
	status NVARCHAR(20) NOT NULL DEFAULT(N'Future') -- future: chua chieu | present: dang chieu | past: da chieu
);
INSERT INTO movies (title, description, trailer_url, poster_url, duration, release_date, status) VALUES
('A Wednesday', 'The film depicts a confrontation between a police commissioner and an anonymous caller who threatens to detonate bombs throughout Mumbai if four terrorists are not freed from police custody.', 'https://youtu.be/oII-vaL3mZg?si=7bpSH8AZDnLF74Gv', './assets/images/wednesday.jpeg', 140, '2019-04-26', N'Present'),
('Commando-3', 'Karan goes to London to stop a terrorist attack on India. A mysterious man is on an impending mission to attack the country from his base in London. Karan Singh Dogra sets out to hunt down the antagonist aided by the British Intelligence.', 'https://youtu.be/m-JIofHHU6s?si=7cEgaUdnLC53j_Rc', './assets/images/commando.jpeg', 195, '1997-12-19', N'Present'),
('Gujjubhai Most Wanted', 'Gujjubhai and his son Khagesh try a shortcut to earn money which gets them into trouble with terrorists and cops. Gujjubhai and his son Khagesh try a shortcut to earn money which gets them into trouble with terrorists and cops.', 'https://youtu.be/rdu8aLHcVsU?si=uCPVF_DMRfBqCa_X', './assets/images/gujjubhai.jpeg', 148, '2010-07-16', N'Present'),
('Joker', 'The Joker is a DC Comics supervillain who is a psychopathic criminal mastermind, anarchist, and nihilist. He is known for his colorful clothing, clown makeup, and menacing appearance. ', 'https://youtu.be/zAGVQLHvwOY?si=W4F88mCTo98mpOBo', './assets/images/m9.jpg', 122, '2019-10-04', N'Present'),
('Avatar', ' It is set in the mid-22nd century, when humans are colonizing Pandora, a lush habitable moon of a gas giant in the Alpha Centauri star system, in order to mine the valuable unobtanium,a room-temperature superconductor mineral. ', 'https://youtu.be/d9MyW72ELq0?si=wyx1BKNRMckJRbfE', './assets/images/avtar-2.jpeg', 210, '2012-11-02', N'Present'),
('Knives Out', 'Knives Out is a 2019 American mystery film written and directed by Rian Johnson, and produced by Johnson and Ram Bergman. It follows a master detective investigating the death of the patriarch of a wealthy, dysfunctional family. ', 'https://youtu.be/qGqiHJTsRkQ?si=YE9vCFyMcrkAjweh', './assets/images/m3.jpg', 152, '2009-10-04', N'Present'),
('Rocketman', 'The film follows John in his early days in England as a prodigy at the Royal Academy of Music through his musical partnership with Taupin, and is titled after John 1972 song "Rocket Man". ', 'https://youtu.be/mpOGT3GTO84?si=m7DY49WZGpAGcUq4', './assets/images/n3.jpg', 190, '2018-03-09', N'Present'),
('Doctor Sleep', 'Doctor Sleep is a 2019 American supernatural horror film written and directed by Mike Flanagan. It is based on the 2013 novel of the same name by Stephen King, a sequel to King 1977 novel The Shining. The film, which also serves as a direct sequel to the 1980 film adaptation. ', 'https://youtu.be/BOzFZxB-8cw?si=aftNf86t4famzTgW', './assets/images/m2.jpg', 135, '2019-12-24', N'Present'),
('Toy Story 4', ' It is the fourth and final installment in Pixar Toy Story series and the sequel of Toy Story 3 (2010). ', 'https://youtu.be/LDXYRzerjzU?si=e3b4ZU6hCfkCjUic', './assets/images/m8.jpg', 220, '2015-10-14', N'Future'),
('Mulan', 'Mulan is a responsible young woman, seen as a fearless warrior, leader, and beloved role model among her people, both men, and women. ', 'https://youtu.be/KK8FHdFluOQ?si=gf5gKBa2izo2aRyl', './assets/images/n2.jpg', 240, '2019-09-22', N'Future');
GO


-- mot phim co the co nhieu the loai
CREATE TABLE movie_genres (
    movie_genre_id INT PRIMARY KEY IDENTITY,
    movie_id INT FOREIGN KEY REFERENCES movies(movie_id),
    genre_id INT FOREIGN KEY REFERENCES genres(genre_id),
);
INSERT INTO movie_genres(movie_id, genre_id) VALUES
(1,8),
(2,1),
(3,5),
(4,1),
(4,4),
(5,7),
(6,5),
(7,1),
(7,7),
(8,4),
(9,5),
(9,6),
(10,1),
(10,2),
(10,3)
GO


-- Xuat chieu
CREATE TABLE showtimes (
    showtime_id INT PRIMARY KEY IDENTITY,
    movie_id INT FOREIGN KEY REFERENCES movies(movie_id),
    room_id INT FOREIGN KEY REFERENCES rooms(room_id),
    showtime DATETIME NOT NULL,
	status NVARCHAR(20) NOt NULL DEFAULT(N'Saved') -- Saved: tam luu vao database, chua tao ve, co the xoa | Submitted: tao ve cho showtime | Cancel: submited nhung bi huy | Passed: submitted va qua thoi gian chieu
);
INSERT INTO showtimes (movie_id, room_id, showtime) VALUES
(1, 1, '2024-10-15 18:00:00'),
(2, 2, '2024-10-16 20:00:00'),
(3, 3, '2024-10-17 19:30:00'),
(4, 4, '2024-10-18 21:00:00');
GO


-- phieu giam gia
CREATE TABLE coupons (
    coupon_id INT PRIMARY KEY IDENTITY,
    coupon_code VARCHAR(50) UNIQUE NOT NULL,
	user_id INT FOREIGN KEY REFERENCES users(user_id) NOT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL,
    expiry_date DATE NOT NULL
);
INSERT INTO coupons (coupon_code, user_id, discount_percentage, expiry_date) VALUES
('SUMMER2024', 8, 10.00, '2024-12-31'),
('WELCOME10', 8, 15.00, '2024-11-30'),
('MOVIELOVER', 8, 20.00, '2024-10-31');
GO


-- thong tin dat ve
CREATE TABLE bookings (
    booking_id INT PRIMARY KEY IDENTITY,
    user_id INT FOREIGN KEY REFERENCES users(user_id) NOT NULL,
    booking_date DATE DEFAULT GETDATE(),
    sub_total_amount DECIMAL(10,2) NOT NULL,
    coupon_id INT NULL FOREIGN KEY REFERENCES coupons(coupon_id),
	total_amount DECIMAL(10,2) NOT NULL,
	status NVARCHAR(20) NOT NULL DEFAULT('Paid') -- co the su dung cho tuong lai: cancel booking hoac tao booking chua tra tien vv 
);
-- Gia ca se duoc tinh tu dong ve sau
INSERT INTO bookings (user_id, sub_total_amount, coupon_id, total_amount) VALUES
(8, 240000.00, 1, 216000.00),
(8, 200000.00, NULL, 200000.00),
(8, 300000.00, 2, 255000.00),
(8, 260000.00, NULL, 260000.00);
GO


-- Ve
CREATE TABLE tickets (
    ticket_id INT PRIMARY KEY IDENTITY,
    showtime_id INT FOREIGN KEY REFERENCES showtimes(showtime_id) NOT NULL,
    seat_id INT FOREIGN KEY REFERENCES seats(seat_id) NOT NULL,
    user_id INT FOREIGN KEY REFERENCES users(user_id),
    booking_id INT FOREIGN KEY REFERENCES bookings(booking_id),
    price DECIMAL(10,2) NOT NULL,
    status NVARCHAR(20) NOT NULL DEFAULT('Available')
);
-- Ve se duoc tao tu dong ve sau
INSERT INTO tickets (showtime_id, seat_id, user_id, booking_id, price, status) VALUES
(1, 1, 8, 1, 120000.00, 'Booked'),
(2, 2, 8, 1, 100000.00, 'Booked'),
(3, 3, 8, 1, 150000.00, 'Booked'),
(4, 4, 8, 1, 130000.00, 'Booked');


-- di viu
CREATE TABLE reviews (
    review_id INT PRIMARY KEY IDENTITY,
    user_id INT FOREIGN KEY REFERENCES users(user_id) NOT NULL,
    ticket_id INT FOREIGN KEY REFERENCES tickets(ticket_id) NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(MAX),
    created_at DATETIME DEFAULT GETDATE()
);
INSERT INTO reviews (user_id, ticket_id, rating, comment) VALUES
(8, 1, 5, 'Amazing movie, great acting!'),
(8, 2, 4, 'Very touching, great soundtrack.'),
(8, 3, 5, 'Brilliant script, excellent direction.'),
(8, 4, 4, 'Joker was phenomenal!');
GO


-- dien vien va dao dien
CREATE TABLE participants (
    participant_id INT PRIMARY KEY IDENTITY,
    participant_name NVARCHAR(100) NOT NULL,
	portrait_url NVARCHAR(100),
    birth_date DATE,
	nationality NVARCHAR(50) DEFAULT('Updating'),
    about NVARCHAR(MAX) DEFAULT('Updating'),
);
--INSERT INTO participants (participant_id, participant_name, portrait_url, birth_date, nationality, about) VALUES
--, 'Anthony Russo, Joe Russo', 'Robert Downey Jr., Chris Evans, Scarlett Johansson'
--, 'James Cameron', 'Leonardo DiCaprio, Kate Winslet'
--, 'Christopher Nolan', 'Leonardo DiCaprio, Joseph Gordon-Levitt'
--, 'Todd Phillips', 'Joaquin Phoenix'
--, 'Phill Foen', 'Joaquin Phoenix'
--, 'Rian Johnson', 'Daniel Craig, Chris Evans, Ana de Armas, Jamie Lee Curtis, Michael Shannon, Don Johnson, Toni Collette, Lakeith Stanfield, Katherine Langford, Jaeden Martell, and Christopher Plummer.'
--, 'Dexter Fletcher', 'Bernie Taupin, Richard Madden,Joaquin Phoenix,Taron Egerton'
--, 'Stanley Kubrick', 'Rebecca Ferguson,Kyliegh Curran, and Cliff Curtis'
--, 'Pixar Animation Studios', 'John Lasseter,Rashida Jones, Will McCormack, Valerie LaPointe'
--, 'Barry Cook and Tony Bancroft', 'Ming-Na Wen,Eddie Murphy,BD Wong,Harvey Fierstein'
GO


-- tham gia bo phim nao
CREATE TABLE movie_participants (
    movie_id INT FOREIGN KEY REFERENCES movies(movie_id) NOT NULL,
    participant_id INT FOREIGN KEY REFERENCES participants(participant_id) NOT NULL,
    role_in_movie NVARCHAR(20) NOT NULL, -- Actor | Director
	PRIMARY KEY (movie_id,participant_id)
);
--INSERT INTO participants (person_id, person_name, portrait_url, birth_date, nationality, about) VALUES
GO


-- tin tuc
CREATE TABLE posts (
    post_id INT PRIMARY KEY,
    user_id INT FOREIGN KEY REFERENCES users(user_id) NOT NULL,
	title NVARCHAR(100) NOT NULL,
	photo_url NVARCHAR(100),
	content NVARCHAR(MAX) NOT NULL,
	created_date DATE NOT NULL DEFAULT(GETDATE()),
	content_type NVARCHAR(MAX) NOT NULL -- Ad: content quang cao uu dai | Info: content lien quan noi dung phim va dien vien dao dien
);
--INSERT INTO posts (person_id, person_name, portrait_url, birth_date, nationality, about) VALUES
GO


-- do an do uong
CREATE TABLE combos (
    combo_id INT PRIMARY KEY,
	combo_name NVARCHAR(30) NOT NULL,
	detail NVARCHAR(100) NOT NULL,
	combo_price DECIMAL(10,2) NOT NULL
);
--INSERT INTO participants (person_id, person_name, portrait_url, birth_date, nationality, about) VALUES
GO


-- kep vao 
CREATE TABLE booking_combos (
    booking_id INT FOREIGN KEY REFERENCES bookings(booking_id) NOT NULL,
    combo_id INT FOREIGN KEY REFERENCES combos(combo_id) NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (booking_id,combo_id)
);
--INSERT INTO participants (person_id, person_name, portrait_url, birth_date, nationality, about) VALUES
GO

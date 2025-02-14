

-- 1️⃣ Insert Roles
INSERT INTO roles (role_name) VALUES (N'Admin'), (N'Manager'), (N'Customer');

-- 2️⃣ Insert Users
INSERT INTO users (role_id, username, password, full_name, email, phone, address)
VALUES
--  Admins
(1, 'admin', '1', N'Nguyễn Văn Hùng', 'nguyen.hung@cinema.com', '0912345678', N'12 Lý Thái Tổ, Hoàn Kiếm, Hà Nội'),
(1, 'mai.tran', 'Admin@1992', N'Mai Thị Trân', 'mai.tran@cinema.com', '0923456789', N'88 Trần Phú, Ba Đình, Hà Nội'),

--  Managers
(2, 'manager', '1', N'Phạm Thúy', 'pham.thuy@cinema.com', '0934567890', N'32 Bạch Đằng, Hải Châu, Đà Nẵng'),
(2, 'le.duong', 'Mng@1989', N'Lê Dương', 'le.duong@cinema.com', '0945678901', N'45 Nguyễn Huệ, Quận 1, TP.HCM'),
(2, 'hoang.minh', 'Manager123!', N'Hoàng Minh', 'hoang.minh@cinema.com', '0956789012', N'27 Lê Lợi, Quận 1, TP.HCM'),
(2, 'do.bao', 'Mng@1995', N'Đỗ Bảo', 'do.bao@cinema.com', '0967890123', N'88 Quang Trung, Thanh Khê, Đà Nẵng'),
(2, 'nguyen.hoa', 'Manager!2024', N'Nguyễn Hoa', 'nguyen.hoa@cinema.com', '0978901234', N'56 Lý Tự Trọng, Quận 1, TP.HCM'),

--  Customers
(3, 'customers', '1', N'Trần Khoa', 'tran.khoa@cinema.com', '0989012345', N'78 Điện Biên Phủ, Hải Châu, Đà Nẵng'),
(3, 'le.ha', 'Ha@2024', N'Lê Hà', 'le.ha@cinema.com', '0990123456', N'34 Nguyễn Trãi, Thanh Xuân, Hà Nội'),
(3, 'dang.trung', 'Trung!567', N'Đặng Trung', 'dang.trung@cinema.com', '0901234567', N'11 Lê Văn Sỹ, Phú Nhuận, TP.HCM'),
(3, 'vo.hoa', 'HoaMovie@88', N'Võ Hoa', 'vo.hoa@cinema.com', '0912345670', N'63 Võ Văn Kiệt, Sơn Trà, Đà Nẵng'),
(3, 'bui.quang', 'QuangFan@99', N'Bùi Quang', 'bui.quang@cinema.com', '0923456781', N'120 Trần Hưng Đạo, Quận 5, TP.HCM'),
(3, 'nguyen.tram', 'Tram!Cinema', N'Nguyễn Trâm', 'nguyen.tram@cinema.com', '0934567892', N'29 Nguyễn Văn Cừ, Long Biên, Hà Nội'),
(3, 'hoang.thanh', 'ThanhMovie123', N'Hoàng Thanh', 'hoang.thanh@cinema.com', '0945678903', N'77 Hàm Nghi, Hải Châu, Đà Nẵng'),
(3, 'pham.lan', 'Lan@Cinema!', N'Phạm Lan', 'pham.lan@cinema.com', '0956789014', N'21 Hoàng Diệu, Ba Đình, Hà Nội');

-- Insert cities
INSERT INTO cities (city_name) VALUES 
(N'Ha Noi'),
(N'Ho Chi Minh'),
(N'Da Nang');

-- Insert theaters (4 per city)
INSERT INTO theaters (city_id, theater_name, address)
VALUES 
-- Theaters in Ha Noi
((SELECT city_id FROM cities WHERE city_name = N'Ha Noi'), N'CGV Vincom Ba Trieu', N'191 Ba Trieu, Hai Ba Trung, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Ha Noi'), N'Lotte Cinema Keangnam', N'72 Landmark Tower, Pham Hung, Nam Tu Liem, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Ha Noi'), N'BHD Star Pham Ngoc Thach', N'2 Pham Ngoc Thach, Dong Da, Ha Noi'),
((SELECT city_id FROM cities WHERE city_name = N'Ha Noi'), N'Galaxy Cinema Giang Vo', N'16B Giang Vo, Ba Dinh, Ha Noi'),

-- Theaters in Ho Chi Minh
((SELECT city_id FROM cities WHERE city_name = N'Ho Chi Minh'), N'CGV Crescent Mall', N'101 Ton Dat Tien, District 7, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Ho Chi Minh'), N'Lotte Cinema Diamond', N'34 Le Duan, District 1, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Ho Chi Minh'), N'BHD Star Bitexco', N'2 Hai Trieu, District 1, Ho Chi Minh'),
((SELECT city_id FROM cities WHERE city_name = N'Ho Chi Minh'), N'Galaxy Cinema Nguyen Du', N'116 Nguyen Du, District 1, Ho Chi Minh'),

-- Theaters in Da Nang
((SELECT city_id FROM cities WHERE city_name = N'Da Nang'), N'CGV Vincom Da Nang', N'910A Ngo Quyen, Son Tra, Da Nang'),
((SELECT city_id FROM cities WHERE city_name = N'Da Nang'), N'Lotte Cinema Da Nang', N'46 Dien Bien Phu, Thanh Khe, Da Nang'),
((SELECT city_id FROM cities WHERE city_name = N'Da Nang'), N'BHD Star Da Nang', N'271 Nguyen Van Linh, Hai Chau, Da Nang'),
((SELECT city_id FROM cities WHERE city_name = N'Da Nang'), N'Galaxy Cinema Da Nang', N'171 Ton Duc Thang, Lien Chieu, Da Nang');

INSERT INTO rooms (theater_id, room_name, capacity)
SELECT theater_id, CONCAT(N'Room ', r), 132  -- 11 rows * 12 seats = 132 seats
FROM theaters, 
(SELECT 1 AS r UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION 
 SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8) AS room_numbers;
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
INSERT INTO genres (genre_name) VALUES
('Action'),
('Romance'),
('Drama'),
('Horror'),
('Commedy'),
('Animation'),
('Adventure'),
('Thriller');

INSERT INTO movies (title, genre_id, director, actors, description, trailer_url, poster_url, duration, release_date) VALUES
('A Wednesday', 8, 'Anthony Russo, Joe Russo', 'Robert Downey Jr., Chris Evans, Scarlett Johansson', 'The film depicts a confrontation between a police commissioner and an anonymous caller who threatens to detonate bombs throughout Mumbai if four terrorists are not freed from police custody.', 'https://youtu.be/oII-vaL3mZg?si=7bpSH8AZDnLF74Gv', './assets/images/wednesday.jpeg', 140, '2019-04-26'),
('Commando-3', 1, 'James Cameron', 'Leonardo DiCaprio, Kate Winslet', 'Karan goes to London to stop a terrorist attack on India. A mysterious man is on an impending mission to attack the country from his base in London. Karan Singh Dogra sets out to hunt down the antagonist aided by the British Intelligence.', 'https://youtu.be/m-JIofHHU6s?si=7cEgaUdnLC53j_Rc', './assets/images/commando.jpeg', 195, '1997-12-19'),
('Gujjubhai Most Wanted', 5, 'Christopher Nolan', 'Leonardo DiCaprio, Joseph Gordon-Levitt', 'Gujjubhai and his son Khagesh try a shortcut to earn money which gets them into trouble with terrorists and cops. Gujjubhai and his son Khagesh try a shortcut to earn money which gets them into trouble with terrorists and cops.', 'https://youtu.be/rdu8aLHcVsU?si=uCPVF_DMRfBqCa_X', './assets/images/gujjubhai.jpeg', 148, '2010-07-16'),
('Joker', 4, 'Todd Phillips', 'Joaquin Phoenix', 'The Joker is a DC Comics supervillain who is a psychopathic criminal mastermind, anarchist, and nihilist. He is known for his colorful clothing, clown makeup, and menacing appearance. ', 'https://youtu.be/zAGVQLHvwOY?si=W4F88mCTo98mpOBo', './assets/images/m9.jpg', 122, '2019-10-04'),
('Avatar', 7, 'Phill Foen', 'Joaquin Phoenix', ' It is set in the mid-22nd century, when humans are colonizing Pandora, a lush habitable moon of a gas giant in the Alpha Centauri star system, in order to mine the valuable unobtanium,a room-temperature superconductor mineral. ', 'https://youtu.be/d9MyW72ELq0?si=wyx1BKNRMckJRbfE', './assets/images/avtar-2.jpeg', 210, '2012-11-02'),
('Knives Out', 5, 'Rian Johnson', 'Daniel Craig, Chris Evans, Ana de Armas, Jamie Lee Curtis, Michael Shannon, Don Johnson, Toni Collette, Lakeith Stanfield, Katherine Langford, Jaeden Martell, and Christopher Plummer.', 'Knives Out is a 2019 American mystery film written and directed by Rian Johnson, and produced by Johnson and Ram Bergman. It follows a master detective investigating the death of the patriarch of a wealthy, dysfunctional family. ', 'https://youtu.be/qGqiHJTsRkQ?si=YE9vCFyMcrkAjweh', './assets/images/m3.jpg', 152, '2009-10-04'),
('Rocketman', 7, 'Dexter Fletcher', 'Bernie Taupin, Richard Madden,Joaquin Phoenix,Taron Egerton', 'The film follows John in his early days in England as a prodigy at the Royal Academy of Music through his musical partnership with Taupin, and is titled after John 1972 song "Rocket Man". ', 'https://youtu.be/mpOGT3GTO84?si=m7DY49WZGpAGcUq4', './assets/images/n3.jpg', 190, '2018-03-09'),
('Doctor Sleep', 4, 'Stanley Kubrick', 'Rebecca Ferguson,Kyliegh Curran, and Cliff Curtis', 'Doctor Sleep is a 2019 American supernatural horror film written and directed by Mike Flanagan. It is based on the 2013 novel of the same name by Stephen King, a sequel to King 1977 novel The Shining. The film, which also serves as a direct sequel to the 1980 film adaptation. ', 'https://youtu.be/BOzFZxB-8cw?si=aftNf86t4famzTgW', './assets/images/m2.jpg', 135, '2019-12-24'),
('Toy Story 4', 6, 'Pixar Animation Studios', 'John Lasseter,Rashida Jones, Will McCormack, Valerie LaPointe', ' It is the fourth and final installment in Pixar Toy Story series and the sequel of Toy Story 3 (2010). ', 'https://youtu.be/LDXYRzerjzU?si=e3b4ZU6hCfkCjUic', './assets/images/m8.jpg', 220, '2015-10-14'),
('Mulan', 1, 'Barry Cook and Tony Bancroft', 'Ming-Na Wen,Eddie Murphy,BD Wong,Harvey Fierstein', 'Mulan is a responsible young woman, seen as a fearless warrior, leader, and beloved role model among her people, both men, and women. ', 'https://youtu.be/KK8FHdFluOQ?si=gf5gKBa2izo2aRyl', './assets/images/n2.jpg', 240, '2019-09-22');

INSERT INTO showtimes (movie_id, room_id, showtime) VALUES
(1, 1, '2024-10-15 18:00:00'),
(2, 2, '2024-10-16 20:00:00'),
(3, 3, '2024-10-17 19:30:00'),
(4, 4, '2024-10-18 21:00:00');


INSERT INTO tickets (showtime_id, seat_id, user_id, price, status) VALUES
(1, 1, 3, 120000.00, 'Booked'),
(2, 2, 4, 100000.00, 'Booked'),
(3, 3, 5, 150000.00, 'Booked'),
(4, 4, 6, 130000.00, 'Booked');

INSERT INTO coupons (coupon_code, discount_percentage, expiry_date) VALUES
('SUMMER2024', 10.00, '2024-12-31'),
('WELCOME10', 15.00, '2024-11-30'),
('MOVIELOVER', 20.00, '2024-10-31');

INSERT INTO bookings (user_id, total_amount, coupon_id) VALUES
(3, 240000.00, 1),
(4, 200000.00, NULL),
(5, 300000.00, 2),
(6, 260000.00, NULL);

INSERT INTO booking_items (booking_id, ticket_id, quantity) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 2),
(4, 4, 1);

INSERT INTO reviews (user_id, movie_id, rating, comment) VALUES
(3, 1, 5, 'Amazing movie, great acting!'),
(4, 2, 4, 'Very touching, great soundtrack.'),
(5, 3, 5, 'Brilliant script, excellent direction.'),
(6, 4, 4, 'Joker was phenomenal!');

-- Database: booking_app

-- DROP DATABASE IF EXISTS booking_app;

CREATE DATABASE booking_app
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Vietnamese_Vietnam.1258'
    LC_CTYPE = 'Vietnamese_Vietnam.1258'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
-- Hotel Information
CREATE TABLE hotel (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(255),
    address VARCHAR(255),
    retired BOOLEAN DEFAULT FALSE
);

-- Room type
CREATE TABLE room_type (
    room_type_id SERIAL PRIMARY KEY,
    type_name VARCHAR(50),
    hotel_id INT,
    retired BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

-- Rate Plan
CREATE TABLE rate_plan (
    rate_plan_id SERIAL PRIMARY KEY,
    plan_name VARCHAR(50),
    hotel_id INT,
    is_default BOOLEAN DEFAULT FALSE,
    retired BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

-- Room rate
CREATE TABLE room_rate (
    room_rate_id SERIAL PRIMARY KEY,
    room_type_id INT,
    rate_plan_id INT,
    date DATE,
    price DECIMAL(10, 2),
    retired BOOLEAN DEFAULT FALSE,
    UNIQUE (room_type_id, rate_plan_id, date),
    FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id),
    FOREIGN KEY (rate_plan_id) REFERENCES rate_plan(rate_plan_id)
);

-- Room
CREATE TABLE room (
    room_id SERIAL PRIMARY KEY,
    room_type_id INT,
    date DATE,
    status INT,
    retired BOOLEAN DEFAULT FALSE,
    UNIQUE (room_type_id, date),
    FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id)
);

-- RoomStatus
CREATE TABLE room_status (
    status_id SERIAL PRIMARY KEY,
    status_name VARCHAR(50),
    retired BOOLEAN DEFAULT FALSE
);

-- Booking
CREATE TABLE booking (
    booking_id SERIAL PRIMARY KEY,
    room_type_id INT,
    rate_plan_id INT,
    booking_number VARCHAR(50) UNIQUE,
    price DECIMAL(10, 2),
    retired BOOLEAN DEFAULT FALSE,
    booking_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_type_id) REFERENCES room_type(room_type_id),
    FOREIGN KEY (rate_plan_id) REFERENCES rate_plan(rate_plan_id)
);

-- BookingDetails
CREATE TABLE booking_details (
    booking_details_id SERIAL PRIMARY KEY,
    booking_id INT,
    check_in_date DATE,
    check_out_date DATE,
    retired BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);

-- Guest
CREATE TABLE guest (
    guest_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    contact_info VARCHAR(100),
    retired BOOLEAN DEFAULT FALSE
);

-- Mapping Guest to Booking (Many-to-Many relationship)
CREATE TABLE guest_booking (
    guest_id INT,
    booking_id INT,
    PRIMARY KEY (guest_id, booking_id),
    FOREIGN KEY (guest_id) REFERENCES guest(guest_id),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);

-- Inserting Sample Data

-- Hotel
INSERT INTO hotel (name, phone, address, retired)
VALUES ('Sample Hotel', '123-456-7890', '123 Main Street', FALSE);

-- Room type
INSERT INTO room_type (type_name, hotel_id, retired)
VALUES ('Single', 1, FALSE),
       ('Double', 1, FALSE),
       ('Suite', 1, FALSE);

-- Rate Plan
INSERT INTO rate_plan (plan_name, hotel_id, is_default, retired)
VALUES ('Standard', 1, TRUE, FALSE),
       ('Premium', 1, FALSE, FALSE),
       ('Executive', 1, FALSE, FALSE);

-- Room rate
INSERT INTO room_rate (room_type_id, rate_plan_id, date, price, retired)
VALUES (1, 1, '2024-01-10', 100.00, FALSE),
       (2, 1, '2024-01-10', 150.00, FALSE),
       (3, 3, '2024-01-10', 200.00, FALSE);

-- Room
INSERT INTO room (room_type_id, date, status, retired)
VALUES (1, '2024-01-10', 1, FALSE),
       (2, '2024-01-10', 1, FALSE),
       (3, '2024-01-10', 1, FALSE);

-- RoomStatus
INSERT INTO room_status (status_name, retired)
VALUES ('Available', FALSE),
       ('Occupied', FALSE),
       ('Under Maintenance', FALSE);

-- Booking
INSERT INTO booking (room_type_id, rate_plan_id, booking_number, price, retired, booking_date_time)
VALUES (1, 1, 'BOOK001', 100.00, FALSE, '2024-01-10 08:00:00'),
       (2, 1, 'BOOK002', 150.00, FALSE, '2024-01-10 09:00:00'),
       (3, 3, 'BOOK003', 200.00, FALSE, '2024-01-10 10:00:00');

-- BookingDetails
INSERT INTO booking_details (booking_id, check_in_date, check_out_date, retired)
VALUES (1, '2024-01-11', '2024-01-13', FALSE),
       (2, '2024-01-12', '2024-01-15', FALSE),
       (3, '2024-01-14', '2024-01-16', FALSE);

-- Guest
INSERT INTO guest (first_name, last_name, contact_info, retired)
VALUES ('John', 'Doe', 'john@example.com', FALSE),
       ('Jane', 'Smith', 'jane@example.com', FALSE),
       ('Alice', 'Johnson', 'alice@example.com', FALSE);

-- Mapping Guest to Booking (Many-to-Many relationship)
INSERT INTO guest_booking (guest_id, booking_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
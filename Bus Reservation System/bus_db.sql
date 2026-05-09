-- Create Database
CREATE DATABASE IF NOT EXISTS bus_db;
USE bus_db;

-- Drop tables if already exist (for fresh setup)
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS buses;

-- Create Buses Table
CREATE TABLE buses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_name VARCHAR(50) NOT NULL,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    seats INT NOT NULL CHECK (seats > 0)
);

-- Create Bookings Table
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bus_id INT NOT NULL,
    passenger_name VARCHAR(50) NOT NULL,
    seats_booked INT NOT NULL CHECK (seats_booked > 0),
    FOREIGN KEY (bus_id) REFERENCES buses(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Insert Sample Data into buses
INSERT INTO buses (bus_name, source, destination, seats) VALUES
('ExpressLine', 'Delhi', 'Jaipur', 40),
('SuperFast', 'Mumbai', 'Pune', 30),
('CityRide', 'Bangalore', 'Chennai', 35);

-- View Data
SELECT * FROM buses;
SELECT * FROM bookings;
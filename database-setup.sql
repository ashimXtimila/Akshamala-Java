-- Database setup script for Student CRUD Application
-- Run this script in MySQL to create the database and configure the connection

-- Create database
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

-- Create tables (these will be auto-created by Hibernate, but here's the structure for reference)
-- 
-- CREATE TABLE student_classes (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     class_name VARCHAR(255) NOT NULL UNIQUE
-- );
-- 
-- CREATE TABLE subjects (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     subject_name VARCHAR(255) NOT NULL,
--     class_id BIGINT NOT NULL,
--     FOREIGN KEY (class_id) REFERENCES student_classes(id)
-- );
-- 
-- CREATE TABLE students (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     student_name VARCHAR(255) NOT NULL,
--     student_address TEXT NOT NULL,
--     class_id BIGINT NOT NULL,
--     subject_id BIGINT NOT NULL,
--     FOREIGN KEY (class_id) REFERENCES student_classes(id),
--     FOREIGN KEY (subject_id) REFERENCES subjects(id)
-- );

-- Note: The tables will be automatically created by Hibernate based on the JPA entities
-- This script is just for creating the database and user

-- Create a user for the application (optional, you can use root)
-- CREATE USER 'student_user'@'localhost' IDENTIFIED BY 'student_password';
-- GRANT ALL PRIVILEGES ON student_db.* TO 'student_user'@'localhost';
-- FLUSH PRIVILEGES;

DROP DATABASE springlearn;

CREATE DATABASE IF NOT EXISTS  springlearn;

USE springlearn;
 
CREATE TABLE IF NOT EXISTS Student
(
    studentId BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    uin BIGINT NOT NULL,
    PRIMARY KEY (studentId)
);

CREATE TABLE IF NOT EXISTS School
(
    schoolId BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    type BIGINT NOT NULL,
    PRIMARY KEY (schoolId)
);

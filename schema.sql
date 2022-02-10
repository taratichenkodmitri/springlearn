DROP DATABASE springlearn;

CREATE DATABASE IF NOT EXISTS springlearn;

USE springlearn;

CREATE TABLE IF NOT EXISTS Student
(

    studentId    BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    uin          BIGINT       NOT NULL,
    questionably VARCHAR(255) NOT NULL,
    PRIMARY KEY (studentId)
);

CREATE TABLE IF NOT EXISTS Teacher
(

    teacherId BIGINT       NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    uin       BIGINT       NOT NULL,
    PRIMARY KEY (teacherId)
);

CREATE TABLE IF NOT EXISTS School
(

    schoolId BIGINT       NOT NULL AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    type     BIGINT       NOT NULL,
    PRIMARY KEY (schoolId)
);

CREATE TABLE IF NOT EXISTS Education
(

    educationId BIGINT  NOT NULL AUTO_INCREMENT,
    studentId   BIGINT  NOT NULL,
    schoolId    BIGINT  NOT NULL,
    current     BOOLEAN NOT NULL,
    FOREIGN KEY (studentId) REFERENCES Student (studentId),
    FOREIGN KEY (schoolId) REFERENCES School (schoolId),
    PRIMARY KEY (educationId)
);

CREATE TABLE IF NOT EXISTS Job
(

    jobId     BIGINT NOT NULL AUTO_INCREMENT,
    teacherId BIGINT NOT NULL,
    schoolId  BIGINT NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES Teacher (teacherId),
    FOREIGN KEY (schoolId) REFERENCES School (schoolId),
    PRIMARY KEY (jobId)
);

CREATE TABLE IF NOT EXISTS QuestionCode
(

    questionCodeId BIGINT       NOT NULL AUTO_INCREMENT,
    type           BIGINT       NOT NULL,
    value          VARCHAR(255) NOT NULL,
    PRIMARY KEY (questionCodeId)
);

INSERT INTO QuestionCode (type, value)
VALUES (0, '01,02,03');
INSERT INTO QuestionCode (type, value)
VALUES (1, '04,05,06');
INSERT INTO QuestionCode (type, value)
VALUES (2, '01,03,06');
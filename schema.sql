DROP DATABASE springlearn;

CREATE DATABASE IF NOT EXISTS springlearn;

USE springlearn;

CREATE TABLE IF NOT EXISTS User
(

    userId   BIGINT       NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL,
    PRIMARY KEY (userId)
);

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

CREATE TABLE IF NOT EXISTS acl_sid
(
    id        BIGINT     NOT NULL AUTO_INCREMENT,
    principal TINYINT(1) NOT NULL,
    sid       VARCHAR(100),
    PRIMARY KEY (id),
    UNIQUE KEY unique_uk_1 (sid, principal)
);

CREATE TABLE IF NOT EXISTS acl_class
(
    id    bigint(20)   NOT NULL AUTO_INCREMENT,
    class varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_uk_2 (class)
);
CREATE TABLE IF NOT EXISTS acl_object_identity
(
    id                 bigint(20) NOT NULL AUTO_INCREMENT,
    object_id_class    bigint(20) NOT NULL,
    object_id_identity bigint(20) NOT NULL,
    parent_object      bigint(20) DEFAULT NULL,
    owner_sid          bigint(20) DEFAULT NULL,
    entries_inheriting tinyint(1) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_uk_3 (object_id_class, object_id_identity)
);
ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);
ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);
ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);

CREATE TABLE IF NOT EXISTS acl_entry
(
    id                  bigint(20) NOT NULL AUTO_INCREMENT,
    acl_object_identity bigint(20) NOT NULL,
    ace_order           int(11)    NOT NULL,
    sid                 bigint(20) NOT NULL,
    mask                int(11)    NOT NULL,
    granting            tinyint(1) NOT NULL,
    audit_success       tinyint(1) NOT NULL,
    audit_failure       tinyint(1) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_uk_4 (acl_object_identity, ace_order)
);
ALTER TABLE acl_entry
    ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id);
ALTER TABLE acl_entry
    ADD FOREIGN KEY (sid) REFERENCES acl_sid (id);

INSERT INTO QuestionCode (type, value)
VALUES (0, '01,02,03');
INSERT INTO QuestionCode (type, value)
VALUES (1, '04,05,06');
INSERT INTO QuestionCode (type, value)
VALUES (2, '01,03,06');
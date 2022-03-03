INSERT INTO QuestionCode (type, value)
VALUES (0, '01,02,03');
INSERT INTO QuestionCode (type, value)
VALUES (1, '04,05,06');
INSERT INTO QuestionCode (type, value)
VALUES (2, '01,03,06');

INSERT INTO  User (username, password, role, status)
VALUES ('user','$2a$12$xep9dR9al0l3Xdv86lHq9uDkVxQIrnIaNB9xFuW2G4Cf/pBkKUyTy', 'USER','ACTIVE');
INSERT INTO  User (username, password, role, status)
VALUES ('admin','$2a$12$xep9dR9al0l3Xdv86lHq9uDkVxQIrnIaNB9xFuW2G4Cf/pBkKUyTy', 'TEACHER','ACTIVE');
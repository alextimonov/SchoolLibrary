INSERT INTO JOBS VALUES(1, 'DIRECTOR');
INSERT INTO JOBS VALUES(2, 'DEPUTY_DIRECTOR');
INSERT INTO JOBS VALUES(3, 'LIBRARIAN');
INSERT INTO JOBS VALUES(4, 'TEACHER');
INSERT INTO JOBS VALUES(5, 'TUTOR');
INSERT INTO JOBS VALUES(6, 'SECURITY');
INSERT INTO JOBS VALUES(7, 'CLEANER');

INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('White', 'Davis', 2);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Black', 'Sarah', 3);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Green', 'Piter', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Brown', 'Steven', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Gray', 'Stew', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Magenta', 'Tom', 5);

INSERT INTO BOOK (name, course, amounttotal) VALUES('Maths', 1, 50);
INSERT INTO BOOK (name, course, amounttotal) VALUES('Alphabet', 1, 80);
INSERT INTO BOOK (name, course, amounttotal) VALUES('Ukrainian language', 2, 70);
INSERT INTO BOOK (name, course, amounttotal) VALUES('English class book', 2, 50);
INSERT INTO BOOK (name, course, amounttotal) VALUES('English work book', 2, 90);

INSERT INTO CLASS (course, letter, employee_id) VALUES(1, 'A', 3);
INSERT INTO CLASS (course, letter, employee_id) VALUES(1, 'B', 4);
INSERT INTO CLASS (course, letter, employee_id) VALUES(2, 'A', 5);
INSERT INTO CLASS (course, letter, employee_id) VALUES(2, 'B', 6);

INSERT INTO BOOK_TO_CLASS VALUES(1, 1, 16);
INSERT INTO BOOK_TO_CLASS VALUES(2, 1, 20);
INSERT INTO BOOK_TO_CLASS VALUES(1, 2, 16);
INSERT INTO BOOK_TO_CLASS VALUES(2, 2, 20);
INSERT INTO BOOK_TO_CLASS VALUES(3, 3, 17);
INSERT INTO BOOK_TO_CLASS VALUES(4, 3, 18);
INSERT INTO BOOK_TO_CLASS VALUES(3, 4, 17);
INSERT INTO BOOK_TO_CLASS VALUES(4, 4, 18);

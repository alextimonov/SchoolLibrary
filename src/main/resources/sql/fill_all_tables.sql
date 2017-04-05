INSERT INTO JOBS VALUES(1, 'DIRECTOR');
INSERT INTO JOBS VALUES(2, 'DEPUTY_DIRECTOR');
INSERT INTO JOBS VALUES(3, 'LIBRARIAN');
INSERT INTO JOBS VALUES(4, 'TEACHER');
INSERT INTO JOBS VALUES(5, 'TUTOR');
INSERT INTO JOBS VALUES(6, 'SECURITY');
INSERT INTO JOBS VALUES(7, 'CLEANER');

INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('White', 'Davis', 2);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Black', 'Sarah', 3);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Green', 'Peter', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Brown', 'Steven', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Gray', 'Stew', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Magenta', 'Tom', 5);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Yellow', 'Clint', 4);
INSERT INTO EMPLOYEE (surname, name, position_id) VALUES('Red', 'Max', 4);

INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(1, 'Maths', 50, 2, 'Piphagor', 'Veselka');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(1, 'Alphabet', 80, 2, 'Nestor', 'Buki-Vedi');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(2, 'Ukrainian language', 70, 2, 'Grigory Skovoroda', 'Prosvita');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(2, 'English class book', 50, 2, 'Kerion Magnet', 'Oxford publishing');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(2, 'English work book', 90, 2, 'Kerion Magnet', 'Oxford publishing');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(3, 'Maths', 70, 2, 'Leonard Eyler', 'Prosvita');
INSERT INTO BOOK (course, name, amounttotal, employee_id, author, publisher) VALUES(3, 'German language', 60, 2, 'Magnus Lahm', 'Deutche Tseitnung');

INSERT INTO CLASS (course, letter, employee_id) VALUES(1, 'A', 3);
INSERT INTO CLASS (course, letter, employee_id) VALUES(1, 'B', 4);
INSERT INTO CLASS (course, letter, employee_id) VALUES(2, 'A', 5);
INSERT INTO CLASS (course, letter, employee_id) VALUES(2, 'B', 6);
INSERT INTO CLASS (course, letter, employee_id) VALUES(3, 'A', 7);
INSERT INTO CLASS (course, letter, employee_id) VALUES(3, 'B', 8);

INSERT INTO BOOK_TO_CLASS VALUES(1, 1, 1, 16);
INSERT INTO BOOK_TO_CLASS VALUES(2, 2, 1, 20);
INSERT INTO BOOK_TO_CLASS VALUES(3, 1, 2, 16);
INSERT INTO BOOK_TO_CLASS VALUES(4, 2, 2, 20);
INSERT INTO BOOK_TO_CLASS VALUES(5, 3, 3, 17);
INSERT INTO BOOK_TO_CLASS VALUES(6, 4, 3, 18);
INSERT INTO BOOK_TO_CLASS VALUES(7, 3, 4, 17);
INSERT INTO BOOK_TO_CLASS VALUES(8, 4, 4, 18);
INSERT INTO BOOK_TO_CLASS VALUES(9, 3, 5, 17);
INSERT INTO BOOK_TO_CLASS VALUES(10, 4, 5, 18);
INSERT INTO BOOK_TO_CLASS VALUES(11, 5, 6, 20);
INSERT INTO BOOK_TO_CLASS VALUES(12, 6, 6, 20);
INSERT INTO BOOK_TO_CLASS VALUES(13, 5, 7, 15);

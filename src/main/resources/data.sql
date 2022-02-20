INSERT into USER (name, username, password, balance, non_Locked, enable) values ('salman', 'admin', '123', 150000, true, true);

INSERT into ROLE (id, role_name) values (1, 'level_1');
INSERT into ROLE (id, role_name) values (2, 'level_2');
INSERT into ROLE (id, role_name) values (3, 'level_3');
INSERT into ROLE (id, role_name) values (4, 'level_4');
INSERT into ROLE (id, role_name) values (5, 'level_5');
INSERT into ROLE (id, role_name) values (6, 'level_6');
INSERT into ROLE (id, role_name) values (7, 'level_7');
INSERT into ROLE (id, role_name) values (8, 'level_8');
INSERT into ROLE (id, role_name) values (9, 'admin');

INSERT into USER_ROLE (user_id, role_id) values (1, 9);


 INSERT INTO SERVICE (name, cost, status, activation_Date) values ('send-sms', 15, true, current_timestamp());
 INSERT INTO SERVICE (name, cost, status, activation_Date) values ('send-mail', 10, true, current_timestamp());
 INSERT INTO SERVICE (name, cost, status, activation_Date) values ('get-world-news', 5, false, current_timestamp());

insert into user (email, identifier, name) values ('email', 'identifier', 'name');
insert into user (email, identifier, name) values ('test@gmail.com', '1', '김아무개');

insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED', 'LETTER_TO', 'email', 'HAPPY', 1 , 'PENDING', 'TITLE', 1, '2021-08-11T12:09:59.342635');
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED1', 'LETTER_TO1', 'email', 'BLUE', 2, 'PENDING', 'TITLE1', 1, '2021-08-09T12:09:59.342635');
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED1', 'LETTER_TO1', 'email', 'BLUE', 8, 'PENDING', 'TITLE1', 1, '2021-08-04T12:09:59.342635');

insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id)
values ('CONTENTS2', 'ENCRYPTED2', 'LETTER_TO2', 'test@gmail.com', 'BLUE', 2, 'PENDING', 'TITLE2', 2);
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id)
values ('CONTENTS3', 'ENCRYPTED3', 'LETTER_TO3', 'test@gmail.com', 'BLUE', 2, 'PENDING', 'TITLE3', 2);

insert into send_option (text, covid_stat) values ('테스트옵션', 1000);
insert into send_option (text, covid_stat) values ('테스트옵션2', 2000);
insert into send_option (text, covid_stat) values ('기본질문옵션', 0);

insert into question (text, send_option_id) values ('테스트1에 1', 1);
insert into question (text, send_option_id) values ('테스트1에 2', 1);
insert into question (text, send_option_id) values ('테스트1에 3', 1);

insert into question (text, send_option_id) values ('테스트2에 1', 2);
insert into question (text, send_option_id) values ('테스트2에 2', 2);

insert into question (text, send_option_id) values ('기본 질문1', 3);
insert into question (text, send_option_id) values ('기본 질문2', 3);
insert into question (text, send_option_id) values ('기본 질문3', 3);

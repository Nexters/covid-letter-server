insert into user (email, identifier, name) values ('email', 'identifier', 'name');

insert into letter (answer, contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id)
values ('ANSWER', 'CONTENTS', 'ENCRYPTED', 'LETTER_TO', 'email', 'A', 'HAPPY', 'PENDING', 'TITLE', 1);
insert into letter (answer, contents, encrypted_id, letter_to, email, sticker, question_id, state, title, user_id)
values ('ANSWER1', 'CONTENTS1', 'ENCRYPTED1', 'LETTER_TO1', 'email', 'A', 'BLUE', 'PENDING', 'TITLE1', 1);

insert into send_option (text, covid_stat) values ('테스트', 1000);
insert into send_option (text, covid_stat) values ('테스트2', 2000);
insert into send_option (text, covid_stat) values ('기본질문', 0);

insert into question (text, send_option_id) values ('테스트1에 1', 1);
insert into question (text, send_option_id) values ('테스트1에 2', 1);
insert into question (text, send_option_id) values ('테스트1에 3', 1);

insert into question (text, send_option_id) values ('테스트2에 1', 2);
insert into question (text, send_option_id) values ('테스트2에 2', 2);

insert into question (text, send_option_id) values ('기본 질문1', 3);
insert into question (text, send_option_id) values ('기본 질문2', 3);
insert into question (text, send_option_id) values ('기본 질문3', 3);

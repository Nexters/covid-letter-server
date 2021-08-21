insert into user (email, identifier, name) values ('email', 'identifier', 'name');
insert into user (email, identifier, name) values ('test@gmail.com', '1', '김아무개');

insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, send_option_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED', 'LETTER_TO', 'email', 'HAPPY', 1 , 1,  'PENDING', 'TITLE', 1, '2021-08-11T12:09:59.342635');
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, send_option_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED1', 'LETTER_TO1', 'email', 'BLUE', 2, 1, 'PENDING', 'TITLE1', 1, '2021-08-09T12:09:59.342635');
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, send_option_id, state, title, user_id, created_date)
values ('64K07Jqp7JqU7Jqp7Jqp7Jqp', 'ENCRYPTED2', 'LETTER_TO1', 'email', 'BLUE', 8, 2, 'PENDING', 'TITLE1', 1, '2021-08-04T12:09:59.342635');

insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, send_option_id, state, title, user_id)
values ('CONTENTS2', 'ENCRYPTED2', 'LETTER_TO2', 'test@gmail.com', 'BLUE', 2, 7, 'PENDING', 'TITLE2', 2);
insert into letter (contents, encrypted_id, letter_to, email, sticker, question_id, send_option_id, state, title, user_id)
values ('CONTENTS3', 'ENCRYPTED3', 'LETTER_TO3', 'test@gmail.com', 'BLUE', 2, 7, 'PENDING', 'TITLE3', 2);

-- 발송 옵션
insert into send_option (text, covid_stat) values ('실내에서 마스크를 벗을 수 있을 때', 1000);
insert into send_option (text, covid_stat) values ('해외 여행이 가능할 때', 2000);
insert into send_option (text, covid_stat) values ('하루 확진자 수 100명 이하일 때', 100);
insert into send_option (text, covid_stat) values ('백신 접종율이 50% 이상일 때', 0);
insert into send_option (text, covid_stat) values ('코로나 종식 선언할 때', 0);
insert into send_option (text, covid_stat) values ('공통 질문', 0);
insert into send_option (text, covid_stat) values ('미선택', 0);

-- 1. 질문 실내에서 마스크를 벗을 수 있을 때
insert into question (text, send_option_id) values ('코로나가 끝나면 누구와 어떤 공간을 가장 가고 싶나요?', 1);
insert into question (text, send_option_id) values ('사회적 거리두가기 완화되면 제일 먼저 만나고 싶은 사람은 누구인가요?', 1);
insert into question (text, send_option_id) values ('노래방에 가서 제일 먼저 부를 곡은 무엇인가요?', 1);
insert into question (text, send_option_id) values ('가장 좋아하는 실내 활동은 무엇인가요?', 1);
insert into question (text, send_option_id) values ('가장 좋아하는 실외 활동은 무엇인가요?', 1);

-- 2. 해외 여행이 가능할 때
insert into question (text, send_option_id) values ('코로나가 끝나면 누구와 어느 나라를 여행하고 싶나요?', 2);
insert into question (text, send_option_id) values ('코로나 전, 가장 마지막에 여행 다녀온 나라는 어딘가요?', 2);
insert into question (text, send_option_id) values ('가장 좋아하는 나라는 어디인가요?', 2);
insert into question (text, send_option_id) values ('제일 오래 머문 나라는 어디인가요?', 2);

-- 3. 하루 확진자 수 100명 이하일 때
insert into question (text, send_option_id) values ('가장 마지막으로 영화관에서 본 영화는 무엇인가요?', 3);
insert into question (text, send_option_id) values ('가장 마지막으로 간 단체 여행을 얘기해주세요.', 3);
insert into question (text, send_option_id) values ('혼자 국내 여행을 떠난다면. 어느 지역을 가고 싶나요?', 3);
insert into question (text, send_option_id) values ('제일 배우고 싶은 운동이 무엇인가요?', 3);
insert into question (text, send_option_id) values ('코로나 시국에 새로 갖게 된 취미활동?', 3);
insert into question (text, send_option_id) values ('코로나가 끝나고 하고싶은 취미활동이 있나요?', 3);

-- 4. 백신 접종율이 50% 이상일 때
insert into question (text, send_option_id) values ('최근 가장 외로웠던 날은 언제였나요?', 4);
insert into question (text, send_option_id) values ('가장 가고 싶은 나만의 맛집은?', 4);
insert into question (text, send_option_id) values ('가장 가고 싶은 공연이나 페스티벌을 얘기해주세요.', 4);
insert into question (text, send_option_id) values ('코로나 시국에 불행 중 다행이었던 것은?', 4);
insert into question (text, send_option_id) values ('가장 재밌게 본 유튜브 영상은?', 4);
insert into question (text, send_option_id) values ('코로나로 인해 알게된 나의 새로운 면모는?', 4);
insert into question (text, send_option_id) values ('코시국에 가장 기억에 남는 에피소드는?', 4);

-- 5. 코로나 종식 선언할 때
insert into question (text, send_option_id) values ('코로나 종식 뉴스를 보자마자 제일 먼저 연락할 사람은?', 5);
insert into question (text, send_option_id) values ('코로나 종식 후, 제일 먼저 만나고 싶은 사람은 누구인가요?', 5);
insert into question (text, send_option_id) values ('코로나가 종식 후 가장 기대되는 일은?', 5);
insert into question (text, send_option_id) values ('코로나 이전의 나에게 해주고 싶은 말은?', 5);
insert into question (text, send_option_id) values ('나만의 코로나블루 극복 방법은?', 5);
insert into question (text, send_option_id) values ('코로나가 변화시킨 나의 삶은?', 5);
insert into question (text, send_option_id) values ('코로나 종식 후에 연말을 어떻게 보내고 싶으신가요?', 5);
insert into question (text, send_option_id) values ('코로나19가 아니었다면 지금의 나는 어떤 일상을 보내고 있을까?', 5);

-- 6. 공통 질문
insert into question (text, send_option_id) values ('요즘 취미는?', 6);
insert into question (text, send_option_id) values ('오늘 하루 어떠셨나요?', 6);
insert into question (text, send_option_id) values ('요즘 하는 고민은?', 6);
insert into question (text, send_option_id) values ('현재 가지고 있는 꿈이 있다면?', 6);
insert into question (text, send_option_id) values ('현재 가장 노력하고 있는 부분은 무엇인가요?', 6);
insert into question (text, send_option_id) values ('올해 가장 기억에 남는 순간은?', 6);
insert into question (text, send_option_id) values ('가장 생각나는 사람은?', 6);
insert into question (text, send_option_id) values ('스스로 가장 자신있는 부분과 자신 없는 부분이 있다면?', 6);
insert into question (text, send_option_id) values ('세상에서 가장 아끼는 것은?', 6);
insert into question (text, send_option_id) values ('올해 가장 슬펐던 기억은?', 6);
insert into question (text, send_option_id) values ('올해 가장 지우고 싶은 일은?', 6);
insert into question (text, send_option_id) values ('올해 가장 즐거웠던 일은?', 6);
insert into question (text, send_option_id) values ('올해 가장 맛있게 먹은 음식은?', 6);
insert into question (text, send_option_id) values ('한해 동안 제일 많이한 일은?', 6);
insert into question (text, send_option_id) values ('편지를 읽을 나에게 하고 싶은 말은?', 6);
insert into question (text, send_option_id) values ('인생에서 가장 후회하는 일은?', 6);
insert into question (text, send_option_id) values ('인생에서 가장 이불킥하는 일은?', 6);

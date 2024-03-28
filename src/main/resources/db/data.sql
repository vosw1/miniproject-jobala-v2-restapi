-- -- 개인 회원가입 더미 9개
insert into user_tb(name, username, email, password, address, age, phone, role, img_filename, img_title, created_at)
values ('김주혁', 'cos1', 'cos@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, 'userImage1.png',
        'userImage.png', now()),
       ('송민경', 'cos2', 'gook@nate.com', '1234', '부산광역시 해운대구', '1992-05-19', '01012345678', 0, 'userImage2.png',
        'userImage2.png', now()),
       ('박찬혁', 'cos3', 'ssar@nate.com', '1234', '서울시 황학구', '1997-07-23', '01022223333', 0, 'userImage3.png',
        'userImage3.png', now()),
       ('박선규', 'cos4', 'ssar@nate.com', '1234', '서울시 황학구', '1997-07-23', '01022223333', 0, 'userImage4.png',
        'userImage4.png', now()),
       ('장유진', 'cos5', 'ssar@nate.com', '1234', '서울시 황학구', '1997-07-23', '01022223333', 0, 'userImage5.png',
        'userImage5.png', now()),
       ('강호동', 'cos6', 'dong@nate.com', '1234', '부산광역시 기장군', '1995-04-17', '01012345678', 0, 'userImage6.png',
        'userImage6.png', now()),
       ('유재석', 'cos7', 'seok@nate.com', '1234', '부산광역시 ', '2000-04-25', '01012345678', 0, 'userImage7.png',
        'userImage7.png', now()),
       ('지석진', 'cos8', 'jin@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, 'userImage8.png',
        'userImage8.png', now()),
       ('송지효', 'cos9', 'ji@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, 'userImage9.png',
        'userImage9.png', now()),
       ('김종국', 'cos10', 'jong@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, 'userImage10.png',
        'userImage10.png', now()),
       ('양세찬', 'cos11', 'chan@nate.com', '1234', '부산광역시 금정구', '2000-04-25', '01012345678', 0, 'userImage11.png',
        'userImage11.png', now());

-- -- 기업 회원가입 더미 9개
insert into user_tb(comp_num, ceo, compname, address, username, email, password, name, phone, role, img_filename,
                    img_title, created_at)
values ('827-546-7895', '이병헌', '쿠팡', '서울특별시 강남구', 'com1', 'actor1@nate.com', '1234', '이병헌', '01011112223', 1,
        'cupang.png', 'cupang.png', now()),
       ('737-546-7196', '송중기', '네이버', '서울특별시 종로구', 'com2', 'actor2@nate.com', '1234', '송중기', '01011112224', 1,
        'naver.png', 'naver.png', now()),
       ('657-546-2897', '전지현', '카카오', '서울특별시 강동구', 'com3', 'actor3@nate.com', '1234', '전지현', '01011112225', 1,
        'kakao.png', 'kakao.png', now()),
       ('567-543-7898', '아이유', '토스', '서울특별시 용산구', 'com4', 'singer1@nate.com', '1234', '아이유', '01011112226', 1,
        'toss.png', 'toss.png', now()),
       ('467-546-7899', '지드래곤', '배달의민족', '서울특별시 마포구', 'com5', 'singer2@nate.com', '1234', '지드래곤', '01011112227', 1,
        'baemin.png', 'baemin.png',
        now()),
       ('367-546-7900', '수지', '라인', '서울특별시 서초구', 'com6', 'actor4@nate.com', '1234', '수지', '01011112228', 1,
        'line.png', 'line.png', now()),
       ('266-546-7901', '박보검', '당근마켓', '서울특별시 강남구', 'com7', 'actor5@nate.com', '1234', '박보검', '01011112229', 1,
        'carrot.png', 'carrot.png', now()),
       ('177-546-7902', '김수현', '하이브', '서울특별시 강동구', 'com8', 'actor6@nate.com', '1234', '김수현', '01011112230', 1,
        'hybe.png', 'hybe.png', now()),
       ('867-546-7903', '이민호', '겟인데어', '서울특별시 강남구', 'com9', 'actor7@nate.com', '1234', '이민호', '01011112231', 1,
        'getinthere.png', 'getinthere.png', now()),
       ('664-236-7893', '송강호', 'NH농협은행', '서울특별시 강남구', 'com10', 'actor8@nate.com', '1234', '송강호', '01011112231', 1,
        'nh.png', 'nh.png', now());

insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, skills, created_at)
values (1, '도전적인 프론트엔드 개발자 지원서', '프론트엔드', '신입', '정보처리기사',
        '안녕하세요, 저는 신입 프론트엔드 개발자 김주혁입니다. 웹 개발에 관심이 많으며 새로운 기술을 배우고 싶습니다.', '대학교 졸업',
        '[JAVA,Java_Script,HTML,jQuery,MySQL]', now()),
       (2, '새로운 시각의 프론트엔드 엔지니어 지원서', '프론트엔드', '신입', 'SQLD',
        '안녕하세요, 프론트엔드 엔지니어 지원자 송민경입니다. 사용자 중심의 디자인과 웹 기술에 대한 열정으로 새로운 경험을 제공하고자 합니다.', '고등학교 졸업',
        '[Java,HTML,jQuery]', now()),
       (3, '혁신적인 백엔드 엔지니어 이력서', '백엔드', '신입', 'SQLD',
        '안녕하세요, 혁신적인 백엔드 엔지니어 지원자 박찬혁입니다. 데이터베이스 관리와 서버 구축에 대한 열정으로 새로운 기술을 개발하고자 합니다.', '고등학교 졸업',
        '[Java,Java_Script,Spring]', now()),
       (4, '데이터 전문가로서의 백엔드 엔지니어링 이력서', '백엔드', '신입', '정보처리기사',
        '안녕하세요, 데이터 전문가로서의 백엔드 엔지니어 강호동입니다. 데이터 처리와 보안에 대한 전문성으로 효율적인 시스템을 구축하고자 합니다.', '고등학교 졸업',
        '[Java,HTML,MySQL]', now()),
       (5, '창의적인 프론트엔드 개발자 자기소개서', '프론트엔드', '신입', '정보처리기사',
        '안녕하세요, 창의적인 프론트엔드 개발자 유재석입니다. 사용자들의 니즈를 파악하여 독특하고 창의적인 웹 서비스를 개발하고자 합니다.', '고등학교 졸업',
        '[Java,Spring,jQuery,MySQL]', now()),
       (6, '현명한 백엔드 엔지니어의 이력서', '백엔드', '신입', 'SQLD',
        '안녕하세요, 현명한 백엔드 엔지니어 지원자 지석진입니다. 데이터 처리와 시스템 관리에 대한 노하우로 안정적인 시스템을 구축하고자 합니다.', '고등학교 졸업',
        '[Java_Script,HTML,MySQL]', now()),
       (7, '트렌디한 프론트엔드 개발자 지원서', '프론트엔드', '신입', 'SQLD',
        '안녕하세요, 트렌디한 프론트엔드 개발자 지원자 송지효입니다. 최신 웹 트렌드에 능숙하게 대응하여 트렌디하고 혁신적인 서비스를 개발하고자 합니다.', '고등학교 졸업',
        '[Java,Spring,jQuery,MySQL]', now()),
       (8, '현대적인 백엔드 엔지니어 이력서', '백엔드', '신입', '정보처리기사',
        '안녕하세요, 현대적인 백엔드 엔지니어 이력서를 제출하는 김종국입니다. 최신 기술과 표준에 따라 안정적이고 효율적인 시스템을 구축하고자 합니다.', '고등학교 졸업',
        '[Java,Spring,MySQL]', now()),
       (9, '열정적인 프론트엔드 엔지니어 자기소개서', '백엔드', '신입', 'SQLD',
        '안녕하세요, 열정적인 프론트엔드 엔지니어 양세찬입니다. 최신 웹 기술에 대한 지식과 열정으로 사용자들에게 최고의 경험을 제공하고자 합니다.', '고등학교 졸업',
        '[Java,Spring,MySQL]', now());

-- --채용공고 더미
INSERT INTO jobopen_tb(user_id, jobopen_title, career, edu, hope_job, comp_location, job_type, salary, end_time, skills,
                       role, created_at)
VALUES (12, '소프트웨어 개발자 채용', '신입', '고등학교 졸업', '백엔드', '서울', '정규직', '협의', '2024-12-31',
        '[Java,Java_Script,HTML,jQuery,MySQL]', 1, now()),
       (13, '데이터 분석가 채용', '경력', '대학교 졸업', '백엔드', '부산', '정규직', '5000만원 이상', '2024-11-30', '[Java,HTML,MySQL]', 1, now()),
       (14, 'UI/UX 디자이너 채용', '신입', '대학교 졸업', '프론트엔드', '서울', '정규직', '3000만원 이상', '2024-10-31',
        '[Java,Spring,jQuery,MySQL]', 1, now()),
       (15, '마케팅 전문가 채용', '경력', '대학교 졸업', '백엔드', '부산', '정규직', '협의', '2024-09-30', '[Java_Script,HTML,MySQL]', 1,
        now()),
       (16, '운영 지원 인력 채용', '신입', '고등학교 졸업', '프론트엔드', '부산', '정규직', '협의', '2024-09-11', '[Java]', 1, now()),
       (17, '운영 지원 인력 채용', '신입', '고등학교 졸업', '프론트엔드', '서울', '계약직', '협의', '2024-08-31', '[Java]', 1, now()),
       (18, '소프트웨어 개발자 채용', '신입', '고등학교 졸업', '백엔드', '서울', '정규직', '5000만원 이상', '2024-10-12', '[Java,Spring]', 1,
        now()),
       (19, '마케팅 전문가 채용', '경력', '고등학교 졸업', '프론트엔드', '서울', '계약직', '협의', '2024-05-20', '[Java,Spring,MySQL]', 1, now()),
       (20, 'UI/UX 디자이너 채용', '경력', '고등학교 졸업', '프론트엔드', '부산', '계약직', '3000만원 이상', '2024-06-22', '[Java,HTML,jQuery]', 1,
        now());

-- 개인이 기업공고를 보고 지원한 더미 9개
INSERT INTO apply_tb(user_id, jobopen_id, resume_id, role, state, created_at)
VALUES (1, 1, 1, 0, '검토중', NOW()),
       (1, 2, 1, 0, '검토중', NOW()),
       (1, 3, 1, 0, '검토중', NOW()),
       (2, 2, 2, 0, '검토중', NOW()),
       (2, 3, 2, 0, '검토중', NOW()),
       (2, 4, 2, 0, '검토중', NOW()),
       (3, 3, 3, 0, '검토중', NOW()),
       (3, 4, 3, 0, '검토중', NOW()),
       (3, 5, 3, 0, '검토중', NOW()),
       (4, 4, 4, 0, '검토중', NOW()),
       (4, 5, 4, 0, '검토중', NOW()),
       (4, 6, 4, 0, '검토중', NOW()),
       (5, 5, 5, 0, '검토중', NOW()),
       (5, 6, 5, 0, '검토중', NOW()),
       (5, 7, 5, 0, '검토중', NOW()),
       (6, 6, 6, 0, '검토중', NOW()),
       (6, 7, 6, 0, '검토중', NOW()),
       (6, 8, 6, 0, '검토중', NOW()),
       (7, 7, 7, 0, '검토중', NOW()),
       (7, 8, 7, 0, '검토중', NOW()),
       (7, 9, 7, 0, '검토중', NOW()),
       (8, 8, 8, 0, '검토중', NOW()),
       (8, 7, 8, 0, '검토중', NOW()),
       (8, 6, 8, 0, '검토중', NOW()),
       (9, 9, 9, 0, '검토중', NOW()),
       (9, 8, 9, 0, '검토중', NOW()),
       (9, 7, 9, 0, '검토중', NOW());


---기업이 포지션 제안한 공고 현황 더미
INSERT INTO apply_tb(user_id, jobopen_id, resume_id, role, state, created_at)
VALUES (10, 1, 1, 1, '검토중', NOW()),
       (10, 1, 2, 1, '검토중', NOW()),
       (10, 1, 3, 1, '검토중', NOW()),
       (11, 2, 2, 1, '검토중', NOW()),
       (11, 2, 3, 1, '검토중', NOW()),
       (11, 2, 4, 1, '검토중', NOW()),
       (12, 3, 3, 1, '검토중', NOW()),
       (12, 3, 4, 1, '검토중', NOW()),
       (12, 3, 5, 1, '검토중', NOW()),
       (13, 4, 4, 1, '검토중', NOW()),
       (13, 4, 5, 1, '검토중', NOW()),
       (13, 4, 6, 1, '검토중', NOW()),
       (14, 5, 5, 1, '검토중', NOW()),
       (14, 5, 6, 1, '검토중', NOW()),
       (14, 5, 7, 1, '검토중', NOW()),
       (15, 6, 6, 1, '검토중', NOW()),
       (15, 6, 7, 1, '검토중', NOW()),
       (15, 6, 8, 1, '검토중', NOW()),
       (16, 7, 7, 1, '검토중', NOW()),
       (16, 7, 8, 1, '검토중', NOW()),
       (16, 7, 9, 1, '검토중', NOW()),
       (17, 8, 8, 1, '검토중', NOW()),
       (17, 8, 7, 1, '검토중', NOW()),
       (17, 8, 6, 1, '검토중', NOW()),
       (18, 9, 9, 1, '검토중', NOW()),
       (18, 9, 8, 1, '검토중', NOW()),
       (18, 9, 7, 1, '검토중', NOW());


-- 개인이 공고를 스트랩한 더미
INSERT INTO scrap_tb(user_id, jobopen_id, role, created_at)
VALUES (1, 1, 0, NOW()),
       (1, 2, 0, NOW()),
       (1, 3, 0, NOW()),
       (2, 2, 0, NOW()),
       (2, 3, 0, NOW()),
       (2, 4, 0, NOW()),
       (3, 3, 0, NOW()),
       (3, 4, 0, NOW()),
       (3, 5, 0, NOW()),
       (4, 4, 0, NOW()),
       (4, 5, 0, NOW()),
       (4, 6, 0, NOW()),
       (5, 5, 0, NOW()),
       (5, 6, 0, NOW()),
       (5, 7, 0, NOW()),
       (6, 6, 0, NOW()),
       (6, 8, 0, NOW()),
       (6, 9, 0, NOW()),
       (7, 7, 0, NOW()),
       (7, 8, 0, NOW()),
       (7, 9, 0, NOW()),
       (8, 8, 0, NOW()),
       (8, 7, 0, NOW()),
       (8, 6, 0, NOW()),
       (9, 9, 0, NOW()),
       (9, 8, 0, NOW()),
       (9, 7, 0, NOW());

-- 기업이 이력서를 스트랩한 더미
INSERT INTO scrap_tb(user_id, resume_id, role, created_at)
VALUES (10, 1, 1, NOW()),
       (10, 2, 1, NOW()),
       (10, 3, 1, NOW()),
       (11, 2, 1, NOW()),
       (11, 3, 1, NOW()),
       (11, 4, 1, NOW()),
       (12, 3, 1, NOW()),
       (12, 4, 1, NOW()),
       (12, 5, 1, NOW()),
       (13, 4, 1, NOW()),
       (13, 5, 1, NOW()),
       (13, 6, 1, NOW()),
       (14, 5, 1, NOW()),
       (14, 6, 1, NOW()),
       (14, 7, 1, NOW()),
       (15, 6, 1, NOW()),
       (15, 7, 1, NOW()),
       (15, 8, 1, NOW()),
       (16, 7, 1, NOW()),
       (16, 8, 1, NOW()),
       (16, 9, 1, NOW()),
       (17, 8, 1, NOW()),
       (17, 7, 1, NOW()),
       (17, 6, 1, NOW()),
       (18, 9, 1, NOW()),
       (18, 8, 1, NOW()),
       (18, 7, 1, NOW());


-- 자유게시판 더미
INSERT INTO board_tb(role, user_id, title, content, created_at)
VALUES (0, 1, '1년차 개발자들', '1년차 개발자들 요즘 패기가 없더라 봄인데도 춥다고 패딩입고 다녀 쿼리 작성할 때도 *쓰더라 하나하나씩 다 안적고 싸가지 없게;; 나때는 하...', now()),
       (0, 2, '죄송합니다', '유저 여러분의 마음을 헤아리지 못해 정말 죄송합니다! 사죄의 의미로 200회 무료 뽑기와 전설급 장비와 펫을 무료로 드리겠습니다', now()),
       (0, 1, '우리 회사 최고', '진짜 너무 좋아서 죽고 싶다', now()),
       (0, 2, '네이버 질문 있어요', '네이버 들어가려면 어떻게 해야하나요?', now()),
       (0, 1, '배민 자소서 질문', '배민 등급 천생연분인데 자기소개서 어필하면 가산점 있을까요?', now()),
       (0, 2, '회사 추천 해주세요', '백엔드 5년차 이직 준비중입니다. 연봉 어느정도 받으시나요?', now()),
       (0, 1, '햄버거', '요즘 맥도날드 보다는 롯데리아가 더 맛있더라', now()),
       (0, 2, '초보자분들 강의 추천 드립니다.', '최주호 강사님 강의 들어보세요. 알기 쉽게 머리에 쏙쏙 들어오게 알려주시네요!', now()),
       (0, 1, '책 추천 해주세요', '개발자라면 꼭 읽어야 할 책 추천해주세요. 이유도 같이 적어주시면 감사하겠습니다! ㅎㅎ', now());


--댓글 더미
Insert Into reply_tb(user_id, board_id, comment, created_at)
values (2, 1, '꼰대임?', now()),             --cos2이 1번 게시물에 댓글을 작성했다.
       (3, 1, '넌 몇년차인데?ㅋㅋ', now()),       --cos3가 1번 게시물에 댓글을 작성했다.
       (1, 2, '관심 받고 싶니?', now()),        --cos1이 2번 게시물에 댓글을 작성했다.
       (3, 2, '너가 주면 난 내 전재산 준다', now()), --cos3가 2번 게시물에 댓글을 작성했다.
       (2, 3, '뭐지', now()),               --cos3가 3번 게시물에 댓글을 작성했다.
       (2, 3, '아재요?', now()),             -- cos2가 게시물 번호 1에 댓글을 남겼습니다.
       (3, 4, '나이는 몇 살이세요? ㅎㅎ', now()),   -- cos3가 게시물 번호 1에 댓글을 남겼습니다.
       (1, 5, '신경쓰여요?', now()),           -- cos1이 게시물 번호 2에 댓글을 남겼습니다.
       (3, 6, '주시면 다 줄게요', now()),        -- cos3가 게시물 번호 2에 댓글을 남겼습니다.
       (2, 7, '뭐라고요?', now()),            -- cos3가 게시물 번호 3에 댓글을 남겼습니다.
       (1, 8, '힘내세요!', now()),
       (2, 8, '오늘도 화이팅!', now()),
       (3, 9, '바닥부터 다시 시작해보세요', now()),
       (1, 9, '한걸음 먼저 나아가보세요', now());



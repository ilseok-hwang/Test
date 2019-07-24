
INSERT INTO MEMBERS(USER_ID, EMAIL, PASSWORD, REG_DT, USE_YN, USER_NAME) VALUES ('test', 'ABC@naver.com', '$2a$10$lqd5FTVdct7PHfDnEMgf7uL0ZxnlB3WrV6FqN55I/q0tzQG9iX176', SYSTIMESTAMP, 'Y', '테스트' );
INSERT INTO MEMBERS_ROLE (USER_ID, ROLE_NAME) VALUES ('test', 'BASIC');


//POPULAR_KEYWORD 인덱스
 CREATE INDEX POPULAR_KEYWORD_IX01 ON POPULAR_KEYWORD (REG_DT DESC);

 
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드1' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드2' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드3' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드4' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드5' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드6' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드7' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드8' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드9' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드10' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드11' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드12' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드13' , 1, systimestamp);
 insert into POPULAR_KEYWORD(KEYWORD,INQ_CNT, REG_DT) values('키워드14 ' , 1, systimestamp);
 
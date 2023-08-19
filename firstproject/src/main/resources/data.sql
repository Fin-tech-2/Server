INSERT INTO article(title, goalprice, startdate, enddate, price, category ,content) VALUES ('aaa', '50000', '0809', '0815', '20000' , 'fund' ,'1111');
INSERT INTO article(title, goalprice, startdate, enddate, price, category ,content) VALUES ('bb', '50000', '0809', '0815', '30000', 'fund' ,'1111');
INSERT INTO article(title, goalprice, startdate, enddate, price, category ,content) VALUES ('ccc', '50000', '0809', '0815', '50000', 'fund' ,'1111');

--article의 더미 데이터
-- article의 더이 데이터
INSERT INTO article(id, title, goalprice, startdate, enddate, category ,content) VALUES (4, '후기', '50000', '0809', '0815', 'fund' ,'너무 좋아요');
INSERT INTO article(id, title, goalprice, startdate, enddate, category ,content) VALUES (5, '후기?', '50000', '0809', '0815', 'fund' ,'추천!!');
INSERT INTO article(id, title, goalprice, startdate, enddate, category ,content) VALUES (6, '강추!!', '50000', '0809', '0815', 'fund' ,'강추');

--comment 더미 데이터
---- 4번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '강추');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '너무 좋아요');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Choi', '별이 5개');

---- 5번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '별이 5개');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '강추!!');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Choi', '너무 좋아요');

---- 6번 게시글의 댓글들
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '별이 5개');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '강추!!');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Choi', '너무 좋아요');
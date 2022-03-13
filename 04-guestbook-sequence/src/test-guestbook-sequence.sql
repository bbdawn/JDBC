-- TABLE 생성
CREATE TABLE guestbook(
	guestbook_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(1000) NOT NULL
)
-- SEQUENCE 생성 
CREATE SEQUENCE guestbook_seq;

SELECT guestbook_seq.nextval FROM dual;

-- SEQUENCE 삭제
DROP SEQUENCE guestbook_seq;


SELECT * FROM guestbook;

INSERT INTO guestbook(guestbook_no,title,content) 
VALUES(guestbook_seq.nextval,'즐목','즐거운 목요일입니다');

-- getAllGuestBookList sql 
SELECT guestbook_no,title,content FROM guestbook ORDER BY guestbook_no DESC

-- findGuestBookListByNo sql
SELECT guestbook_no,title,content FROM guestbook 
WHERE guestbook_no BETWEEN 2 AND 4 
ORDER BY guestbook_no DESC;

--LIKE연산자를 이용해 TITLE에 금 이 포함된 방명록 정보를 조회
SELECT GUESTBOOK_NO,TITLE,CONTENT FROM GUESTBOOK
WHERE TITLE LIKE '%금%'

--JDBC에 위를 적용
SELECT GUESTBOOK_NO,TITLE,CONTENT FROM GUESTBOOK
WHERE TITLE LIKE '%'||?||'%'

--문자열 합치기 -> ||
SELECT '불'||'금' FROM DUAL;























-- DDL: CREATE를 이용한 회원 테이블 생성
CREATE TABLE typetest(
 name VARCHAR2(9) PRIMARY KEY,
 money VARCHAR2(100) NOT NULL
)
--DML : INSERT
--ERROR : NAME의 최대 사이즈가 9, 아래 사이즈는 10자이므로 ERROR
INSERT INTO typetest(name,money) VALUE('ABCDEFGHIJ',100);

-9자이므로 succeeded
INSERT INTO typetest(name,money) VALUE('ABCDEFGHI',100);
- succeeded
INSERT INTO typetest(name,money) VALUE('아이유',100);
--error: 한글은 한자당 3byte를 차지하므로 3자까지 가능
INSERT INTO typetest(name,money) VALUE('박보검님',100);

--DML : UPDATE구문으로 아이유의 money에 200을 누적시킨다
UPDATE TYPETEST SET MONEY=MONEY+200 WHERE NAME='아이유';

--DML : SELECT구문으로 NAME이 아이유인 대상의 MONEY를 조회 
SELECT MONEY FROM TYPETEST WHERE NAME='아이유';

--DDL : DROP을 이용한 테이블 삭제
DROP TABLE TYPETEST;

--DDL : ALTER을 이용한 테이블 정보 변경 (TABLE명 변경, COLUMN명 변경, 제약조건 및 타입 변경)
CREATE TABLE ALTER_TEST(
	ID VARCHAR2(100) PRIMARY KEY,
	HIT NUMBER DEFAULT 0
)

--제약조건 DEFAULT : 별도로 INSERT하지 않으면 초기값이 0으로 저장됨
INSERT INTO ALTER_TEST(ID) VALUE('JAVA');

--HIT가 0으로 저장되어있음을 확인
SELECT * FROM ALTER_TEST;

--DDL: ALTER를 이용해 테이블명을 변경해본다
ALTER TABLE ALTER_TEST RENAME TO ALTER_TEST2;

--DDL: ALTER를 이용해 컬럼명을 HIT에서 COUNT로 변경해본다 
ALTER TABLE ALTER_TEST2 RENAME COLUMN HIT TO COUNT;








































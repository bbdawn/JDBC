-- DDL: CREATE를 이용한 회원 테이블 생성
CREATE TABLE typetest(
 name VARCHAR2(9) PRIMARY KEY,
 money VARCHAR2(100) NOT NULL,
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



























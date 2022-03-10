--DDL
/*
	테이블명 : product
	컬럼명 : id, name, maker, price
	데이터타입 : id와 price는 number. name과 maker는 varchar2(100)
	제약조건 : id는 primary key. name과 make는 not null. price는 default 0
*/

CREATE TABLE PRODUCT(
	ID NUMBER PRIMARY KEY,
	NAME VARCHAR2(100) NOT NULL,
	MAKER VARCHAR2(100) NOT NULL,
	PRICE NUMBER DEFAULT 0
)

--DML
INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(1,'불닭볶음면','삼양',1500);
INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(2,'진라면','오뚜기',1100);
INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(3,'테라','하이트진로',1800);
INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(4,'참이슬후레쉬','하이트진로',1300);

SELECT * FROM PRODUCT;

--데이터베이스 함수 테스트
--수량 COUNT(*)
SELECT COUNT(*)
FROM PRODUCT;

--상품 최저가 구하기 (MIN)
SELECT MIN(PRICE)
FROM PRODUCT;

--상품 최고가 구하기 (MAX)
SELECT MAX(PRICE) AS 최고가
FROM PRODUCT;

--상품 평균가 구하기 (AVG)
SELECT AVG(PRICE) AS 평균가
FROM PRODUCT;

--정렬 : 오름차순 정렬 ASC, 내림차순 정렬 DESC
--PRICE기준으로 정렬-> ORDER BY!!
--ORDER BY -> DEFAULT는 오름차순(ASC)
SELECT NAME,PRICE
FROM PRODUCT
ORDER BY PRICE; 

SELECT NAME,PRICE
FROM PRODUCT
ORDER BY PRICE DESC; 

/*
 *  SELECT 컬럼명
 *  FROM 테이블명
 *  WHERE 조건절 AND 조건절
 * 	GROUP BY 컬럼명
 *  HAVING
 *  ORDER BY
 */

--MAKER가 하이트진로인 상품의 ID,NAME,PRICE를 조회하되, PRICE 내림차순으로 정렬
SELECT ID,NAME,PRICE
FROM PRODUCT
WHERE MAKER='하이트진로'
ORDER BY PRICE DESC;





























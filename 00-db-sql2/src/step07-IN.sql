/*
 * #1 IN 연산자
 * : 특정값이 포함되는 데이터를 조회하고자 할 때 사용하는 연산자
 * SELECT 컬럼,컬럼
 * FROM 테이블명 
 * WHERE 컬럼 IN('데이터','데이터)
 * 
 * EX) MAKER가 농심 또는 오뚜기인 상품듸 정보들을 조회
 * 
 * 
 * #2 NOT IN 연산자
 * 	: 특정 값이 포함되지 않는 데이터를 조회하고자 할 떄 사용 
 * SELECT 컬럼,컬럼
 * FROM 테이블명 
 * WHERE 컬럼 NOT IN('데이터','데이터)
 * 
 * EX) MAKER가 농심 또는 오뚜기가 아닌 상품의 정보들을 조회
 */


CREATE TABLE FOOD(
	ID NUMBER PRIMARY KEY,
	NAME VARCHAR2(100) NOT NULL,
	MAKER VARCHAR2(100) NOT NULL,
	PRICE NUMBER NOT NULL
)

CREATE SEQUENCE FOOD_SEQ;

INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'후라이드','또래오래',15000);
INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'소곱창','대한곱창',20000);
INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'양념치킨','또래오래',16000);
INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'참치회','이춘복참치',35000);
INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'파닭','또래오래',17000);
INSERT INTO FOOD(ID,NAME,MAKER,PRICE) VALUES(FOOD_SEQ.NEXTVAL,'미니전골','대한곱창',18000);

COMMIT

SELECT COUNT(*) FROM FOOD;

--MAKER가 대한곱창, 이춘복참치 인 FOOD 정보를 조회
SELECT ID,NAME,MAKER,PRICE
FROM FOOD
WHERE MAKER IN('대한곱창','이춘복참치')
--위 SQL을 아래와 같이도 표현할 수 있다 
SELECT ID,NAME,MAKER,PRICE
FROM FOOD
WHERE MAKER='대한곱창' OR MAKER='이춘복참치'


--MAKER가 대한곱창, 이춘복참치가 아닌 FOOD 정보를 조회
SELECT ID,NAME,MAKER,PRICE
FROM FOOD
WHERE MAKER NOT IN('대한곱창','이춘복참치');
--위 SQL을 아래와 같이도 표현할 수 있다 
SELECT ID,NAME,MAKER,PRICE
FROM FOOD
WHERE MAKER<>'대한곱창' AND MAKER<>'이춘복참치' 
--<> : NOT



--전체 음식의 평균가보다 MEMVER별 음식 평균가가 낮은 MAKER의 음식정보
SELECT NAME, PRICE, MAKER
FROM FOOD
WHERE MAKER IN(
	SELECT MAKER
	FROM FOOD
	GROUP BY MAKER
	HAVING AVG(PRICE)<(SELECT AVG(PRICE) FROM FOOD)
)
ORDER BY PRICE DESC


SELECT * FROM S_EMPLOYEE;
--JOB 개발4명, 총무3명, 기획1명

--JOB 별 사원수가 3명 이상인 JOB(개발,총무)에 해당하는 사람의 EMPNO, NAME, JOB을 조회. EMPNO내림차순
SELECT EMPNO, NAME, JOB
FROM S_EMPLOYEE
WHERE JOB IN(
	SELECT JOB
	FROM S_EMPLOYEE
	GROUP BY JOB
	HAVING COUNT(*)>=3
)
ORDER BY EMPNO DESC

--












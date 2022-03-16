/* 
오라클 시퀀스(sequence)
: 순차적으로 증가, 유일한 값을 생성하기 위한 객체 
주로 primary key ( unique + not null ) 를 생성하기 위해 사용
테이블과는 독립적 구조 
​
CREATE SEQUENCE 시퀀스명 
[START WITH 시작번호]
[INCREMENT BY 증가값]
[MAXVALUE 최대값] 
[MINVALUE 최소값]
[ CYCLE or NOCYCLE ] 
[ NOCACHE ] 
​
Oracle dual table 
: 오라클에서 제공하는 기본 테이블 
컬럼 하나만 존재 , 주로 시퀀스 또는 날짜함수, 산술연산에 사용
sys Admin 계정에서 관리하고 수정 및 삭제 등 조작은 불가 
*/

SELECT * FROM DUAL;

--연산시 DUAL을 사용
SELECT 2*5 FROM DUAL;

--현재시간 SYSDATE
SELECT SYSDATE FROM DUAL;


--시퀀스 생성
CREATE SEQUENCE TEST_SEQ;

--DUAL TABLE을 이용해서 시퀀스 값을 확인 : NEXTVAL -> 시퀀스 다음값을 생성
SELECT TEST_SEQ.NEXTVAL FROM DUAL;

--시퀀스 삭제
DROP SEQUENCE TEST_SEQ;

--TEST_SEQ 시퀀스를 다시 생성 (옵션 START WITH 시작번호)
CREATE SEQUENCE TEST_SEQ START WITH 7; -->7부터 시작함



--TABLE에서 시퀀스를 활용해보기
--1)테이블생성
CREATE TABLE CAR(
	CAR_NO NUMBER PRIMARY KEY,
	MODEL VARCHAR2(100) NOT NULL
)

--2)시퀀스 생성
CREATE SEQUENCE CAR_SEQ;

--3)시퀀스 활용
INSERT INTO CAR(CAR_NO,MODEL) VALUES(CAR_SEQ.NEXTVAL,'소나타'); -->CARNO : 1
INSERT INTO CAR(CAR_NO,MODEL) VALUES(CAR_SEQ.NEXTVAL,'제네시스'); --> CARNO : 2

SELECT * FROM CAR;



--























​
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















/*
	DB Modeling 연습
	
	**요구사항**
	주식 거래 시스템을 구현하고자 한다
	주식 정보는 주식명과 주당 가격으로 구성된다 
	현재 공시된 모든 주식 정보는 고객에게 리스트로 제공되어야 한다
	고객정보는 아이디,패스워드,이름,주소로 구성된다
	고객이 시스템에 로그인하면 주소와 이름이 화면에 제공된다
	또한 고객이 배당받은 주식정보(주식명,주당가격,수량,총액)가 리스트로 제공된다 
	공시된 주식 정보를 통해 매수와 매도가 가능하다 
	
	DB Modeling을 위해서는 실제 데이터를 작성해보는 것이 필요하다
	또한 UI설계를 기반으로 데이터를 확인해보면 원활한 DB설계가 가능하다 

	사례) 고객과 주식은 다대다 관계이다
		 한명의 고객은 다수의 주식을 배당받을 수 있다
		 하나의 주식은 다수의 고객에게 배당될 수 있다
		 
		 다대다(Many To Many Relation)는 교차엔티티(Intersection Entity) 또는 제휴엔티티(Association Entity)로 해결한다
		 
		 복합키(복합pk) : 두 개 이상의 컬럼을 이용해 pk(식별자)를 만드는 것을 말한다
		 
		 CUSTOMER |----0|<-SHARES->|0----|STOCK
		 					교차엔티티로 다대다 관계 해소
		 					
		 고객일반정보      고객과 주식의 관계정보   주식일반정보
		 
*/
--고객 일반 정보 (부모테이블)
CREATE TABLE CUSTOMER(
	ID VARCHAR2(100) PRIMARY KEY,
	PASSWORD VARCHAR2(100) NOT NULL,
	NAME VARCHAR2(100) NOT NULL,
	ADDRESS VARCHAR2(100) NOT NULL
)

--주식 일반 정보 (부모테이블)
CREATE TABLE STOCK(
	SYMBOL VARCHAR2(100) PRIMARY KEY,
	PRICE NUMBER NOT NULL
)

--고객과 주식 관계 테이블 (자식테이블)
--ID와 SYMBOL은 복합PK로 구성한다 -> 두개이상의 컬럼을 이용해 식별자를 만드는 것을 복합키라고 한다. 
CREATE TABLE SHARES(
	ID VARCHAR2(100),
	SYMBOL VARCHAR2(100),
	QUANTITY NUMBER NOT NULL,
	CONSTRAINT FK_CUSTOMER_ID FOREIGN KEY(ID) REFERENCES CUSTOMER(ID),
	CONSTRAINT FK_SYMBOL_ID FOREIGN KEY(SYMBOL) REFERENCES STOCK(SYMBOL),
	CONSTRAINT PK_SHARES PRIMARY KEY(ID,SYMBOL)
)

--고객 정보 등록
INSERT INTO CUSTOMER(ID,PASSWORD,NAME,ADDRESS) VALUES('java','a','아이유','오리');
INSERT INTO CUSTOMER(ID,PASSWORD,NAME,ADDRESS) VALUES('spring','a','강하늘','종로');

--주식 정보 등록
INSERT INTO STOCK(SYMBOL,PRICE) VALUES('삼성',2000);
INSERT INTO STOCK(SYMBOL,PRICE) VALUES('LG',2500);
INSERT INTO STOCK(SYMBOL,PRICE) VALUES('현대',3000);

COMMIT

SELECT * FROM CUSTOMER;
SELECT * FROM STOCK;


--제약조건 테스트
--fail : fk 제약조건에 위배됨. customer의 id에 spring은 존재하지 않는다
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('spring','삼성',2);

--fail : fk 제약조건에 위배됨. stock의 symbol에 sk는 존재하지 않는다
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('java','SK',2);

--succeeded
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('java','삼성',2);

--fail : 복합키 제약조건에 위배됨 unique constraint(SCOTT.PK_SHARES) violated
--java와 삼성의 조합된 정보는 shares 테이블에서 유일해야한다. 
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('java','삼성',5);

--java id와 현대의 조합은 현재 유일하기 때문에 succeeded
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('java','현대',5);
--java id와 LG의 조합은 현재 유일하기 때문에 succeeded
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('java','LG',10);


--JAVA 아이디의 고객이 삼성 주식을 더 매수하려고 한다면 UPDATE를 해야한다
UPDATE SHARES SET QUANTITY=QUANTITY+3 WHERE ID='java' AND SYMBOL='삼성';

SELECT * FROM SHARES;

INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('spring','LG',3);

--fail : 복합키 제약조건에 위배됨 unique constraint(SCOTT.PK_SHARES) violated
--spring+LG의 조합된 정보는 shares 테이블에서 유일해야한다. 
INSERT INTO SHARES(ID,SYMBOL,QUANTITY) VALUES('spring','LG',4);



--JOIN SQL 연습(CUSTOMER, SHARES, STOCK TABLE 3개 테이블을 JOIN)
SELECT C.ID, C.PASSWORD,C.NAME,C.ADDRESS,S.SYMBOL,S.PRICE,SH.QUANTITY
FROM CUSTOMER C, SHARES SH, STOCK S
WHERE C.ID=SH.ID AND SH.SYMBOL=S.SYMBOL;

SELECT C.ID, C.PASSWORD, C.NAME, C.ADDRESS, S.SYMBOL, S.PRICE, SH.QUANTITY
FROM  SHARES SH 
INNER JOIN CUSTOMER C ON C.ID=SH.ID
INNER JOIN STOCK S ON SH.SYMBOL=S.SYMBOL;















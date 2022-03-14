-- DDL 
CREATE TABLE account(
	account_no NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	password VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL
)
CREATE SEQUENCE account_seq;

-- DML 

-- 1. 계좌개설 
INSERT INTO account(account_no,name,password,balance) VALUES(account_seq.nextval,'아이유','1234',1000);
SELECT * FROM account;


--2. 계좌 잔액조회
--2-1)계좌가 없을 때 :: 조회헸을 때 결과가 없음.
SELECT PASSWORD,BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=11;
--2-2)계좌가 존재할 때에는 패스워드를 확인 
SELECT PASSWORD,BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=1;


















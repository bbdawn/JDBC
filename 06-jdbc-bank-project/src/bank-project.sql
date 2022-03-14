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


--3. 계좌번호 유무와 비밀번호 일치 여부를 확인
SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT_NO=11;
SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT_NO=1;


--4. 입금(계좌번호 account_no 3을 이용해 sql test)
SELECT * FROM ACCOUNT;
--update 구문으로 account_no3의 계좌에 1000원을 입금해본다 
UPDATE ACCOUNT SET BALANCE=BALANCE+1000 WHERE ACCOUNT_NO=3;


--5.출금
UPDATE ACCOUNT SET BALANCE=BALANCE-100 WHERE ACCOUNT_NO=3;













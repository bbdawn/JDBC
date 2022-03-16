--한줄 주석
/* 
  여러줄 주석. SQL은 대소문자 구분하지 않는다
*/

/*
  SQL(Structured Query Language) : 데이터베이스에서 데이터를 정의, 조작, 제어하는 언어
  
  1. DDL(Data Definition Language, 데이터 정의어)-CREATE생성, DROP삭제, ALTER수정
  2. DML(Data Manipulation Language, 데이터 조작어)-INSERT삽입, SELECT조회, DELETE삭제, UPDATE수정
  3. DCL(Data Control Language, 데이터 제어어)-GRANT권한부여, REVOKE권한취소 
  											COMMIT실제 DB에 반영, ROLLBACK 작업을 취소하고 원상태로 복귀
  
  TABLE - 데이터를 저장하는 공간
  COLUMN - 속성(attribute)
  DATA TYPE - 문자열(VARCHAR2) 가변적인 문자열. 문자열만큼 공간 차지. 자바에서 STRING
  			  숫자형(NUMBER) 등등
  CONSTRAINT - 제약조건 ex) PRIMARY KEY제약조건 : NOT NULL(반드시 존재해야함) + UNIQUE(유일해야함)
  						  NOT NULL : NULL을 허용하지 않는다. 반드시 데이터가 존재해야한다)
  						  UNIQUE : 유일해야한다 
  
*/ 

-- SQL 실행 단축키 : 영역 지정 후 ALT + X

-- DDL: CREATE를 이용한 회원 테이블 생성
CREATE TABLE member(
 ID VARCHAR2(100) PRIMARY KEY,
 PASSWORD VARCHAR2(100) NOT NULL,
 NAME VARCHAR2(100) NOT NULL,
 ADDRESS VARCHAR2(100)
)
--DDL: DROP을 이용한 회원 테이블 삭제 
DROP TABLE MEMBER;

--DML: SELECT 데이터 조회
SELECT* FROM MEMBER;

--DML: INSERT 데이터 삽입
INSERT INTO MEMBER(ID,PASSWORD,NAME,ADDRESS) VALUES('java','abcd','아이유','오리');
INSERT INTO MEMBER(ID,PASSWORD,NAME,ADDRESS) VALUES('spring','dcba','박보검','죽전');


--PRIMARY KEY 제약조건 테스트 : id는 primary key로 제약조건이 명시되어있어 not null + unique이어야하므로 error. java id는 기존에 존재하기 때문에 안됨
INSERT INTO MEMBER(ID,PASSWORD,NAME,ADDRESS) VALUES('java','a','b','c');

--PRIMARY KEY 제약조건 테스트 : id는 primary key로 제약조건이 명시되어있어 not null + unique이어야하므로 error. id는 null을 입력할 수 없음 
INSERT INTO MEMBER(PASSWORD,NAME,ADDRESS) VALUES('a','b','c');

--NOT NULL 제약조건 테스트 : password는 not null제약조건이 명시되어있으므로 반드시 데이터를 입력해야한다.
INSERT INTO MEMBER(ID,NAME,ADDRESS) VALUES('jsp','b','c');

--아래 SQL은 succeded. address는 별도의 제약조건이 없으므로 null이 허용된다.
INSERT INTO MEMBER(ID,PASSWORD,NAME) VALUES('jsp','b','강하늘');

SELECT ID,ADDRESS FROM MEMBER;

--WHERE를 이용한 조회
SELECT * FROM MEMBER WHERE ID='java';

--DML : UPDATE 수정(id가 jsp인 회원의 address를 null에서 '강남으로 수정'하기)
UPDATE MEMBER SET ADDRESS='강남'WHERE ID='jsp';

SELECT * FROM MEMBER WHERE ID='jsp';

--DML : DELETE 삭제(id가 jsp인 회원을 삭제)
DELETE FROM MEMBER WHERE ID='jsp'

SELECT ID,ADDRESS FROM MEMBER;



--2일차
--회원 아이디 angel 패스워드 kind 이름 이상순 주소 애월읍 -> insert구문


INSERT INTO MEMBER(ID,PASSWORD,NAME,ADDRESS) VALUES('angel','kind','이상순','애월읍');

SELECT * FROM MEMBER;

--조건절(WHERE ~ AND) : NAME이 이상순이고 ADDRESS가 애월읍인 MEMBER의 ID와 PASSWORD를 조회해본다
SELECT ID,PASSWORD FROM MEMBER WHERE NAME='이상순'AND ADDRESS='애월읍';

--회원의 ADDRESS가 애월읍인 회원의 ADDRESS를 오리로 업데이트
UPDATE MEMBER SET ADDRESS='오리'WHERE ADDRESS='애월읍';































-- test sql 

CREATE TABLE  department(
		deptno NUMBER PRIMARY KEY,
		dname VARCHAR2(100) NOT NULL,
		loc VARCHAR2(100) NOT NULL,
		tel VARCHAR2(100) NOT NULL
	)
									   							   
    CREATE TABLE k_employee(
    	empno NUMBER PRIMARY KEY,
    	ename VARCHAR2(100) NOT NULL,
    	sal NUMBER NOT NULL,
    	job VARCHAR2(100) NOT NULL,
    	deptno NUMBER NOT NULL,
    	CONSTRAINT fk_deptno FOREIGN KEY(deptno) REFERENCES department(deptno)
    )
    
    -- empno 3 에 해당하는 사원의 empno,ename,sal,job, 부서 정보인 deptno,dname,loc,tel 을 조회 
    
    SELECT e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc,d.tel
    FROM k_employee e, department d
    WHERE e.deptno=d.deptno 
    AND e.empno=3
    
    SELECT e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc,d.tel
    FROM k_employee e 
    INNER JOIN department d ON e.deptno=d.deptno 
    WHERE  e.empno=3
    
    ---------------------------------------
    
    --사원의 job이 총무인 사원의 ename, sal, dname, loc를 sal 내림차순으로 조회하세요
    SELECT e.ename, e.sal, d.dname,d.loc
    FROM K_EMPLOYEE E
    INNER JOIN DEPARTMENT D ON E.DEPTNO=D.DEPTNO
    WHERE JOB = '총무'
    ORDER BY d.loc DESC
    
    
    -------------------
    SELECT *
    FROM K_EMPLOYEE;
    
    SELECT *
    FROM DEPARTMENT;
    -------------------
       /*
    	 사원번호가 3 인 사원의 사원명, 월급, 직종, 부서명, 근무지를 조회하고자 한다 
    	 사원 테이블과 부서 테이블의 정보를 결합해서 조회해야 한다 -> JOIN SQL 
    	 
    	 방법 1
    	 SELECT 컬럼명, 컬럼명 
    	 FROM 테이블명 별칭, 테이블명 별칭  
    	 WHERE 별칭.컬럼명=별칭.컬럼명 
    	 
    	 방법 2
    	 SELECT 컬럼명,컬럼명
    	 FROM 테이블명 별칭
    	 INNER JOIN 테이블명 별칭 ON 별칭.컬럼명=별칭.컬럼명 
    */
    
    
    
    
    
    
    
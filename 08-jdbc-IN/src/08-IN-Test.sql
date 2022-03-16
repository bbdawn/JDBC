-- SQL TEST
/*
		s_employee 테이블에 저장된 전체 사원 정보의 평균 월급보다 
		직종별(job) 평균월급이 낮은 직종job 에 해당하는 사원 정보(empno,name,job,salary)를 salary 내림차순으로 조회해서 리스트로 반환받아 출력  			
*/
-- 전체 사원 평균월급 : 반올림하면 763
SELECT ROUND(AVG(salary)) FROM s_employee;

-- JOB 별 사원의 평균월급 ( GROUP BY ) 
SELECT job,AVG(salary) FROM s_employee GROUP BY job

-- GROUP BY, HAVING, IN , SUBQUERY, ORDER BY를 이용해 sql 을 작성 
SELECT empno,name,job,salary	FROM	s_employee
WHERE job IN(
	SELECT job FROM s_employee 
	GROUP BY job HAVING AVG(salary)<(SELECT ROUND(AVG(salary)) FROM s_employee)
) ORDER BY salary DESC











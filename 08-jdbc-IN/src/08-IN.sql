--s_employee 테이블에 지정된 전체 사원 정보의 평균 월급보다
--직종별(job) 평균 월급이 낮은 직종 job에 해당하는 해당하는 사원 정보를 조회해서 리스트로 반환받아 출력
--salary 내림차순으로 정렬

SELECT EMPNO, NAME, JOB, SALARY
FROM S_EMPLOYEE
WHERE JOB IN(
	SELECT JOB
	FROM S_EMPLOYEE
	GROUP BY JOB
	HAVING AVG(SALARY)<(SELECT ROUND(AVG(SALARY))
						FROM S_EMPLOYEE)
	
)
ORDER BY SALARY DESC;



/*
 * s_employee 테이블에 저장된 사원들을 대상으로
 * job을 그룹화하여 job, 사원수, 최고salary를 조회(최고 salary 내림차순)
 */

SELECT JOB, COUNT(*) AS 사원수, MAX(SALARY) AS 고연봉
FROM S_EMPLOYEE
GROUP BY JOB
ORDER BY MAX(SALARY) DESC;
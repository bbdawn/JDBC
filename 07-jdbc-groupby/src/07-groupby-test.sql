/*
 * s_employee 테이블에 저장된 사원들을 대상으로
 * job을 그룹화하여 job, 사원수, 최고salary를 조회(최고 salary 내림차순)
 */

SELECT JOB, COUNT(*) AS 사원수, MAX(SALARY) AS 고연봉
FROM S_EMPLOYEE
GROUP BY JOB
ORDER BY MAX(SALARY) DESC;




insert into product(id,name,maker,price)VALUES(7,'새우깡','농심',1000);
insert into product(id,name,maker,price)VALUES(8,'양파깡','농심',900);
COMMIT

/*
 * 전체 product의 평균가보다 maker그룹별 평균가가 낮은 그룹의 maker명과 상품평균가를 내림차순으로 조회
 */
SELECT MAKER,AVG(PRICE) AS 상품평균가
FROM PRODUCT 
GROUP BY MAKER
HAVING AVG(PRICE)< (SELECT AVG(PRICE) FROM PRODUCT)
ORDER BY 상품평균가 DESC;













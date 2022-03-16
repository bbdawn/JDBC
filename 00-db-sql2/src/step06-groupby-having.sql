/*
	GROUP BY ~ HAVING
	GROUP BY : 테이블에서 특정 컬럼을 기준으로 그룹화하여 검색할 때 사용
				데이터를 원하는 그룹으로 나눌 수 있따 
	
	HAVING : GROUP BY와 함께 사용하는 조건절 (그룹에 대한 조건을 지정)
	
	예) 상품 테이블에서 제조사별 상품수, 평균가 등
		사원 테이블에서 직종별 사원수, 평균월급 등
*/

SELECT * FROM PRODUCT;

--maker별 상품수
SELECT MAKER,COUNT(*) AS 상품수
FROM PRODUCT
GROUP BY MAKER
ORDER BY 상품수 DESC

--maker별 상품수를 조회하되 상품수가 1개를 초과하는 maker들만 조회
SELECT MAKER,COUNT(*) AS 상품수
FROM PRODUCT
GROUP BY MAKER
HAVING COUNT(*)>1
ORDER BY 상품수 DESC/*ORDER BY에서는 별칭 사용가능*/

--maker별 상품평균가(AVG(컬럼))를 조회하여 평균가 내림차순으로 정렬
SELECT MAKER,AVG(PRICE) AS 상품평균가
FROM PRODUCT
GROUP BY MAKER
ORDER BY 상품평균가 DESC

--maker별 상품평균가가 1550을 초과하는 maker, 상품수 평균가를 상품수 오름차순으로 조회
SELECT MAKER, COUNT(*) AS 상품수, AVG(PRICE) AS 상품평균가
FROM PRODUCT
GROUP BY MAKER
HAVING AVG(PRICE)>1550
ORDER BY 상품수 ASC, 상품평균가 ASC;


SELECT * FROM S_EMPLOYEE;
--JOB별 JOB,사원수
SELECT JOB, COUNT(*) AS 사원수
FROM S_EMPLOYEE
GROUP BY JOB
ORDER BY 사원수 DESC;

--JOB을 기준으로 그룹화하여 JOB,평균월급을 조회하되, 평균 월급이 700이상 JOB에 한해서 조회한다. 평균월급 내림차순
SELECT JOB, AVG(SALARY) AS 평균월급
FROM S_EMPLOYEE
GROUP BY JOB
HAVING AVG(SALARY)>=700
ORDER BY 평균월급 DESC;

/*
 *  부서별 평균 월급
 * 	개발 875
 * 	총무 700
 * 	기획 500
 * 
 * 	전체사원 평균 월급 763
 */
SELECT ROUND(AVG(SALARY)) FROM S_EMPLOYEE;
--전체사원의 평균 월급(763)보다 JOB별 평균 월급액이 작은 JOB의 JOB,평균월급을 조회 (평균월급 내림차순)
SELECT JOB, AVG(SALARY) AS 평균월급
FROM S_EMPLOYEE
GROUP BY JOB
HAVING AVG(SALARY)<(SELECT ROUND(AVG(SALARY)) FROM S_EMPLOYEE)
ORDER BY 평균월급 DESC;


































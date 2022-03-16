package test;

import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestIn {

	public static void main(String[] args) {
		try {
			EmployeeDAO dao=new EmployeeDAO();
			/*
			 * s_employee 테이블에 지정된 전체 사원 정보의 평균 월급보다
			 * 직종별(job) 평균 월급이 낮은 직종 job에 해당하는 해당하는 사원 정보를 조회해서 리스트로 반환받아 출력
			 * salary 내림차순으로 정렬
			 */
			ArrayList<EmployeeVO> list=dao.findEmployeeList();
			for(EmployeeVO vo:list)
				System.out.println(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

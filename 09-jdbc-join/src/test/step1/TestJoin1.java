package test.step1;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestJoin1 {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		int empno = 3;
		try {
			EmployeeVO vo = dao.findEmployeeByEmpNo(empno);
			if (vo != null) {
				System.out.println("사원번호:"+vo.getEmpno());
				System.out.println("사원명:"+vo.getEname());
				System.out.println("월급:"+vo.getSalary());
				System.out.println("직종:"+vo.getJob());
				System.out.println("부서번호:"+vo.getDepartmentVO().getDeptno());
				System.out.println("부서명:"+vo.getDepartmentVO().getDname());
				System.out.println("근무지:"+vo.getDepartmentVO().getLoc());
				System.out.println("부서전화번호:"+vo.getDepartmentVO().getTel());
			} else {
				System.out.println(empno+" 사원번호에 해당하는 사원정보가 없습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





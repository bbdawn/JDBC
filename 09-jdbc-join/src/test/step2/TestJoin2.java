package test.step2;

import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestJoin2 {
	public static void main(String[] args) {
		EmployeeDAO dao=new EmployeeDAO();
		try {
			String job="총무";
			ArrayList<EmployeeVO> list=dao.findEmployeeByJob(job);
			for(EmployeeVO empVO:list) {
				System.out.println(empVO.getEname()+" "+empVO.getSalary()+" "+empVO.getDepartmentVO().getDname()+" "+empVO.getDepartmentVO().getLoc());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package test.step1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.EmployeeDAO;

public class TestEmployeeGroupBy {
	public static void main(String[] args) {
		EmployeeDAO dao=new EmployeeDAO();
		/*
		 * s_employee 테이블에 저장된 사원들을 대상으로
		 * job을 그룹화하여 job, 사원수, 최고salary를 조회(최고 salary 내림차순)
		 */
		
		try {
			ArrayList<HashMap<String,Object>> list=dao.findJobGroupList();
			for(int i=0; i<list.size();i++) {
				HashMap<String,Object> map=list.get(i);
				System.out.println(map.get("job")+" "+map.get("empcount")+" "+map.get("highestsal"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}

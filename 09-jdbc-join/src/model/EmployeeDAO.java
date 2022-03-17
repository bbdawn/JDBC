package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

public class EmployeeDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL,DbInfo.USER,DbInfo.PASS);
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}
	/*
	 	SELECT e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc,d.tel
	    FROM k_employee e, department d
	    WHERE e.deptno=d.deptno 
	    AND e.empno=?
	 */
	public EmployeeVO findEmployeeByEmpNo(int empno) throws SQLException {
		EmployeeVO empVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT e.empno,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc,d.tel ");
			sql.append("FROM k_employee e, department d ");
			sql.append("WHERE e.deptno=d.deptno ");
			sql.append("AND e.empno=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, empno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				DepartmentVO deptVO=new DepartmentVO(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc"),rs.getString("tel"));
				empVO=new EmployeeVO(rs.getInt("empno"), rs.getString("ename"), rs.getInt("sal"), rs.getString("job"), deptVO);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return empVO;
	}
	
	public ArrayList<EmployeeVO> findEmployeeByJob(String job) {
		return null;
	}
	
	
	/*
	  	SELECT e.ename, e.sal, d.dname,d.loc
    	FROM K_EMPLOYEE E
    	INNER JOIN DEPARTMENT D ON E.DEPTNO=D.DEPTNO
    	WHERE JOB = '총무'
    	ORDER BY d.loc DESC
	 */
	
}





























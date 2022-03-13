package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbInfo;

public class CardDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}
	/*
	 *  트랜잭션 처리를 하지 않았을 때 발생할 수 있는 문제를 확인하는 메서드 
	 *  
	 *  별도의 커밋모드를 명시하지 않으면 자동커밋(auto commit)모드가 JDBC의 기본
	 *  1. 카드 등록 ( insert card ) 
	 *  2. 포인트 등록 ( insert point ) 
	 */
	public void registerCardAndPointVer1(String id,String name,String pointType,int point) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String insertCardSql="INSERT INTO card(id,name) VALUES(?,?)";
			String insertPointSql="INSERT INTO point(id,point_type,point) VALUES(?,?,?)";
			pstmt=con.prepareStatement(insertCardSql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int result1=pstmt.executeUpdate();
			System.out.println("카드 등록: "+result1);
			pstmt.close();
			pstmt=con.prepareStatement(insertPointSql);
			pstmt.setString(1, id);
			pstmt.setString(2, pointType);
			pstmt.setInt(3, point);
			int result2=pstmt.executeUpdate();
			System.out.println("포인트 등록:"+result2);
		}finally {
			closeAll(pstmt, con);
		}
	}
	/*
	 * 위의 registerCardAndPointVer1() 메서드에 Transaction 처리를 적용하는 메서드 
	 * 
	 * 트랜잭션 처리를 통해 카드 및 포인트의 등록시점이 정상적으로 수행되면 실제 db에 반영 commit 처리 
	 * 카드 및 포인트의 등록시 장애가 발생하면 수행했던 작업을 취소하고 원상태로 되돌리는 rollback 처리 
	 * 
	 * 
	 * try{
	 * 수동커밋모드 con.setAutoCommit(false) : 직접 commit 을 실행할 때 작업내용이 db에 반영 
	 * 		카드등록
	 * 		포인트등록 
	 * 		con.commit();
	 * }catch(){
	 * 		con.rollback();
	 * }finally{
	 * 
	 * }
	 */
	public void registerCardAndPointVer2(String id,String name,String pointType,int point) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			//수동 커밋모드로 변경 
			con.setAutoCommit(false);
			String insertCardSql="INSERT INTO card(id,name) VALUES(?,?)";
			String insertPointSql="INSERT INTO point(id,point_type,point) VALUES(?,?,?)";
			pstmt=con.prepareStatement(insertCardSql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int result1=pstmt.executeUpdate();
			System.out.println("카드 등록: "+result1);
			pstmt.close();
			pstmt=con.prepareStatement(insertPointSql);
			pstmt.setString(1, id);
			pstmt.setString(2, pointType);
			pstmt.setInt(3, point);
			int result2=pstmt.executeUpdate();
			System.out.println("포인트 등록:"+result2);
			con.commit();
			System.out.println("commit 실행: 작업내용을 실제 db에 반영");
		}catch(Exception e) {
			con.rollback();
			System.out.println("장애발생 rollback 처리해서 작업내용 취소하고 원상태로 되돌림 "+e.getMessage());
		}finally {
			closeAll(pstmt, con);
		}
	}
}
































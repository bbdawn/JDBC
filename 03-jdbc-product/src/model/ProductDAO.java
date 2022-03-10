//DAO : Data Access Object _데이타베이스 연동 로직 정리
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbInfo;

public class ProductDAO {
	public ProductDAO() throws ClassNotFoundException{
		Class.forName(DbInfo.DRIVER);
	}
	
	//insert,delete,update할 때 사용함
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	
	//select시에 사용
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}

	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
	}
	
	//총 상품수 반환하기
	public int getProductTotalCount() throws SQLException {
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT COUNT(*) FROM PRODUCT";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
			totalCount=rs.getInt(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	/*
	 * 아이디에 대한 상품 정보를 조회
	 * 존재하면 ProductVO를 반환
	 * 존재하지 않으면 null을 반환
	 * 
	 * 아이디가 int형이므로 아래 메서드를 활용
	 * pstmt.setInt(1,id)
	 */
	public ProductVO findProductById(int id) throws SQLException {
		ProductVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			String sql="SELECT NAME,MAKER,PRICE FROM PRODUCT WHERE ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			
			//id는 pk이므로 조회 결과는 1행이 존재하거나 존재하지 않는다.
			if(rs.next())//조회 결과 행이 존재하면 true
				vo=new ProductVO(id,rs.getString(1),rs.getString(2),rs.getInt(3));
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}

	/*
	 * INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(?,?,?,?)
	 */
	//UPDATE하기
	public void registerProduct(ProductVO productVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="INSERT INTO PRODUCT(ID,NAME,MAKER,PRICE)VALUES(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, productVO.getId());//1은 첫번째 물음표
			pstmt.setString(2, productVO.getName());
			pstmt.setString(3, productVO.getMaker());
			pstmt.setInt(4, productVO.getPrice());
			pstmt.executeUpdate();//insert,delete,update시에 사용!
		}finally {
			closeAll(pstmt, con);
		}
		
		
		
		
		
		
	}//REGISTERPRODUCT

	public void deleteProductById(int id) {
		
		
		
		
		
	}//deleteProductById
}












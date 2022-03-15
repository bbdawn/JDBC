package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbInfo;

public class ProductDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL,DbInfo.USER,DbInfo.PASS);
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	
	public void classAll(ResultSet rs,PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(rs, pstmt, con);
	}

	public ArrayList<HashMap<String, Object>> findProductMakerGroupLessThanAvgPrice() throws SQLException {
		ArrayList<HashMap<String, Object>>list=new ArrayList<HashMap<String,Object>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			StringBuilder sql=new StringBuilder("SELECT MAKER,AVG(PRICE) AS 상품평균가 ");
			sql.append("FROM PRODUCT ");
			sql.append("GROUP BY MAKER ");
			sql.append("HAVING AVG(PRICE)< (SELECT AVG(PRICE) FROM PRODUCT) ");
			sql.append("ORDER BY 상품평균가 DESC");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("maker", rs.getString(1));
				map.put("avgprice", rs.getInt(2));
				list.add(map);
			}
		}finally {
			closeAll(rs,pstmt,con);
		}
		return list;
	}
}


























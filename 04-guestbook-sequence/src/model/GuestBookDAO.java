package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

// DAO : Data Access Object
public class GuestBookDAO {
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
	 * insert 구문 
	 * Connection , PreparedStatement , close 
	 */
	public void register(GuestBookVO guestBookVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="INSERT INTO guestbook(guestbook_no,title,content) values(guestbook_seq.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, guestBookVO.getTitle());//1 첫번째 물음표 
			pstmt.setString(2, guestBookVO.getContent());//2 두번째 물음표
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public ArrayList<GuestBookVO> getAllGuestBookList() throws SQLException {
		ArrayList<GuestBookVO> list=new ArrayList<GuestBookVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT guestbook_no,title,content FROM guestbook ORDER BY guestbook_no DESC";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookVO(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}		
		return list;
	}
	public ArrayList<GuestBookVO> findGuestBookListByNo(int startNo, int endNo) throws SQLException {
		ArrayList<GuestBookVO> list=new ArrayList<GuestBookVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			//sql문이 길 경우 가변 문자열 StringBuilder를 이용한다 
			StringBuilder sql=new StringBuilder("SELECT guestbook_no,title,content FROM guestbook ");
			sql.append("WHERE guestbook_no BETWEEN ? AND ? ");
			sql.append("ORDER BY guestbook_no DESC");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, startNo);//첫번째 물음표에 할당 
			pstmt.setInt(2, endNo);//두번째 물음표에 할당 
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookVO(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public ArrayList<GuestBookVO> findGuestBookListLikeKeyword(String keyword) throws SQLException {
		ArrayList<GuestBookVO> list=new ArrayList<GuestBookVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT guestbook_no,title,content FROM guestbook WHERE title LIKE '%' || ? || '%' ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookVO(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}		
		return list;
	}
}




















































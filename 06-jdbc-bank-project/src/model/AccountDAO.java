//껍데기 역할
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbInfo;

public class AccountDAO {
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
	}//AccountDAO
	
	/**
	 * 계좌 개설하는 메서드 
	 * 예외흐름 : 초기 납입액이 1000원 미만일 경우 CreateAccountException을 발생시키고 전파한다 
	 * @param accountVO
	 * @throws SQLException
	 * @throws CreateAccountException
	 */
	
	public void createAccount(AccountVO accountVO) throws SQLException, CreateAccountException {
		if(accountVO.getBalance()<1000)
			throw new CreateAccountException("계좌 개설시 초기 납입금은 1000원 이상이어야 합니다");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="INSERT INTO account(account_no,name,password,balance) VALUES(account_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, accountVO.getName());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setInt(3, accountVO.getBalance());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	/**
	 * <<findBalanceByAccountNo>>
	 * 계좌의 잔액을 조회하는 메서드
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException을 발생시키고 전파한다 
	 * 계좌번호에 해당하는 계좌가 존재하되, 비비밀번호가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파한다
	 * 계좌번호에 해당하는 계좌가 존재하고 비밀번호가 일치하면 잔액(balance)를 반환한다 
	 * @param accountNo
	 * @param password
	 * @return balance
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	
	public int findBalanceByAccountNo(String accountNo,String password)throws SQLException,AccountNotFoundException,NotMatchedPasswordException {
		int balance=0;
		return balance;
	}//findBalanceByAccountNo
	
	
	
}






















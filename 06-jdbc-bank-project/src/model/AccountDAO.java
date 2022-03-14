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
	 * <<createAccount>><br>
	 * 계좌 개설하는 메서드 <br>
	 * 예외흐름 : 초기 납입액이 1000원 미만일 경우 CreateAccountException을 발생시키고 전파한다<br> 
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
	 * <<findBalanceByAccountNo>><br>
	 * 계좌의 잔액을 조회하는 메서드<br>
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException을 발생시키고 전파한다 <br>
	 * 계좌번호에 해당하는 계좌가 존재하되, 비비밀번호가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파한다<br>
	 * 계좌번호에 해당하는 계좌가 존재하고 비밀번호가 일치하면 잔액(balance)를 반환한다 <br>
	 * @param accountNo
	 * @param password
	 * @return balance
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	
	public int findBalanceByAccountNo(String accountNo,String password)throws SQLException,AccountNotFoundException,NotMatchedPasswordException {
		int balance=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT PASSWORD,BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(password)) {
					balance=rs.getInt(2);
				}else {
					throw new NotMatchedPasswordException("계좌의 패스워드가 일치하지 않습니다");
				}
			}else {
				throw new AccountNotFoundException(accountNo+" 계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
		}finally {
			closeAll(rs,pstmt,con);
		}
		return balance;
	}//findBalanceByAccountNo
	
	/**
	 * <<checkAccountNoAndPassword>><br>
	 * 계좌번호 유무와 계좌번호에 따른 비밀번호 일치여부를 확인하는 메서드<br>
	 * 매개변수로 전달된 계좌번호가 존재하지 않으면 AccountNotFoundException을 발생시키고 전파<br>
	 * 매개변수로 전달된 패스워드가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파 <br>
	 * @param accountNo
	 * @param password
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	public void checkAccountNoAndPassword(String accountNo, String password) throws SQLException,AccountNotFoundException,NotMatchedPasswordException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {//계좌번호에 따른 계좌가 존재하면
				if(rs.getString(1).equals(password)==false) {//DB에 저장된 비밀번호와 매개변수로 전달된 비밀번호가 다르면 
					throw new NotMatchedPasswordException("계좌에 따른 비밀번호가 일치하지 않습니다");
				}
			}else {//계좌번호에 따른 계좌가 존재하지 않을 때
				throw new AccountNotFoundException(accountNo+" 계좌번호에 따른 계좌가 존재하지 않습니다");
			}
		}finally {
			closeAll(rs,pstmt,con);
		}
	}//checkAccountNoAndPassword
	
	
	/**
	 * <<AccountDAO>><br>
	 * 계좌에 입금하는 메서드 <br>
	 * 입금액이 0원 이하이면 NoMoneyException을 발생시키고 전파 <br>
	 * 계좌번호가 존재하지 않으면 AccountNotFoundException을 발생시키고 전파<br>
	 * 패스워드가 일치하지 않으면 NoMoneyException을 발생시키고 전파 <br>
	 * 위 검증과정을 다 통과하면 입금처리된다 <br>
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws NotMatchedPasswordException
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 */
	public void deposit(String accountNo, String password, int money) throws SQLException, NotMatchedPasswordException, NoMoneyException, AccountNotFoundException{
		if(money<=0)
			throw new NoMoneyException("입금액은 0원을 초과해야합니다");
		//계좌번호 확인과 비밀번호 일치여부 확인은 위에서 만든 메서드를 활용하기
		checkAccountNoAndPassword(accountNo, password);
		
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection(); 
			String sql="UPDATE ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();//중요!!!! 빼먹지말기
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	
	/** 
	 * <<withdraw>> <br>
	 * 계좌 출금 메서드 <br>
	 * 출금액이 0원 이하이면 NoMoneyException 발생, 전파<br>
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException 발생, 전파<br>
	 * 계좌번호에 대한 비밀번호가 다를 경우 NotMatchedPasswordException 발생, 전파<br>
	 * 잔액이 부족할 경우 InsufficientBalanceException 발생, 전파<br>
	 * 
	 * 위의 검증 절차를 확인한 후 출금처리
	 * 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws InsufficientBalanceException
	 */
	public void withdraw(String accountNo, String password, int money) throws SQLException, NoMoneyException, AccountNotFoundException, NotMatchedPasswordException, InsufficientBalanceException {
		if(money<=0)
			throw new NoMoneyException("출금액은 0원을 초과해야합니다");
		int balance = findBalanceByAccountNo(accountNo, password);
		
		//출금액과 잔액을 비교해서 출금액이 잔액보다 크면 예외를 발생시키기
		if(balance<money) {
			throw new InsufficientBalanceException("잔액부족 : 출금액이 잔액보다 큽니다");
		}

		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection(); 
			String sql="UPDATE ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();//중요!!!! 빼먹지말기
		}finally {
			closeAll(pstmt, con);
		}
	}
}//AccountDAO






















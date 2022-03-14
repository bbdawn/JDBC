package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.NotMatchedPasswordException;

//step3 계좌번호 존재유무와 비밀번호 일치여부를 확인하는 메서드  
public class TestUnit3 {
	public static void main(String[] args) {
		
		try {
			AccountDAO dao=new AccountDAO();
			String accountNo="1";
			String password="1234";
			
			//test1 : 존재하지 않는 계좌번호 입력
			//accountNo="1111";
			//test2 : 존재하는 계좌번호에 일치하지 않는 비밀번호를 입력해 테스트
			accountNo="1";
			password="abcd";
			//test3 : 존재하는 계좌번호와 일치하는 비밀번호를 입력
			//		  test1과 test2를 주석처리한 후 테스트하기
			dao.checkAccountNoAndPassword(accountNo,password);
			System.out.println("계좌번호가 존재하고 비밀번호가 일치합니다");
		} catch(AccountNotFoundException e) {
			System.out.println(e.getMessage());//11 계좌번호에 따른 계좌가 존재하지 않습니다
		} catch(NotMatchedPasswordException e) {
			System.out.println(e.getMessage());//계좌에 따른 비밀번호가 일치하지 않습니다
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}









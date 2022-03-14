package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountVO;
import model.CreateAccountException;

/*
 * 계좌 개설 
 */
public class TestUnit1 {
	public static void main(String[] args) {

		try {
			AccountDAO dao = new AccountDAO();
			// 계좌 개설
			// 1.정상흐름테스트

			//AccountVO vo = new AccountVO("아이유", "1234", 1000);
			//dao.createAccount(vo);
			//System.out.println("계좌 개설");

			AccountVO vo=new AccountVO("이상순","1",2000);
			dao.createAccount(vo);
			System.out.println("계좌 개설");

			// 2. 대안흐름테스트
			// AccountVO vo=new AccountVO("박보검","1111",999);
			// dao.createAccount(vo);
			// System.out.println("계좌 개설");

			/*
			//TestUnit8을 위한 코드
			AccountVO vo=new AccountVO("이강인","1",90000);
			dao.createAccount(vo);
			AccountVO vo2=new AccountVO("손흥밍","1",90000);
			dao.createAccount(vo2);
			System.out.println("계좌 개설 완료");
			*/
		} catch (CreateAccountException ce) {
			System.out.println(ce.getMessage());// 계좌 개설시 초기 납입금은 1000원 이상이어야 합니다
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}

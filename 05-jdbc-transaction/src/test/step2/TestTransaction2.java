package test.step2;

import java.sql.SQLException;

import model.CardDAO;

/*
	아래는 트랙잭션 처리가 되어 있는 메서드를 테스트하는 예제 
 */
public class TestTransaction2 {
	public static void main(String[] args) {
		CardDAO dao=new CardDAO();
		try {
			//dao.registerCardAndPointVer2("java", "아이유", "cgv", 10000);
			//아래 코드는 고의로 문제를 발생시켜 테스트해본다 
			//point 발급( insert 시 ) 시 Exception을 발생시키기 위해 
			//pointType 을 null로 전달한다 
			//아래 코드를 실행하면 db card table에는 박보검 카드가 insert가
			//되고 point 는 등록 insert 가 되지 않는 상태가 되지 않고
			//트랜잭션 처리를 통해 rollback 되어 card 및 point table에 
			//작업 내용이 반영되지 않고 원상태로 되돌아간다 
			dao.registerCardAndPointVer2("spring", "박보검",null, 10000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

















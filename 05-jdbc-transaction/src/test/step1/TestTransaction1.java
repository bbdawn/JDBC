package test.step1;

import java.sql.SQLException;

import model.CardDAO;

/*
 *  트랜잭션 처리가 필요한 상황을 확인하는 예제 
 *  트랜잭션 처리를 하지 않았을 때 발생할 수 있는 문제를 테스트 해본다 
 *  카드 발급 작업단위 ( 카드발급 + 포인트발급 )에서 
 *  카드 발급은 정상적으로 되고 , 포인트 발급시 장애가 발생할 경우 
 *  카드 db에 카드정보는 insert가 되고, 포인트 정보는 존재하지 않는 상태가 된다 -> 고객과의 약속 위반 
 */
public class TestTransaction1 {
	public static void main(String[] args) {
		CardDAO dao=new CardDAO();
		try {
			//dao.registerCardAndPointVer1("java", "아이유", "cgv", 10000);
			//아래 코드는 고의로 문제를 발생시켜 테스트해본다 
			//point 발급( insert 시 ) 시 Exception을 발생시키기 위해 
			//pointType 을 null로 전달한다 
			//아래 코드를 실행하면 db card table에는 박보검 카드가 insert가
			//되고 point 는 등록 insert 가 되지 않는 상태가 된다 -> 무결성 위배
			dao.registerCardAndPointVer1("spring", "박보검",null, 10000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

















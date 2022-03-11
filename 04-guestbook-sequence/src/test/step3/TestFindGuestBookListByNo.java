package test.step3;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestFindGuestBookListByNo {
	public static void main(String[] args) {
		GuestBookDAO dao=new GuestBookDAO();
		int startNo=2;
		int endNo=4;
		// startNo 글번호 이상  endNo 글번호 이하의 글목록을 반환받는다 
		// 출력결과는 글번호(guestbook_no) 내림차순으로 출력되도록 한다 		
		try {
			ArrayList<GuestBookVO> list
			=dao.findGuestBookListByNo(startNo,endNo);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}







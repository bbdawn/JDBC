package test.step2;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestGetAllGuestBookList {
	public static void main(String[] args) {
		GuestBookDAO dao=new GuestBookDAO();		
		try {
			ArrayList<GuestBookVO> list = dao.getAllGuestBookList();
			// guestbook_no 내림차순으로 출력된다  
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}














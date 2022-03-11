package test.step1;

import java.sql.SQLException;
import java.util.Scanner;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestRegister {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.print("글제목:");
		String title=s.nextLine();
		System.out.print("본문내용:");
		String content=s.nextLine();
		//System.out.println(title+" "+content);		
		s.close();
		
		GuestBookDAO dao=new GuestBookDAO();
		try {
			dao.register(new GuestBookVO(title,content));
			System.out.println(title+" 방명록 글 등록완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}




















package Test.step2;

import java.sql.SQLException;
import java.util.Scanner;

import model.ProductDAO;
import model.ProductVO;

public class TestFindProductById {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.print("조회할 상품의 아이디: ");
		int id=scanner.nextInt();
		scanner.close();
		try {
			ProductDAO dao=new ProductDAO();
			ProductVO vo=dao.findProductById(id);
			if(vo!=null)
				System.out.println("상품정보:"+vo);
			else
				System.out.println(id+"아이디에 대한 상품정보없음");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

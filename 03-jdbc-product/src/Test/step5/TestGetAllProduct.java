//상품 목록 반환받기
package Test.step5;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductDAO;
import model.ProductVO;

public class TestGetAllProduct {
	public static void main(String[] args) {
		try {
			ProductDAO dao=new ProductDAO();
			//전체 상품 리스트를 반환(아이디 내림차순 정렬)
			ArrayList<ProductVO> list=dao.getAllProductList();
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

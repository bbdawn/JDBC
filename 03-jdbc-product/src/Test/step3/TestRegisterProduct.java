package Test.step3;

import java.sql.SQLException;

import model.ProductDAO;
import model.ProductVO;

public class TestRegisterProduct {
	public static void main(String[] args) {
		try {
			ProductDAO dao=new ProductDAO();
			//등록할 상품 정보
			ProductVO productVO=new ProductVO(5,"갤럭시","삼성",1700);
			if(dao.findProductById(productVO.getId())==null) {
				dao.registerProduct(productVO);
				System.out.println("상품등록완료 "+productVO);
			}else {
				System.out.println(productVO.getId()+"해당 아이디 상품 존재하므로 등록불가함");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package Test.step4;

import java.sql.SQLException;

import model.ProductDAO;

public class TestDeleteProduct {
	public static void main(String[] args) {
		try {
			ProductDAO dao=new ProductDAO();
			int id=6;//삭제할 상품의 아이디
			if(dao.findProductById(id)==null) {
				System.out.println(id+" 아이디에 대한 상품이 없어서 삭제 불가함");
			}else {//상품이 존재하면
				dao.deleteProductById(id);
				System.out.println(id+"아이디 상품 삭제완료");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

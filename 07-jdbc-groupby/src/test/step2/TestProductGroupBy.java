package test.step2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.ProductDAO;

public class TestProductGroupBy {
	public static void main(String[] args) {
		ProductDAO dao=new ProductDAO();
		/*
		 * 전체 product의 평균가보다 maker그룹별 평균가가 낮은 그룹의 maker명과 상품평균가를 내림차순으로 조회
		 */
		ArrayList<HashMap<String, Object>> list;
		try {
			list = dao.findProductMakerGroupLessThanAvgPrice();
			for(int i=0; i<list.size();i++) {
				HashMap<String,Object> map=list.get(i);
				System.out.println(map.get("maker")+" "+map.get("avgprice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

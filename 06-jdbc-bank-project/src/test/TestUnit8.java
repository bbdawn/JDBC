package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AccountDAO;
import model.AccountVO;

//step8 최고 잔액을 가진 계좌 리스트를 조회 ( subquery ) 
public class TestUnit8 {
	public static void main(String[] args) {
		
		try {
			AccountDAO dao=new AccountDAO();
			ArrayList<AccountVO> list=dao.findHighestBalanceAccount();
			for(int i=0; i<list.size();i++)
				System.out.println(list.get(i).getAccountNo()+" "+list.get(i).getName()+" "+list.get(i).getBalance());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

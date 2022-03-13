package model;
/*
 * 계좌 개설시 발생할 수 있는 Exception
 * 초기 납입액이 1000원 미만일 경우 발생된다 
 */
public class CreateAccountException extends Exception {	
	private static final long serialVersionUID = 491332526990042396L;

	public CreateAccountException(String message) {
		super(message);
	}
}

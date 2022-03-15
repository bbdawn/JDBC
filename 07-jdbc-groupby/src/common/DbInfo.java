package common;

public interface DbInfo {
	//public static final 자동 인식 
	String DRIVER="oracle.jdbc.OracleDriver";
	String URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String USER="scott";
	String PASS="tiger";
}

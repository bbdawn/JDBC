package common;

public interface DbInfo {
	//interface -> public static final로 자동 인식됨
	String DRIVER="oracle.jdbc.OracleDriver";
	String URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String USER="scott";
	String PASS="tiger";
}

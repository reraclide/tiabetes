package tiabetes.modelo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilMSQL {
	
	private static final String URL = "jdbc:mysql://localhost:3306/TIABETES?autoReconnect=true";
	private static final String USER = "root";
	private static final String PASS = "aluno";
	private static final String DRIVER ="com.mysql.jdbc.Driver";
	
	
	private static UtilMSQL utilMSQL = null;
	
	private Connection conn = null;
	
	private UtilMSQL() {
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
		
	public static UtilMSQL getInstance() { 
		
		if (utilMSQL == null) {
			utilMSQL = new UtilMSQL();
		}
		
		return utilMSQL;	
		
	}
	
	public Connection getConn() { 
		return conn;
	}
	
}

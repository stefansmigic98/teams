package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionProvider {
	public static Connection conn = null;
	static {
		try {
			Class.forName(IProvider.Provider);
			conn = DriverManager.getConnection(IProvider.Connection_url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getconnnConnection()
	{
		return conn;
	}
}

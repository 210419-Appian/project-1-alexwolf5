import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
	public static Connection getConnection() throws SQLException {
		//We need to register our driver.  This process makes the application aware of what particular driver class we are using
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String url = "jdbcpostgresql://battlements.crmiglwomb0b.us-west-1.rds.amazonaws.com:5432/demos";
		String username = "postgres";	//you can use environment variables to hide the raw values to protect this
		String password = "password";	//System.getenv("keyName")
		
		return DriverManager.getConnection(url, username, password);
	}	
}



import java.sql.Connection;
import java.sql.SQLException;

import ConnectionUtility.java;

public class test {
	public static void main(String[] args) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Connection successful!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

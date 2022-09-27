package libraryapp.dao.dbutil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	// This class should not be instatiated
	private DBUtil() {}
	
	
	private static Connection connection;
	
	public static Connection openConnection() throws SQLException {
		
			try {
				//Loading Driver for MySQL connection
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				// Get database url and connection credentials
				String url = "jdbc:mysql://localhost:3306/cf_java_final_project?serverTimezone=UTC";
				String username = "Antonis";
				String password = "Kwdikos123@";
				
				
				// Make the connection
				connection = DriverManager.getConnection(url, username, password);
				return connection;
			} catch (ClassNotFoundException | NullPointerException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	
	/**
	 * Closes the connection to the MySQL database
	 * @throws SQLException
	 */
	public static void closeConnection() throws SQLException {
		connection.close();
	}
}

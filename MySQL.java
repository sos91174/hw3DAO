package hw3DAO;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQL {
	
	private static MySQL instance =null;
	private MySQL() {}
	
	public static MySQL getInstance() {
		if(instance ==null) {
			instance = new MySQL();
		}
		return instance;
	}

	public static Connection getConnection() {
		Connection connect = null;
		try {
			// load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://192.168.56.129/hw", "justin", "506234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}


}

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
public static Connection connection;

public static Connection getConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/filehider?useSSL=false","root","Password your sql");
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("connection created.............");
	return connection;
	
}
    public static void closeConnection() {
    	if(connection!=null) {
    		try {
				connection.close();
			} catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
    	}
    }
   
}

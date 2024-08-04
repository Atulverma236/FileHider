package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDao{
	public static boolean isExists (String email) throws SQLException {
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps=connection.prepareStatement("select email from user");
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			String e= rs.getString(1);
			if(e.equals(email))
				return true;
		}
		return false;
	}
	public static int saveUser(User user) throws SQLException{
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps= connection.prepareStatement("insert into user values(default,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		return ps.executeUpdate();
	}

}

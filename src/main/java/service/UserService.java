package service;

import java.sql.SQLException;

import dao.UserDao;
import model.User;

public class UserService {
	public static Integer saveUser(User user) {
		try {
			if (UserDao.isExists(user.getEmail())) {
				return 0;
				
			}else {
				return UserDao.saveUser(user);
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return null;
	}

}

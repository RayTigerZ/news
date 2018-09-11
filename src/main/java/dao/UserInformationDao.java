package dao;

import java.sql.SQLException;

import bean.UserInformation;

public class UserInformationDao {

	public UserInformationDao() {
		// TODO 自动生成的构造函数存根
	}

	// 获取用户信息
	public UserInformation getInformationByUserId(Integer userId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		UserInformation userInformation = null;
		String sql = "SELECT * FROM user_information WHERE userId=" + userId;

		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			userInformation = new UserInformation();
			userInformation.setUserId(dataBaseDao.getInt("userId"));
			userInformation.setSex(dataBaseDao.getString("sex"));
			userInformation.setHobby(dataBaseDao.getString("hobby"));
		}

		return userInformation;
	}

	// 更新用户信息
	public Integer update(UserInformation userInformation)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "UPDATE user_information SET sex='" + userInformation.getSex() + "',hobby='"
				+ userInformation.getHobby() + "' WHERE userId=" + userInformation.getUserId();

		return dataBaseDao.update(sql);

	}

	// 插入新的用户信息
	public Integer insert(UserInformation userinformation)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "INSERT INTO user_information(userId,sex,hobby) VALUES(" + userinformation.getUserId() + ",'"
				+ userinformation.getSex() + "','" + userinformation.getHobby() + "')";
		return dataBaseDao.update(sql);
	}

	// 查看是否有该id用户的信息
	public boolean hasUserId(Integer userId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "SELECT * FROM user_information WHERE userId=" + userId;
		dataBaseDao.query(sql);
		while (dataBaseDao.next()) {
			return true;
		}
		return false;
	}

}

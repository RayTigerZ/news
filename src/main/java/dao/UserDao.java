package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.User;
import tool.PageInformation;
import tool.Tool;

public class UserDao {

	// 查询数据库是否有该用户
	// 返回true表示有该用户，false表示没有
	public Boolean hasUser(User user)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "SELECT * FROM user WHERE userName='" + user.getUserName() + "' AND userId=" + user.getUserId();
		dataBaseDao.query(sql);
		while (dataBaseDao.next()) {
			return true;
		}
		return false;
	}

	// 查询user表是否有与userName相同用户名的用户记录，
	// 返回false表示有相同的，true表示没有相同的
	public Boolean NotSameName(String userName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "SELECT * FROM user WHERE " + "userName='" + userName + "' ";
		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			return false;
		}
		return true;

	}

	// 用户登录
	public Integer login(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();
		String sqlString = "SELECT * FROM user WHERE " + "userName='" + user.getUserName() + "' AND password='"
				+ user.getPassword() + "'";
		dataBaseDao.query(sqlString);

		while (dataBaseDao.next()) {
			String enable = dataBaseDao.getString("enable");
			if (("use").equals(enable)) {
				user.setUserType(dataBaseDao.getString("userType"));
				user.setEnable(enable);
				user.setUserId(dataBaseDao.getInt("userId"));
				user.setHeadIconUrl(dataBaseDao.getString("headIconUrl"));
				user.setRegisterDate(dataBaseDao.getTimestamp("registerDate"));

				return 1;// 可以登录
			}
			return 0;// 用户存在，但被停用
		}
		return -1;// 该用户不存在或密码错误

	}

	// 用户登录
	// 返回1表示user类的普通用户注册成功
	// 返回0表示注册manager和newsAuthor的请求提交成功，等待管理员审核
	// 返回-1表示数据库中有相同userName的用户记录，注册失败
	// 返回-2表示数据库异常
	public Integer registe(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DataBaseDao dataBaseDao = new DataBaseDao();

		if (NotSameName(user.getUserName()) == false)
			return -1;
		else {
			if ("user".equals(user.getUserType())) {// user用户直接注册

				String sql = "INSERT INTO user(userType,userName,password) VALUES('" + user.getUserType() + "','"
						+ user.getUserName() + "','" + user.getPassword() + "')";
				if (dataBaseDao.update(sql) == 1)
					return 1;
				else
					return -2;

			} else {
				// 其他类型用户需要管理员审核，先将数据放在user_registing
				String sql = "INSERT INTO user_registing VALUES('" + user.getUserType() + "','" + user.getUserName()
						+ "','" + user.getPassword() + "')";
				if (dataBaseDao.update(sql) == 1)
					return 0;
				else
					return -2;

			}

		}

	}

	// 获取某一页的用户信息（页面信息由pageInformation决定）
	public ArrayList<User> getOnePage(PageInformation pageInformation)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();
		ArrayList<User> users = new ArrayList<User>();
		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = 0;

		allRecordCount = dataBaseDao.getCount(sqlCount);
		// 符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);// 更新pageInformation的总页数等

		String sqlSelect = Tool.getSql(pageInformation, "select");

		dataBaseDao.query(sqlSelect);

		while (dataBaseDao.next()) {
			User user = new User();
			user.setEnable(dataBaseDao.getString("enable"));
			user.setUserName(dataBaseDao.getString("userName"));
			user.setRegisterDate(dataBaseDao.getTimestamp("registerDate"));
			user.setUserType(dataBaseDao.getString("userType"));
			user.setUserId(dataBaseDao.getInt("userId"));
			users.add(user);
		}

		return users;
	}

	// 切换用户的可用性
	public Integer changeEnable(String id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {// 查询失败返回-1

		DataBaseDao dataBaseDao = null;

		dataBaseDao = new DataBaseDao();

		String sql = "SELECT * FROM user WHERE userId IN (" + id + ")";

		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			String enable = dataBaseDao.getString("enable");
			if ("use".equals(enable))
				enable = "stop";
			else
				enable = "use";
			sql = "UPDATE user SET enable='" + enable + "' WHERE userId IN (" + id + ")";
			return dataBaseDao.update(sql);

		}

		return -1;
	}

	// 删除多个用户
	public Integer deletes(String ids)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {// 查询失败返回-1

		DataBaseDao dataBaseDao = new DataBaseDao();

		if (ids != null && ids.length() > 0) {
			String sql = "DELETE FROM user WHERE userId IN (" + ids + ")";

			return dataBaseDao.update(sql);

		}
		return -1;
	}

	// 更新用户头像
	public Integer updateHeadIcon(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {//
		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "UPDATE user SET headIconUrl='" + user.getHeadIconUrl() + "' WHERE userId =" + user.getUserId();

		return dataBaseDao.update(sql);

	}

}

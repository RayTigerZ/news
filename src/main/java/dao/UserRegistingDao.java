package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.UserRegisting;
import tool.PageInformation;
import tool.Tool;

public class UserRegistingDao {

	public UserRegistingDao() {
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<UserRegisting> getOnePage(PageInformation pageInformation)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		ArrayList<UserRegisting> userRegistings = new ArrayList<UserRegisting>();

		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = dataBaseDao.getCount(sqlCount);// 符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);// 更新pageInformation的总页数等

		String sqlSelect = Tool.getSql(pageInformation, "select");
		dataBaseDao.query(sqlSelect);
		while (dataBaseDao.next()) {
			UserRegisting userRegisting = new UserRegisting();

			userRegisting.setId(dataBaseDao.getInt("id"));
			userRegisting.setUserType(dataBaseDao.getString("userType"));
			userRegisting.setUserName(dataBaseDao.getString("userName"));
			userRegisting.setPassword(dataBaseDao.getString("password"));

			userRegistings.add(userRegisting);
		}
		return userRegistings;
	}

	//
	public Integer deletes(String ids)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {// 查询失败返回-1

		DataBaseDao dataBaseDao = new DataBaseDao();

		if (ids != null && ids.length() > 0) {
			String sql = "DELETE FROM user_registing WHERE id IN (" + ids + ")";
			return dataBaseDao.update(sql);
		} else
			return -1;
	}

	// 非普通用户的注册请求通过，先将user_registing表对应ids的信息添加user表内，再将user_registing表内相应的信息删除
	// -1表示操作失败，大于0表示成功
	public Integer checked(String ids)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {// 查询失败返回-1

		DataBaseDao dataBaseDao = new DataBaseDao();

		if (ids != null && ids.length() > 0) {
			String sql = "INSERT INTO user(userType,userName,password) SELECT * FROM user_registing WHERE id IN ( "
					+ ids + ")";
			if (dataBaseDao.update(sql) > 0)
				return deletes(ids);
			else
				return -1;
		}
		return -1;

	}

}

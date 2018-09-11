package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Authority;

public class AuthorityDao {

	public ArrayList<Authority> getAll() throws SQLException, Exception {
		ArrayList<Authority> authorities = new ArrayList<Authority>();
		DataBaseDao dataBaseDao = new DataBaseDao();
		dataBaseDao.query("SELECT * FROM authority");
		while (dataBaseDao.next()) {
			Authority authority = new Authority();
			authority.setAuthorityId(dataBaseDao.getInt("authorityId"));
			authority.setUserType(dataBaseDao.getString("userType"));
			authority.setUrl(dataBaseDao.getString("url"));
			authority.setRedirectUrl(dataBaseDao.getString("redirectUrl"));
			authority.setParam(dataBaseDao.getString("param"));
			authorities.add(authority);
		}
		return authorities;
	}

}

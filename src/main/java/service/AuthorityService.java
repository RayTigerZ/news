package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Authority;
import dao.AuthorityDao;

public class AuthorityService {

	public ArrayList<Authority> getAll() {
		try {
			return (ArrayList<Authority>) (new AuthorityDao()).getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

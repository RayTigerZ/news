package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import dao.DataBaseDao;
import dao.UserDao;
import tool.PageInformation;

public class UserDaoTest {
	private static User user;

	@BeforeClass
	public static void set() {
		// TODO 自动生成的方法存根

		DataBaseDao.setDrive("com.mysql.cj.jdbc.Driver");
		DataBaseDao.setPassword("1756332466");
		DataBaseDao.setUserName("root");
		DataBaseDao
				.setUrl("jdbc:mysql://localhost:3306/newsdb?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false");

		user = new User();

		user.setUserId(2);
		user.setUserType("user");
		user.setUserName("tom");
		user.setPassword("12345");
		user.setHeadIconUrl("/news/headIcon/default.jpg");

	}

	@Test
	public void testHasUser() {
		UserDao userDao = new UserDao();
		Boolean result = false;
		try {
			result = userDao.hasUser(user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			result = false;
		}

		assertTrue(result);
	}

	@Test
	public void testNotSameName() {
		UserDao userDao = new UserDao();
		boolean result = true;
		try {
			result = userDao.NotSameName(user.getUserName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertFalse(result);

	}

	@Test
	public void testLogin() {
		UserDao userDao = new UserDao();
		Integer result = 0;
		try {
			result = userDao.login(user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(1, (int) result);
	}

	@Test
	public void testRegiste() {
		UserDao userDao = new UserDao();
		Integer result = 0;
		try {
			result = userDao.registe(user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(-1, (int) result);
	}

	@Test
	public void testGetOnePage() {
		UserDao userDao = new UserDao();

		PageInformation pageInformation = new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");

		ArrayList<User> users = new ArrayList<User>();
		try {
			users = userDao.getOnePage(pageInformation);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		for (User user : users) {
			System.out.println(user.getUserId());
			System.out.println(user.getUserName());
			System.out.println(user.getUserType());
			System.out.println(user.getRegisterDate());
			System.out.println(user.getEnable());

		}

	}

	@Test
	public void testChangeEnable() {
		UserDao userDao = new UserDao();
		Integer result = 0;

		try {
			result = userDao.changeEnable("3");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		assertEquals(1, (int) result);
	}

	@Test
	public void testDeletes() {
		UserDao userDao = new UserDao();
		Integer result = 0;
		try {
			result = userDao.deletes("4");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(0, (int) result);
	}

	@Test
	public void testUpdateHeadIcon() {
		UserDao userDao = new UserDao();
		Integer result = 0;
		try {
			result = userDao.updateHeadIcon(user);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(result);
		assertEquals(1, (int) result);
	}

}

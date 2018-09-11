package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import bean.UserInformation;
import dao.DataBaseDao;
import dao.UserInformationDao;

public class UserInformationDaoTest {

	private static UserInformation userInformation = new UserInformation();

	@BeforeClass
	public static void set() {
		DataBaseDao.setDrive("com.mysql.cj.jdbc.Driver");
		DataBaseDao.setPassword("1756332466");
		DataBaseDao.setUserName("root");
		DataBaseDao
				.setUrl("jdbc:mysql://localhost:3306/newsdb?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false");

		userInformation.setUserId(2);
		userInformation.setSex("女");
		userInformation.setHobby("看电视剧");
	}

	@Test
	public void testGetInformationByUserId() {
		UserInformationDao userInformationDao = new UserInformationDao();
		UserInformation userInformation1 = new UserInformation();
		try {
			userInformation1 = userInformationDao.getInformationByUserId(2);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(userInformation.getUserId(), userInformation1.getUserId());
		assertEquals(userInformation.getSex(), userInformation1.getSex());
		assertEquals(userInformation.getHobby(), userInformation1.getHobby());
	}

	@Test
	public void testUpdate() {
		UserInformationDao userInformationDao = new UserInformationDao();
		Integer result = 0;
		try {
			result = userInformationDao.update(userInformation);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(1, (int) result);
	}

	@Test
	public void testInsert() {
		UserInformationDao userInformationDao = new UserInformationDao();
		Integer result = 0;
		try {
			result = userInformationDao.insert(userInformation);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertEquals(0, (int) result);
	}

	@Test
	public void testHasUserId() {
		UserInformationDao userInformationDao = new UserInformationDao();
		Boolean result = false;
		try {
			result = userInformationDao.hasUserId(userInformation.getUserId());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		assertTrue(result);
	}

}

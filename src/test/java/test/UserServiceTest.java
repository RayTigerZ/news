package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import bean.UserInformation;
import dao.DataBaseDao;
import service.UserService;
import tool.PageInformation;

public class UserServiceTest {

	private static UserInformation userInformation = new UserInformation();
	private static User user = new User();

	@BeforeClass
	public static void set() {
		DataBaseDao.setDrive("com.mysql.cj.jdbc.Driver");
		DataBaseDao.setPassword("1756332466");
		DataBaseDao.setUserName("root");
		DataBaseDao
				.setUrl("jdbc:mysql://localhost:3306/newsdb?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false");

		user.setUserId(2);
		user.setUserType("user");
		user.setUserName("tom");
		user.setPassword("12345");
		user.setHeadIconUrl("/news/headIcon/default.jpg");

		userInformation.setUserId(2);
		userInformation.setSex("女");
		userInformation.setHobby("看电视剧");
	}

	@Test
	public void testLogin() {
		UserService userService = new UserService();
		Integer result = -2;
		result = userService.login(user);
		assertEquals(1, (int) result);
	}

	@Test
	public void testRegiste() {
		UserService userService = new UserService();
		Integer result = -2;
		result = userService.registe(user);
		assertEquals(-1, (int) result);
	}

	@Test
	public void testGetOnePage() {
		UserService userService = new UserService();
		ArrayList<User> users = new ArrayList<User>();

		PageInformation pageInformation = new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");

		users = userService.getOnePage(pageInformation);

		for (User user : users) {
			System.out.println(user.getUserId());
			System.out.println(user.getUserName());
			System.out.println(user.getUserType());
			System.out.println(user.getRegisterDate());
			System.out.println(user.getEnable());

		}
	}

	@Test
	public void testCheck() {
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		pageInformation.setIds("3");

		UserService userService = new UserService();
		ArrayList<User> users = userService.check(pageInformation);
		assertNull(users);

	}

	@Test
	public void testDeletes() {
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		pageInformation.setTableName("user");
		pageInformation.setIds("5");

		UserService userService = new UserService();
		ArrayList<User> users = userService.deletes(pageInformation);
		assertEquals(2, users.size());

	}

	@Test
	public void testChangePassword() {
		UserService userService = new UserService();
		Integer result = userService.changePassword(user, "12345");
		assertEquals(1, (int) result);
	}

	@Test
	public void testGetInformationByUserId() {

		UserService userService = new UserService();
		UserInformation userInformation1 = userService.getInformationByUserId(user.getUserId());

		assertEquals(userInformation.getUserId(), userInformation1.getUserId());
		assertEquals(userInformation.getSex(), userInformation1.getSex());
		assertEquals(userInformation.getHobby(), userInformation1.getHobby());

	}

	@Test
	public void testUpdateUserInformation() {

	}

}

package sevlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import bean.UserInformation;
import service.UserService;
import tool.Message;
import tool.PageInformation;
import tool.SearchTool;
import tool.Tool;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/servlet/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		if (type.equals("login"))
			userLogin(request, response);
		else if (type.equals("registe"))
			userRegiste(request, response);
		else if (type.equals("exit")) {
			userExit(request, response);
		} else if (type.equals("show"))
			userShow(request, response);
		else if (type.equals("search"))
			userDelete(request, response);
		else if (type.equals("check"))
			userCheck(request, response);
		else if (type.equals("delete"))
			userDelete(request, response);
		else if (type.equals("registeCheck"))
			userCheck(request, response);
		else if (type.equals("changePassword"))
			changePassword(request, response);
		else if (type.equals("showUserInformation"))
			showUserInformation(request, response);
		else if (type.equals("changeUserInformation1"))
			changeUserInformation1(request, response);
		else if (type.equals("changeUserInformation2"))
			changeUserInformation2(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 用户登录
		Message message = new Message();
		User user = new User();
		UserService userService = new UserService();

		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		int result = userService.login(user);
		message.setResult(result);
		if (result == 1) {// 登录成功
			request.getSession().setAttribute("user", user);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {// 登录失败
			if (result == 0) {
				message.setMessage("用户存在，但已被停用，请联系管理员！");
				message.setRedirectUrl("/news/login.jsp");
			} else if (result == -1) {
				message.setMessage("用户不存在，或者密码错误，请重新登录！");
				message.setRedirectUrl("/news/login.jsp");
			} else if (result == -2) {
				message.setMessage("出现其它错误，请重新登录！");
				message.setRedirectUrl("/news/login.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		}

	}

	public void userRegiste(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 用户注册

		Message message = new Message();
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setUserType(request.getParameter("userType"));
		UserService userService = new UserService();

		int result = userService.registe(user);
		message.setResult(result);

		if (result == 1) {
			message.setMessage("注册成功！");
			message.setRedirectUrl("/news/login.jsp");

		} else if (result == 0) {
			message.setMessage("用户注册提交成功，请联系管理员尽快审核！");
			message.setRedirectUrl("/news/login.jsp");
		} else if (result == -1) {
			message.setMessage("用户名已存在，请更换用户名注册！");
			message.setRedirectUrl("/news/registe.jsp");
		} else if (result == -2) {
			message.setMessage("出现其它错误，请重新注册！");
			message.setRedirectUrl("/news/registe.jsp");
		}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

	}

	protected void userExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("/news/index.jsp");
	}

	public void userShow(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();

		Tool.getPageInformation("user", request, pageInformation);
		ArrayList<User> users = userService.getOnePage(pageInformation);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/manager/showUser.jsp").forward(request, response);
	}

	public void userCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Message message = new Message();
		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();

		Tool.getPageInformation("user", request, pageInformation);

		ArrayList<User> users = userService.check(pageInformation);
		if (users == null) {
			message.setMessage("切换可用性成功！");
			message.setRedirectUrl("/news/servlet/UserServlet?type=check&page=1&pageSize=2");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

		} else {
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/manager/checkUser.jsp").forward(request, response);
		}
	}

	public void userSeach(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = new UserService();
		PageInformation pageInformation = new PageInformation();

		Tool.getPageInformation("user", request, pageInformation);
		pageInformation.setSearchSql(SearchTool.user(request));
		ArrayList<User> users = userService.getOnePage(pageInformation);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/manager/showUser.jsp").forward(request, response);

	}

	public void userDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageInformation pageInformation = new PageInformation();
		UserService userService = new UserService();

		Tool.getPageInformation("user", request, pageInformation);
		pageInformation.setSearchSql(" (userType='user' or userType='newsAuthor')");
		ArrayList<User> users = userService.deletes(pageInformation);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/manager/deleteUser.jsp").forward(request, response);

	}

	public void registeCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = new UserService();
		Message message = new Message();

		String newPassword = request.getParameter("newPassword");
		User user = (User) request.getSession().getAttribute("user");

		Integer result = userService.changePassword(user, newPassword);

		message.setResult(result);
		if (result == 1) {
			message.setMessage("修改密码成功！");
		} else if (result == 0) {
			message.setMessage("修改密码失败，请联系管理员！");
		}
		message.setRedirectUrl("/news/login.jsp");
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

	}

	public void changeUserInformation1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getUserInformation(request, response);
		getServletContext().getRequestDispatcher("/all/changeUserInformation.jsp").forward(request, response);

	}

	public void changeUserInformation2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = new UserService();
		Message message = new Message();

		Integer result = userService.updateUserInformation(request);
		message.setResult(result);
		if (result == 1) {
			message.setMessage("修改个人信息成功！");
			message.setRedirectUrl("/news/servlet/UserServlet?type=showUserInformation");
		} else {
			message.setMessage("修改个人信息失败，请联系管理员！");
			message.setRedirectUrl("/news/servlet/UserServlet?type=showUserInformation");
		}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void showUserInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getUserInformation(request, response);

		getServletContext().getRequestDispatcher("/all/showUserInformation.jsp").forward(request, response);

	}

	public void getUserInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if ("user".equals(user.getUserType())) {
			UserService userService = new UserService();
			UserInformation userInformation = userService.getInformationByUserId(user.getUserId());
			request.setAttribute("userInformation", userInformation);
		}
	}

}

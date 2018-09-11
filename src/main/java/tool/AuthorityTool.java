package tool;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import bean.Authority;
import bean.User;

public class AuthorityTool {

	static public Hashtable<String, Authority> authorityMap = new Hashtable<String, Authority>();

	public static String getKey(HttpServletRequest request) {
		String originalUrl = request.getRequestURI();// 获取用户请求的原始网址
		String param = "";

		if (!originalUrl.endsWith("jsp")) {// 访问servlet
			param = request.getParameter("type");
			if (param == null) {
				param = request.getParameter("userType");
				if (param == null) {
					param = "";
				}
			}
		}
		String key = originalUrl + param;
		User user = (User) request.getSession().getAttribute("user");
		if (user == null)
			key += "anonymous";// 匿名用户：等价于未登录状态
		else
			key += user.getUserType();
		System.out.println(key);
		return key;
	}

}

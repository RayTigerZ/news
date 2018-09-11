package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import bean.Authority;
import tool.AuthorityTool;
import tool.Message;

public class AuthorityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		// HttpServletResponse res = (HttpServletResponse) response;

		String key = AuthorityTool.getKey(req);
		Authority authority = AuthorityTool.authorityMap.get(key);

		if (authority == null) {// 无权限
			Message message = new Message();
			message.setMessage("权限不够！");
			message.setRedirectTime(1000);
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		} else
			chain.doFilter(request, response);// 有权限，可以继续访问
	}

	public void destroy() {
		// TODO 自动生成的方法存根

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根

	}

}

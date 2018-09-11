package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD,
		DispatcherType.ERROR }, urlPatterns = { "/*" })
public class EncodeFilter implements Filter {

	public void destroy() {
		// TODO 自动生成的方法存根

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO 自动生成的方法存根
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0, arg1);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根

	}

}

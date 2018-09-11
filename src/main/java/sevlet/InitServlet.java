package sevlet;

import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import bean.Authority;
import bean.NewsType;
import dao.DataBaseDao;
import service.AuthorityService;
import service.NewsTypeService;
import tool.AuthorityTool;
import tool.FileTool;
import tool.WebProperties;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(urlPatterns = { "/servlet/InitServlet" }, loadOnStartup = 1)

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) {
		// TODO Auto-generated method stub
		try {
			super.init(config);
		} catch (ServletException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		// 初始化数据库参数
		DataBaseDao.setDrive(this.getServletContext().getInitParameter("drive"));
		DataBaseDao.setPassword(this.getServletContext().getInitParameter("password"));
		DataBaseDao.setUserName(this.getServletContext().getInitParameter("userName"));
		DataBaseDao.setUrl(this.getServletContext().getInitParameter("url"));

		ServletContext servletContext = config.getServletContext();
		FileTool.root = servletContext.getRealPath("/");

		// 读取属性文件
		String fileDir = servletContext.getRealPath("/WEB-INF/web.properties");
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class).configure(params.properties().setFileName(fileDir));
		try {

			WebProperties.config = builder.getConfiguration();
			WebProperties.config.addProperty("projectRoot",
					servletContext.getRealPath(WebProperties.config.getString("projectName")));

			// 加载新闻类型
			NewsTypeService newsTypeService = new NewsTypeService();
			ArrayList<NewsType> newsTypes = new ArrayList<NewsType>();
			newsTypes = newsTypeService.getAll();
			this.getServletContext().setAttribute("newsTypes", newsTypes);

			// 加载权限信息:利用哈希map存储权限，目的是方便查找权限
			AuthorityService authorityService = new AuthorityService();
			ArrayList<Authority> authorities = authorityService.getAll();
			for (Authority authority : authorities) {
				String key;
				if (authority.getParam() == null || authority.getParam().isEmpty())
					key = authority.getUrl() + authority.getUserType();
				else
					key = authority.getUrl() + authority.getParam() + authority.getUserType();

				AuthorityTool.authorityMap.put(key, authority);

			}
		} catch (ConfigurationException cex) {
			cex.printStackTrace();
		}

	}

}

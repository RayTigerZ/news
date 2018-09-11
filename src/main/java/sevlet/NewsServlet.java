package sevlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.News;
import bean.NewsType;
import service.NewsService;
import tool.Message;
import tool.PageInformation;
import tool.ServletTool;
import tool.Tool;
import tool.WebProperties;

@WebServlet("/servlet/NewsServlet")
public class NewsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4598485004162020105L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");

		if (type.equals("addNews")) {
			addNews(request, response);

		} else if (type.equals("showNewsList")) {
			showNewsList(request, response);

		} else if (type.equals("showNews")) {
			showNews(request, response);

		} else if (type.equals("deleteNews")) {
			deleteNews(request, response);

		} else if (type.equals("manageNews")) {// 显示编辑页面
			manageNews(request, response);

		} else if (type.equals("editNews")) {// 显示编辑页面
			editNews(request, response);

		} else if (type.equals("edit")) {// 修改新闻

			edit(request, response);
		} else if (type.equals("homepageTypes1")) {// 主页多个分类新闻区
			NewsService newsService = new NewsService();
			String newsTypesString = new String(WebProperties.config.getString("newsTypes").getBytes("ISO-8859-1"),
					"UTF-8");
			String[] newsTypes = newsTypesString.split(",");
			Integer homePageNewsN = Integer.parseInt(WebProperties.config.getString("homePageNewsN"));
			ArrayList<ArrayList<News>> newsesList = newsService.getByTypesTopN(newsTypes, homePageNewsN);
			request.setAttribute("newsTypes", newsTypes);
			request.setAttribute("newsesList", newsesList);
			request.setAttribute("homePageNewsCaptionMaxLength",
					Integer.parseInt(WebProperties.config.getString("homePageNewsCaptionMaxLength")));
			getServletContext().getRequestDispatcher("/index2.jsp").include(request, response);
			return;
		} else if (type.equals("homepageTypes")) {// 主页多个分类新闻区
			NewsService newsService = new NewsService();
			String newsTypesString = new String(WebProperties.config.getString("newsTypes").getBytes("ISO-8859-1"),
					"UTF-8");
			String[] newsTypes = newsTypesString.split(",");
			Integer homePageNewsN = Integer.parseInt(WebProperties.config.getString("homePageNewsN"));
			ArrayList<ArrayList<String>> newsCaptionsList = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<News>> newsesList = newsService.getByTypesTopN1(newsTypes, homePageNewsN,
					newsCaptionsList);
			int newsTypesNumber = newsTypes.length;
			request.setAttribute("newsTypes", newsTypes);
			request.setAttribute("newsTypesNumber", newsTypesNumber);
			request.setAttribute("newsesList", newsesList);
			request.setAttribute("newsCaptionsList", newsCaptionsList);
			getServletContext().getRequestDispatcher("/index2.jsp").include(request, response);
			return;
		} else if (type.equals("showNewsByNewsType")) {// 主页多个分类新闻区
			NewsService newsService = new NewsService();
			ArrayList<NewsType> newsTypes = (ArrayList<NewsType>) this.getServletContext().getAttribute("newsTypes");
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			String newsType = request.getParameter("newsType");

			if (!("all").equals(newsType))
				pageInformation.setSearchSql(" newsType='" + newsType + "' ");

			ArrayList<News> newss = newsService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newss);
			request.setAttribute("newsTypes", newsTypes);
			request.setAttribute("newsType", newsType);
			getServletContext().getRequestDispatcher("/news/showNewsByType.jsp").forward(request, response);
			return;
		}
	}

	public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NewsService newsService = new NewsService();
		Message message = new Message();

		News news = ServletTool.news(request);
		int result = newsService.add(news);
		message.setResult(result);
		if (result == 1) {
			message.setMessage("添加新闻成功！请添加新的新闻！");
			message.setRedirectUrl("/news/news/addNews.jsp");
		} else if (result == 0) {
			message.setMessage("添加新闻失败！请联系管理员！");
			message.setRedirectUrl("/news/index.jsp");
		}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void showNewsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsService newsService = new NewsService();
		// Message message = new Message();

		PageInformation pageInformation = new PageInformation();
		Tool.getPageInformation("news", request, pageInformation);
		ArrayList<News> newss = newsService.getOnePage(pageInformation);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("newses", newss);
		getServletContext().getRequestDispatcher("/news/showNewsList.jsp").forward(request, response);
	}

	public void showNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsService newsService = new NewsService();
		// Message message = new Message();

		Integer newsId = Integer.parseInt(request.getParameter("newsId"));
		News news = newsService.getNewsById(newsId);
		request.setAttribute("news", news);
		getServletContext().getRequestDispatcher("/news/showNews.jsp").forward(request, response);
	}

	public void manageNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsService newsService = new NewsService();
		// Message message = new Message();

		PageInformation pageInformation = new PageInformation();
		Tool.getPageInformation("news", request, pageInformation);
		ArrayList<News> newses = null;

		newses = newsService.getOnePage(pageInformation);

		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("newses", newses);
		getServletContext().getRequestDispatcher("/news/manageNews.jsp").forward(request, response);
	}

	public void editNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsService newsService = new NewsService();

		PageInformation pageInformation = new PageInformation();
		Tool.getPageInformation("news", request, pageInformation);
		Integer newsId = Integer.parseInt(pageInformation.getIds());
		News news = newsService.getNewsById(newsId);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("news", news);
		getServletContext().getRequestDispatcher("/news/editNews.jsp").forward(request, response);
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		NewsService newsService = new NewsService();
		Message message = new Message();

		News news = ServletTool.news(request);
		int result = newsService.update(news);
		message.setResult(result);
		if (result == 1) {
			message.setMessage("添加新闻成功！请添加新的新闻！");
		} else if (result == 0) {
			message.setMessage("添加新闻失败！请联系管理员！");
		}
		message.setRedirectTime(1000);
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void deleteNews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsService newsService = new NewsService();
		// Message message = new Message();

		PageInformation pageInformation = new PageInformation();
		Tool.getPageInformation("news", request, pageInformation);
		ArrayList<News> newses = null;

		newses = newsService.deletes(pageInformation);

		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("newses", newses);
		getServletContext().getRequestDispatcher("/news/manageNews.jsp").forward(request, response);
	}

}

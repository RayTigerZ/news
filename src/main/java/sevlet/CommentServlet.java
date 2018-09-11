package sevlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.CommentUserView;
import bean.User;
import service.CommentService;
import tool.PageInformation;
import tool.Tool;

@WebServlet("/servlet/CommentServlet")

public class CommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436866859094221418L;

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

		if (type.equals("showCommnet")) {
			showComment(request, response);
		} else if (type.equals("praise")) {// 点赞
			praise(request, response);
		} else if (type.equals("addCommnet")) {
			addComment(request, response);
		}
	}

	public void showComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		CommentService commentService = new CommentService();

		PageInformation pageInformation = new PageInformation();
		Tool.getPageInformation("commentuserview", request, pageInformation);
		pageInformation.setSearchSql(" (newsId=" + newsId + ") ");
		pageInformation.setOrder("desc");
		pageInformation.setOrderField("time");
		ArrayList<CommentUserView> commentUserViews = commentService.getOnePage(pageInformation);
		request.setAttribute("pageInformation", pageInformation);
		request.setAttribute("commentUserViews", commentUserViews);
		getServletContext().getRequestDispatcher("/comment/showComment.jsp").include(request, response);
	}

	public void praise(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		CommentService commentService = new CommentService();
		String commentId = request.getParameter("commentId");

		commentService.paise(Integer.parseInt(commentId));
		String url = "/servlet/NewsServlet?type=showNews&newsId=" + newsId + "&page" + request.getParameter("page")
				+ "&pageSize" + request.getParameter("pageSize") + "&totalPageCount"
				+ request.getParameter("totalPageCount");
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	public void addComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommentService commentService = new CommentService();
		String newsId = request.getParameter("newsId");

		Comment comment = new Comment();
		comment.setContent(request.getParameter("content"));
		comment.setNewsId(Integer.parseInt(newsId));
		User user = (User) request.getSession().getAttribute("user");
		comment.setUserId(user.getUserId());
		String commentId = request.getParameter("commentId");

		String url;

		if (commentId == null || commentId.isEmpty()) {
			commentService.addComment(comment);// 对新闻的回复
			url = "/servlet/NewsServlet?type=showNews&newsId=" + newsId + "&page=1&addCommnet=addCommnet";
		} else {
			comment.setCommentId(Integer.parseInt(commentId));
			commentService.addCommentToComment(comment);// 对回复的回复
			url = "/servlet/NewsServlet?type1=showNews&newsId=" + newsId + "&page=1";
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}

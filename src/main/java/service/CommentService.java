package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Comment;
import bean.CommentUserView;
import dao.CommentDao;
import tool.PageInformation;

public class CommentService {

	public CommentService() {
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<CommentUserView> getOnePage(PageInformation pageInformation) {
		ArrayList<CommentUserView> commentUserViews = new ArrayList<CommentUserView>();
		try {

			CommentDao commentDao = new CommentDao();
			commentUserViews = commentDao.getOnePage(pageInformation);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentUserViews;
	}

	// 点赞
	public Integer paise(Integer commentId) {
		try {
			CommentDao commentDao = new CommentDao();
			if (commentDao.paise(commentId) > 0)
				return 1;//
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;//
		} catch (Exception e) {
			e.printStackTrace();
			return -3;//
		}
	}

	// 对新闻的回复
	public Integer addComment(Comment comment) {
		CommentDao commentDao = new CommentDao();
		try {

			Integer stair = commentDao.getStairByNewsId(comment.getNewsId());
			comment.setStair(stair + 1);
			return commentDao.addComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}

	// 对回复的回复
	public Integer addCommentToComment(Comment comment) {
		CommentDao commentDao = new CommentDao();
		try {

			Comment oldComment = commentDao.getById(comment.getCommentId());
			String s = "回复第" + oldComment.getStair() + "楼层&nbsp;" + oldComment.getUserId() + "&nbsp;的留言：<br>";
			comment.setContent(s + comment.getContent());
			Integer stair = commentDao.getStairByNewsId(comment.getNewsId());
			comment.setStair(stair + 1);
			return commentDao.addComment(comment);

		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}

}

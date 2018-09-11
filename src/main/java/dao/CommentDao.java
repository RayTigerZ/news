package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Comment;
import bean.CommentUserView;
import tool.PageInformation;
import tool.Tool;

public class CommentDao {

	public CommentDao() {
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<CommentUserView> getOnePage(PageInformation pageInformation)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		ArrayList<CommentUserView> commentUserViews = new ArrayList<CommentUserView>();
		DataBaseDao dataBaseDao = new DataBaseDao();
		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = dataBaseDao.getCount(sqlCount);// 符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);// 更新pageInformation的总页数等

		String sqlSelect = Tool.getSql(pageInformation, "select");

		dataBaseDao.query(sqlSelect);
		while (dataBaseDao.next()) {
			CommentUserView commentUserView = new CommentUserView();
			commentUserView.setCommentId(dataBaseDao.getInt("commentId"));
			commentUserView.setContent(dataBaseDao.getString("content"));
			commentUserView.setNewsId(dataBaseDao.getInt("newsId"));
			commentUserView.setPraise(dataBaseDao.getInt("praise"));
			commentUserView.setStair(dataBaseDao.getInt("stair"));
			commentUserView.setTime(dataBaseDao.getTimestamp("time"));
			commentUserView.setUserName(dataBaseDao.getString("name"));
			commentUserView.setHeadIconUrl(dataBaseDao.getString("headIconUrl"));
			commentUserView.setUserId(dataBaseDao.getInt("userId"));
			commentUserViews.add(commentUserView);
		}
		return commentUserViews;
	}

	public Integer paise(Integer commentId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "UPDATE comment SET praise=praise+1 WHERE commentId=" + commentId;

		return dataBaseDao.update(sql);

	}

	public Integer getStairByNewsId(Integer newsId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "SELECT count(*) AS count FROM comment WHERE newsId=" + newsId;
		Integer stair = 0;

		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			stair = dataBaseDao.getInt("count");
		}

		return stair;
	}

	public Integer addComment(Comment comment)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "INSERT INTO comment(newsId,userId,content,stair) VALUES(" + comment.getNewsId() + ",'"
				+ comment.getUserId() + "','" + comment.getContent() + "', " + comment.getStair() + ")";

		return dataBaseDao.update(sql);

	}

	public Comment getById(Integer commentId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();
		Comment comment = null;
		String sql = "SELECT * FROM comment WHERE commentId=" + commentId;
		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			comment = new Comment();
			comment.setCommentId(dataBaseDao.getInt("commentId"));
			comment.setContent(dataBaseDao.getString("content"));
			comment.setNewsId(dataBaseDao.getInt("newsId"));
			comment.setPraise(dataBaseDao.getInt("praise"));
			comment.setStair(dataBaseDao.getInt("stair"));
			comment.setTime(dataBaseDao.getTimestamp("time"));
			comment.setUserId(dataBaseDao.getInt("userId"));

		}

		return comment;

	}

	public CommentUserView getByIdFromView(Integer commentId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DataBaseDao dataBaseDao = new DataBaseDao();
		String sql = "select * from commentUserView  where commentId=" + commentId.toString();
		dataBaseDao.query(sql);
		while (dataBaseDao.next()) {
			CommentUserView commentUserView = new CommentUserView();
			commentUserView.setCommentId(dataBaseDao.getInt("commentId"));
			commentUserView.setContent(dataBaseDao.getString("content"));
			commentUserView.setNewsId(dataBaseDao.getInt("newsId"));
			commentUserView.setPraise(dataBaseDao.getInt("praise"));
			commentUserView.setStair(dataBaseDao.getInt("stair"));
			commentUserView.setTime(dataBaseDao.getTimestamp("time"));
			commentUserView.setUserName(dataBaseDao.getString("name"));
			commentUserView.setHeadIconUrl(dataBaseDao.getString("headIconUrl"));
			commentUserView.setUserId(dataBaseDao.getInt("userId"));
			return commentUserView;
		}
		return null;
	}

}

package bean;

import java.sql.Timestamp;

//评论类
public class Comment {

	private Integer commentId;// 评论id
	private Integer newsId;// 被评论的新闻id
	private Integer userId;// 评论者（用户）id
	private String content;// 评论内容
	private Timestamp time;// 评论时间
	private Integer praise;// 点赞数
	private Integer stair;// 在评论区的楼层

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getStair() {
		return stair;
	}

	public void setStair(Integer stair) {
		this.stair = stair;
	}

}

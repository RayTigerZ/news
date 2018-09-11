package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//新闻类
public class News {

	private Integer newsId;// 新闻id，唯一
	private String newsType;// 新闻类型id
	private String author;// 作者
	private String caption;// 标题
	private String content;// 内容（正文）
	private LocalDateTime newsTime;// 新闻时间
	private Timestamp publishTime;// 出版时间
	private Boolean checked;// 是否通过审核，true表示通过，false表示未通过

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(LocalDateTime newsTime) {
		this.newsTime = newsTime;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

}

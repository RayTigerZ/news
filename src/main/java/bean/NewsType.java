package bean;

//新闻类型类
public class NewsType {

	private Integer newsTypeId;// 新闻类型的id，唯一
	private String newsTypeName;// 新闻类型名称，唯一

	public Integer getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(Integer newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public String getNewsTypeName() {
		return newsTypeName;
	}

	public void setNewsTypeName(String NewsTypeName) {
		this.newsTypeName = NewsTypeName;
	}

}

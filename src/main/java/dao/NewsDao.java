package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.News;
import tool.PageInformation;
import tool.Tool;

public class NewsDao {

	public NewsDao() {
		// TODO 自动生成的构造函数存根
	}

	public Integer add(News news)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql = "INSERT INTO news(caption,author,newsType,content,newsTime) VALUES(" + "'" + news.getCaption()
				+ "','" + news.getAuthor() + "','" + news.getNewsType() + "','" + news.getContent() + "','"
				+ news.getNewsTime() + "')";

		DataBaseDao dataBaseDao = new DataBaseDao();

		return dataBaseDao.update(sql);

	}

	public Integer update(News news)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		String sql = " update news set caption='" + news.getCaption() + "',author='" + news.getAuthor() + "',newsType='"
				+ news.getNewsType() + "',content='" + news.getContent() + "',newsTime='" + news.getNewsTime()
				+ "' where newsId=" + news.getNewsId().toString();

		DataBaseDao dataBaseDao = new DataBaseDao();

		return dataBaseDao.update(sql);
	}

	public Integer deletes(String ids)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		String sql = "DELETE FROM news WHERE newsId IN (" + ids + ")";

		return dataBaseDao.update(sql);

	}

	public ArrayList<News> getOnePage(PageInformation pageInformation)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataBaseDao dataBaseDao = new DataBaseDao();

		ArrayList<News> newses = new ArrayList<News>();
		String sqlCount = Tool.getSql(pageInformation, "count");
		Integer allRecordCount = dataBaseDao.getCount(sqlCount);// 符合条件的总记录数

		Tool.setPageInformation(allRecordCount, pageInformation);// 更新pageInformation的总页数等

		String sqlSelect = Tool.getSql(pageInformation, "select");
		dataBaseDao.query(sqlSelect);
		while (dataBaseDao.next()) {
			News news = new News();
			news.setNewsId(dataBaseDao.getInt("newsId"));
			news.setCaption(dataBaseDao.getString("caption"));
			news.setAuthor(dataBaseDao.getString("author"));
			news.setNewsType(dataBaseDao.getString("newsType"));
			news.setContent(dataBaseDao.getString("content"));
			news.setNewsTime(dataBaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(dataBaseDao.getTimestamp("publishTime"));
			newses.add(news);
		}
		return newses;
	}

	public News getById(Integer newsId) throws SQLException, Exception {

		DataBaseDao dataBaseDao = new DataBaseDao();
		News news = new News();
		String sql = "SELECT * FROM news WHERE newsId=" + newsId;
		dataBaseDao.query(sql);
		while (dataBaseDao.next()) {
			news.setNewsId(dataBaseDao.getInt("newsId"));
			news.setCaption(dataBaseDao.getString("caption"));
			news.setAuthor(dataBaseDao.getString("author"));
			news.setNewsType(dataBaseDao.getString("newsType"));
			news.setContent(dataBaseDao.getString("content"));
			news.setNewsTime(dataBaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(dataBaseDao.getTimestamp("publishTime"));

		}
		return news;
	}

	public ArrayList<News> getByTypesTopN(String type, Integer n) throws SQLException, Exception {
		DataBaseDao dataBaseDao = new DataBaseDao();
		ArrayList<News> newses = new ArrayList<News>();
		String sql;
		if (type.equals("all"))
			sql = "SELECT newsId,caption,author,newsType,newsTime,publishTime FROM news order by newsTime desc limit 0,"
					+ n.toString();
		else
			sql = "SELECT newsId,caption,author,newsType,newsTime,publishTime FROM news where newsType ='" + type
					+ "' order by newsTime desc limit 0," + n.toString();

		dataBaseDao.query(sql);
		while (dataBaseDao.next()) {
			News news = new News();
			news.setNewsId(dataBaseDao.getInt("newsId"));
			news.setCaption(dataBaseDao.getString("caption"));
			news.setAuthor(dataBaseDao.getString("author"));
			news.setNewsType(dataBaseDao.getString("newsType"));
			news.setNewsTime(dataBaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(dataBaseDao.getTimestamp("publishTime"));
			newses.add(news);
		}
		return newses;
	}

}

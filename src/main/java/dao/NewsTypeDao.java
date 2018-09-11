package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.NewsType;

public class NewsTypeDao {

	public NewsTypeDao() {
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<NewsType> getAllNewsType()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		ArrayList<NewsType> newsTypes = new ArrayList<NewsType>();
		String sql = "SELECT * FROM news_type";

		DataBaseDao dataBaseDao = new DataBaseDao();

		dataBaseDao.query(sql);

		while (dataBaseDao.next()) {
			NewsType newsType = new NewsType();
			newsType.setNewsTypeName(dataBaseDao.getString("newsTypeName"));
			newsType.setNewsTypeId(dataBaseDao.getInt("newsTypeId"));
			newsTypes.add(newsType);
		}

		return newsTypes;
	}

}

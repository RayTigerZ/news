package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.NewsType;
import dao.NewsTypeDao;

public class NewsTypeService {

	public NewsTypeService() {
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<NewsType> getAll() {

		ArrayList<NewsType> NewsTypes = null;
		NewsTypeDao newsTypeDao = new NewsTypeDao();

		try {
			NewsTypes = newsTypeDao.getAllNewsType();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			newsTypeDao = null;
			e.printStackTrace();

		}

		return NewsTypes;
	}

}

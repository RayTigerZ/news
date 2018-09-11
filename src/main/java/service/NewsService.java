package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.News;
import dao.NewsDao;
import tool.PageInformation;
import tool.Tool;
import tool.WebProperties;

public class NewsService {

	public NewsService() {
		// TODO 自动生成的构造函数存根
	}

	public Integer add(News news) {

		Integer result = -2;

		NewsDao newsDao = new NewsDao();

		try {
			return newsDao.add(news);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return result;

	}

	public ArrayList<News> getOnePage(PageInformation pageInformation) {
		ArrayList<News> newses = null;

		NewsDao newsDao = new NewsDao();

		try {
			newses = newsDao.getOnePage(pageInformation);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			newses = null;
			e.printStackTrace();
		}

		return newses;
	}

	public News getNewsById(Integer newsId) {
		NewsDao newsDao = new NewsDao();

		try {
			return newsDao.getById(newsId);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return null;
	}

	// 删除多条记录
	public ArrayList<News> deletes(PageInformation pageInformation) {
		ArrayList<News> newses = null;

		NewsDao newsDao = new NewsDao();

		try {
			newsDao.deletes(pageInformation.getIds());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO 自动生成的 catch 块

			e1.printStackTrace();
			return null;
		}

		pageInformation.setIds(null);

		try {
			newses = newsDao.getOnePage(pageInformation);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

		return newses;
	}

	public ArrayList<ArrayList<News>> getByTypesTopN(String[] newsTypes, Integer n) {
		ArrayList<ArrayList<News>> newsesList = new ArrayList<ArrayList<News>>();
		try {

			NewsDao newsDao = new NewsDao();
			for (String type : newsTypes) {
				ArrayList<News> newses = newsDao.getByTypesTopN(type, n);
				newsesList.add(newses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newsesList;
	}

	public ArrayList<ArrayList<News>> getByTypesTopN1(String[] newsTypes, Integer n,
			ArrayList<ArrayList<String>> newsCaptionsList) {
		ArrayList<ArrayList<News>> newsesList = new ArrayList<ArrayList<News>>();
		try {

			NewsDao newsDao = new NewsDao();
			for (String type : newsTypes) {
				ArrayList<News> newses = newsDao.getByTypesTopN(type, n);
				ArrayList<String> newsCaptions = new ArrayList<String>();
				for (News news : newses) {
					String newsCaption = Tool.getStringByMaxLength(news.getCaption(),
							Integer.parseInt(WebProperties.config.getString("homePageNewsCaptionMaxLength")));
					newsCaptions.add(newsCaption);
				}
				newsesList.add(newses);
				newsCaptionsList.add(newsCaptions);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newsesList;
	}

	public Integer update(News news) {
		try {

			NewsDao newsDao = new NewsDao();
			return newsDao.update(news);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}

}

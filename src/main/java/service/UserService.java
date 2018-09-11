package service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.User;
import bean.UserInformation;
import dao.DataBaseDao;
import dao.UserDao;
import dao.UserInformationDao;
import tool.FileTool;
import tool.PageInformation;
import tool.WebProperties;

public class UserService {

	public UserService() {
		// TODO 自动生成的构造函数存根
	}

	public Integer login(User user) {
		int result = -2; // 数据库操作失败
		try {
			UserDao UserDao = new UserDao();
			return UserDao.login(user);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}

	public Integer registe(User user) {
		int result = -2;

		try {
			UserDao UserDao = new UserDao();
			return UserDao.registe(user);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	public ArrayList<User> getOnePage(PageInformation pageInformation) {

		ArrayList<User> users = null;
		try {
			UserDao userDao = new UserDao();
			users = userDao.getOnePage(pageInformation);
		} catch (Exception e) {
			users = null;
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<User> check(PageInformation pageInformation) {
		ArrayList<User> users = null;
		String ids = pageInformation.getIds();

		try {

			UserDao userDao = new UserDao();

			if (ids != null && !ids.isEmpty())
				userDao.changeEnable(ids);
			else {

				users = userDao.getOnePage(pageInformation);
				pageInformation.setIds(null);
			}

		} catch (Exception e) {
			users = null;
			e.printStackTrace();
		}

		return users;
	}

	// 删除多条记录
	public ArrayList<User> deletes(PageInformation pageInformation) {
		ArrayList<User> users = null;
		try {
			UserDao userDao = new UserDao();
			userDao.deletes(pageInformation.getIds());
			pageInformation.setIds(null);
			users = userDao.getOnePage(pageInformation);

		} catch (Exception e) {
			users = null;
			e.printStackTrace();
		}
		return users;
	}

	// 审查除普通用户的注册记录
	public ArrayList<User> registeCheck(PageInformation pageInformation) {
		ArrayList<User> users = null;
		try {
			UserDao userDao = new UserDao();
			userDao.deletes(pageInformation.getIds());
			pageInformation.setIds(null);
			users = userDao.getOnePage(pageInformation);
		} catch (Exception e) {
			users = null;
			e.printStackTrace();
		}
		return users;
	}

	// 修改密码
	public Integer changePassword(User user, String newPassword) {
		try {

			UserDao userDao = new UserDao();
			if (userDao.hasUser(user)) {
				String sqlString = "UPDATE user SET password='" + newPassword + "' WHERE  " + "userId="
						+ user.getUserId();
				DataBaseDao dataBaseDao = new DataBaseDao();
				if (dataBaseDao.update(sqlString) > 0)
					return 1;// 修改成功
				else
					return 0;// 用户存在，但修改失败，可能是密码问题
			} else
				return -1;// 用户不存在
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;// 数据库问题
		} catch (Exception e) {
			e.printStackTrace();
			return -3;// 其它异常
		}
	}

	public UserInformation getInformationByUserId(Integer userId) {
		UserInformation userInformation = null;
		try {
			UserInformationDao userInformationDao = new UserInformationDao();
			userInformation = userInformationDao.getInformationByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userInformation = null;
			e.printStackTrace();
		}
		return userInformation;
	}

	public Integer updateUserInformation(HttpServletRequest request) {

		Integer result1, result2 = 1;
		User user = (User) request.getSession().getAttribute("user");
		UserInformation userInformation = new UserInformation();

		try {

			String oldHeadIconUrl = user.getHeadIconUrl();

			// 创建工厂模式
			DiskFileItemFactory factory = new DiskFileItemFactory();// **
			ServletFileUpload upload = new ServletFileUpload(factory);// **

			// 设置临时文件存放的文件夹
			String fullPath = request.getServletContext().getRealPath(WebProperties.config.getString("tempDir"));// 获取相对路径的绝对路径
			File repository = new File(fullPath);
			factory.setRepository(repository);

			// 解析request，将其中各表单元素和上传文件提取出来
			List<FileItem> items = upload.parseRequest(request);// items存放各表单元素

			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {// 遍历表单元素
				FileItem item = iter.next();

				if (item.isFormField()) {// 非上传文件表单元素

					if ("sex".equals(item.getFieldName()))// 获取表单元素的name属性
						userInformation.setSex(item.getString("UTF-8"));// 获取表单元素的值（一般是value属性）

					else if ("hobby".equals(item.getFieldName()))// 获取表单元素的name属性
						userInformation.setHobby(item.getString("UTF-8"));// 获取表单元素的值（一般是value属性）

				} else {// 上传文件表单元素

					String newHeadIconUrl = item.getName();
					if (newHeadIconUrl.equals("") || newHeadIconUrl == null)// 用户没有设置新头像
					{
						result1 = 2;
						continue;
					}

					File uploadedFile;
					String randomFileName;

					do {
						randomFileName = FileTool.getRandomFileNameByCurrentTime(newHeadIconUrl);
						String full = request.getServletContext()
								.getRealPath(WebProperties.config.getString("headIconDir") + "/" + randomFileName);
						uploadedFile = new File(full);
					} while (uploadedFile.exists());// 确保文件未存在

					item.write(uploadedFile);// 将临时文件转存为新文件保存
					result1 = 1;// 表示上传文件成功

					item.delete();// 删除临时文件
					result1 = 2;// 表示上传文件成功，且临时文件删除

					user.setHeadIconUrl("/" + WebProperties.config.getString("projectName")
							+ WebProperties.config.getString("headIconDirDefault") + "/" + randomFileName);
				}
			}

			UserDao userDao = new UserDao();
			userDao.updateHeadIcon(user);// 更新用户表的头像

			result1 = 3;// 表示上传文件成功，临时文件删除，且路径保存到数据库成功

			if (oldHeadIconUrl.equals("/" + WebProperties.config.getString("projectName")
					+ WebProperties.config.getString("headIconFileDefault")))
				result1 = 5;//// 表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片使用系统默认图片，不需要删除
			else {// 老的图片没有使用系统默认图片，需要删除
				File file = new File(
						FileTool.root.replace("\\" + WebProperties.config.getString("projectName") + "\\", "")
								+ oldHeadIconUrl);
				if (FileTool.deleteFile(file))
					result1 = 5;//// 表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片被删除
				else
					result1 = 4;//// 表示上传文件成功，临时文件删除，且路径保存到数据库成功，老的图片无法被删除
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -3;
		}

		UserInformationDao userInformationDao = new UserInformationDao();
		if ("user".equals(user.getUserType())) {//// 普通用户需要更新user_information
			userInformation.setUserId(user.getUserId());

			try {
				result2 = userInformationDao.update(userInformation);
			} catch (InstantiationException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} // 更新用户详细信息

		}
		if ((result1 > 0) && (result2 > 0))
			return 1;
		else
			return 0;

	}

}

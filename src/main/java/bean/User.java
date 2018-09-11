package bean;

import java.sql.Timestamp;

//用户类
public class User {

	private Integer userId;// 用户id，唯一
	private String userName;// 用户名，唯一
	private String userType;// 用户类型,有三类：user，newsAuthor，manager
	private String password;// 登录密码
	private String headIconUrl;// 头像，默认为"/news/headIcon/default.jpg"
	private Timestamp registerDate;// 注册日期
	private String enable;// 可用性，stop或use

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getHeadIconUrl() {
		return headIconUrl;
	}

	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
	}

}

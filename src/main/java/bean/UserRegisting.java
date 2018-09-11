package bean;

//需要管理员审核的非普通用户审核
public class UserRegisting {

	private Integer id;//
	private String userType;// 用户类型，只能是newsAuthor或manager
	private String userName;// 用户名
	private String password;// 登录密码

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}

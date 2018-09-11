package bean;

//用户信息，只有普通用户才有用户信息
public class UserInformation {

	private Integer userId;// 用户id
	private String sex;// 性别
	private String hobby;// 爱好

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}

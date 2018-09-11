package tool;

public class Message {
	private Integer result;
	private String message;// 显示的提示信息
	private String redirectUrl;// 跳转的网址
	private Integer redirectTime;// 跳转的间隔时间，单位秒

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Integer getRedirectTime() {
		return redirectTime;
	}

	public void setRedirectTime(Integer i) {
		this.redirectTime = i;
	}

	public Message() {
		// TODO 自动生成的构造函数存根

	}

}

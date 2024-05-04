package ex999_test;

public class Logout {
	
	UserInfo userInfo;
	
	public Logout (UserInfo userInfo) {
		this.userInfo = userInfo;
		userInfo = null;
	}
}

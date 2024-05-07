package flight_booking;

public class Logout {
	
	UserInfo userInfo;
	
	public Logout (UserInfo userInfo) {
		this.userInfo = userInfo;
		userInfo = null;
	}
}

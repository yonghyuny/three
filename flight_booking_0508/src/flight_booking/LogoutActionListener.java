package flight_booking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LogoutActionListener implements ActionListener{
	private UserInfo userInfo;
	private JFrame frame;
	
	public LogoutActionListener () { }
	
	public LogoutActionListener (UserInfo userInfo, JFrame frame) {
		this.userInfo = userInfo;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        // 로그아웃 시 LoginForm으로 이동
		new Logout(userInfo);
		new LoginForm();
        frame.setVisible(false); // 현재 화면 숨기기
	}
}

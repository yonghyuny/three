package flight_booking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ReservationDetailsActionListener implements ActionListener{

	UserInfo userInfo;
	JFrame frame;
	
	public ReservationDetailsActionListener() { }
	
	public ReservationDetailsActionListener(UserInfo userInfo, JFrame frame) {
		this.userInfo = userInfo;
		this.frame = frame;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
    	// 예매내역 화면으로 이동
        new ReservationDetails(userInfo);
        frame.setVisible(false); // 현재 화면 숨기기
	}

}

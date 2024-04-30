package ex999_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class ReservationDetails {
	
	

	/*
	 * 예매내역 확인
	 *
	 * -------------------------
	 * (상단메뉴)     		 로그아웃
	 * 
	 *
	 *      이름
	 *      전화번호
	 *      항공사	항공편
	 *      출발지	도착지
	 *      날짜     인원 
	 * 		
	 *
	 *      다운로드	메인화면
	 *      
	 *
	 * -------------------------
	 *
	 * 
	 *
	 * */

	JMenuBar jpBar;
	JPanel jpCon;
	JPanel jpCheck;
	
	UserInfo userInfo;
	
	public ReservationDetails () { }

	
	public ReservationDetails (UserInfo userInfo) {
		
		this.userInfo = userInfo;
		
		// 상단 바 - 로그아웃
		jpBar = new JMenuBar ();
		jpBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		JButton logout = new JButton("로그아웃");
		logout.setPreferredSize(new Dimension(100, 35));

		jpBar.add(logout);
		
		// 예매내역
		jpCon = new JPanel();
		jpCon.setLayout(new BoxLayout(jpCon, BoxLayout.Y_AXIS));
		
		JPanel name = new JPanel();
		JPanel number = new JPanel();
		JPanel airline = new JPanel();
		JPanel airlineInfo = new JPanel();
		JPanel startDest = new JPanel();
		JPanel startDestInfo = new JPanel();
		JPanel goPeople = new JPanel();
		JPanel goPeopleInfo = new JPanel();
		
		JLabel rName = new JLabel("이름");
		rName.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rName.setPreferredSize(new Dimension(200, 40));
		
		JLabel uName = new JLabel(userInfo.getName());
		uName.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel rNum = new JLabel("전화번호");
		rNum.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rNum.setPreferredSize(new Dimension(200, 40));
		
		JLabel uNum = new JLabel(userInfo.getNumber());
		uNum.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel rAirline = new JLabel("항공사");
		rAirline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rAirline.setPreferredSize(new Dimension(200, 40));
		
		JLabel rFlight = new JLabel("항공편");
		rFlight.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel uAirline = new JLabel(userInfo.getAirline());
		uAirline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel uAirplane = new JLabel(userInfo.getAirplane());
		uAirline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel rStart = new JLabel("출발지");
		rStart.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rStart.setPreferredSize(new Dimension(200, 40));
		
		JLabel rDest = new JLabel("도착지");
		rDest.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel uStartInfo = new JLabel("인천");
		uStartInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		uStartInfo.setPreferredSize(new Dimension(200, 40));
		
		JLabel uDestInfo = new JLabel(userInfo.getDestination());
		uDestInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		uDestInfo.setPreferredSize(new Dimension(200, 40));
		
		JLabel rGo = new JLabel("가는 날");
		rGo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rGo.setPreferredSize(new Dimension(200, 40));
		
		JLabel rPeople = new JLabel("탑승객 인원");
		rPeople.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel uGo = new JLabel(userInfo.getDate());
		uGo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel uPeople = new JLabel(userInfo.getPeople() + "명");
		uPeople.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		name.add(rName);
		name.add(uName);
		number.add(rNum);
		number.add(uNum);
		airline.add(rAirline);
		airline.add(rFlight);
		airlineInfo.add(uAirline);
		airlineInfo.add(uAirplane);
		startDest.add(rStart);
		startDest.add(rDest);
		startDestInfo.add(uStartInfo);
		startDestInfo.add(uDestInfo);
		goPeople.add(rGo);
		goPeople.add(rPeople);
		goPeopleInfo.add(uGo);
		goPeopleInfo.add(uPeople);
		
		
		jpCon.add(Box.createVerticalGlue());
		jpCon.add(name);
		jpCon.add(number);
		jpCon.add(airline);
		jpCon.add(airlineInfo);
		jpCon.add(startDest);
		jpCon.add(startDestInfo);
		jpCon.add(goPeople);
		jpCon.add(goPeopleInfo);

		
		
		
		// 다운로드, 메인화면 - 항공권조회
		jpCheck = new JPanel();
		JButton download = new JButton("다운로드");
		download.setPreferredSize(new Dimension(200, 35));
		
		JButton main = new JButton("메인화면");
		main.setPreferredSize(new Dimension(200, 35));
				
		jpCheck.add(download);
		jpCheck.add(main);
		
		JFrame f = new JFrame();
		f.setJMenuBar(jpBar);
		f.add(jpCon, BorderLayout.CENTER);
		f.add(jpCheck, BorderLayout.SOUTH);
		f.setSize(1400,800);
		
		f.setVisible(true);
		
		
		// 로그아웃 버튼 클릭 시 로그아웃 되고 로그인 페이지로 이동
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 로그인 창 호출
				logout();
				// new ();
				f.setVisible(false);
			}
			
		});
		
		// 다운로드
		download.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		// 메인화면 버튼 클릭 시 메인화면으로 이동
		main.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 메인화면 - 항공권 조회 화면
				new ReservationPanel1();
				f.setVisible(false);
			}
			
		});
		
	}
	

	public void logout() {
		userInfo = null;
	}
	
	public static void main(String[] args) {
		
		ReservationDetails rd = new ReservationDetails();
		
	}
	
	
}

package reservationDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import reservation.ReservationPanel;
import test_jframe.JFrameTest;

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
	

	
	public ReservationDetails () {
		// text 파일에서 정보 가져오기 - 이름, 전화번호
		
		
		// text 파일에서 항공권 정보 가져오기 - 항공사, 항공편, 도착지, 날짜, 인원
		
		
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
		
		JLabel rNum = new JLabel("전화번호");
		rNum.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rNum.setPreferredSize(new Dimension(200, 40));
		
		JLabel rAirline = new JLabel("항공사");
		rAirline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rAirline.setPreferredSize(new Dimension(200, 40));
		
		JLabel rFlight = new JLabel("항공편");
		rFlight.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel rStart = new JLabel("출발지");
		rStart.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rStart.setPreferredSize(new Dimension(200, 40));
		
		JLabel rStartInfo = new JLabel("인천");
		rStartInfo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rStartInfo.setPreferredSize(new Dimension(200, 40));
		
		JLabel rDest = new JLabel("도착지");
		rDest.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		JLabel rGo = new JLabel("가는 날");
		rGo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		rGo.setPreferredSize(new Dimension(200, 40));
		
		JLabel rPeople = new JLabel("탑승객 인원");
		rPeople.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		
		name.add(rName);
		
		number.add(rNum);
		
		airline.add(rAirline);
		airline.add(rFlight);
		// airlineInfo.add();
		// airlineInfo.add();
		startDest.add(rStart);
		startDest.add(rDest);
		startDestInfo.add(rStartInfo);
		// startDestInfo.add();
		goPeople.add(rGo);
		goPeople.add(rPeople);
		// goPeopleInfo.add();
		// goPeopleInfo.add();
		
		
		jpCon.add(Box.createVerticalGlue());
		jpCon.add(name);
		jpCon.add(number);
		jpCon.add(airline);
		// jpCon.add(airline);
		jpCon.add(startDest);
		jpCon.add(startDestInfo);
		jpCon.add(goPeople);
		// jpCon.add(goPeopleInfo);

		
		
		
		// 다운로드, 메인화면 - 항공권조회
		jpCheck = new JPanel();
		JButton download = new JButton("다운로드");
		download.setPreferredSize(new Dimension(200, 35));
		
		JButton main = new JButton("메인화면");
		main.setPreferredSize(new Dimension(200, 35));
				
		jpCheck.add(download);
		jpCheck.add(main);
		
		JFrameTest f = new JFrameTest();
		f.setJMenuBar(jpBar);
		f.add(jpCon, BorderLayout.CENTER);
		f.add(jpCheck, BorderLayout.SOUTH);
		
		f.setVisible(true);
		
		// 로그아웃 버튼 클릭 시 로그아웃 되고 로그인 페이지로 이동
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 로그인 창 호출
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
				new ReservationPanel();
				f.setVisible(false);
			}
			
		});
		
	}
	
	// 아이디를 확인하여 예매내역이 있는 지 확인
	public static boolean isIdExists(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("아이디: " + id + ",")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public static void main(String[] args) {
		
		ReservationDetails rd = new ReservationDetails();
		
	}
	
	
}

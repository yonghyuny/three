package ex999_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReservationDetails extends JFrame{
	

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

	JMenuBar jmenu;
	JPanel jpCon;
	JPanel jpCheck;
	
	UserInfo userInfo;
	
	public ReservationDetails () { }

	
	public ReservationDetails (UserInfo userInfo) {
		
		this.userInfo = userInfo;
		
		// 상단 메뉴바
		menuBar();
		// 예매 내역 정보
		reservationInfo();
		
		showFrame();
		
		
	}
	
	// 상단 메뉴바
    public void menuBar() {
    	jmenu = new JMenuBar ();

    	JMenuItem logout = new JMenuItem("로그아웃");
    	jmenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    	
    	jmenu.add(logout);
        
    	 // 로그아웃
    	logout.addActionListener(new LogoutActionListener(userInfo, this));


	}
    
    // 예매 내역 정보
    public void reservationInfo() {
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
    	
    	// 항공사 로고 이미지 넣기
    	ImageIcon airlineIcon = new ImageIcon(airlineImg(userInfo.getAirline()));
    			
    	JLabel rAirlineImg = new JLabel();
    	rAirlineImg.setIcon(airlineIcon);
    	rAirlineImg.setPreferredSize(new Dimension(30, 30));
    			
    	JLabel uAirline = new JLabel(userInfo.getAirline());
    	uAirline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    	uAirline.setPreferredSize(new Dimension(200, 40));
    			
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
    			
    	JLabel rGo = new JLabel("가는 날");
    	rGo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    	rGo.setPreferredSize(new Dimension(200, 40));
    			
    	JLabel rPeople = new JLabel("탑승객 인원");
    	rPeople.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    			
    	JLabel uGo = new JLabel(userInfo.getDate());
    	uGo.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    	uGo.setPreferredSize(new Dimension(200, 40));
    			
    	JLabel uPeople = new JLabel(userInfo.getPeople() + "명");
    	uPeople.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    			
    	name.add(rName);
    	name.add(uName);
    	number.add(rNum);
    	number.add(uNum);
    	airline.add(rAirline);
    	airline.add(rFlight);
    	airlineInfo.add(rAirlineImg);
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
    			

    			
    	// 다운로드
    	download.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent e) {
    					
    			String reservationInfo = "이름 : " + userInfo.getName() + "\n전화번호 : " + userInfo.getNumber() +
    									"\n항공사 : " + userInfo.getAirline() + "\n항공편 : " + userInfo.getAirplane() +
    									"\n날짜 : " + userInfo.getDate() + "\n출발지 : 인천" + "\n도착지 : " + userInfo.getDestination() + 
    									"\n인원 : " + userInfo.getPeople();
    					
    			JFileChooser fileChooser = new JFileChooser();
    		    fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); // .txt 파일만 필터링
    		    int userSelection = fileChooser.showSaveDialog(null);

    		    if (userSelection == JFileChooser.APPROVE_OPTION) {
    		    	File fileToSave = fileChooser.getSelectedFile();
    		        if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
    		        	// 파일의 확장자가 .txt가 아닌 경우 확장자를 .txt로 추가
    		            fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
    		        }
    		        try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
    		        	writer.println(reservationInfo); // 예매내역을 텍스트 파일에 쓰기
    		            System.out.println("예매내역이 저장되었습니다.");
    		        } catch (IOException e2) {
    		            e2.printStackTrace();
    		            System.err.println("저장 실패");
    		        }
    		   }
    		}
    				
    	});
    			
    	// 메인화면 버튼 클릭 시 메인화면으로 이동
    	main.addActionListener(new ActionListener() {
    				
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// 메인화면 - 항공권 조회 화면
    			new ReservationPanel(userInfo);
    			setVisible(false);
    		}
    				
    	});
    			
    }
    
 // 항공사 이미지 경로 찾기 		
 	public String airlineImg (String airline) {
 		String airlineName = "";
 		
 		if (airline.equals("진에어")) {
 			airlineName = "jinair";
 		} else if (airline.equals("이스타항공")) {
 			airlineName = "easta";
 		} else if (airline.equals("티웨이항공")) {
 			airlineName = "tway";
 		} else if (airline.equals("에어서울")) {
 			airlineName = "airseoul";
 		} else if (airline.equals("아시아나항공")) {
 			airlineName = "ana";
 		} else if (airline.equals("에어부산")) {
 			airlineName = "airbusan";
 		} else if (airline.equals("대한항공")) {
 			airlineName = "kal";
 		}
 		
        return "logo/" + airlineName + ".png";

 	}
    
    public void showFrame () {
		setJMenuBar(jmenu);
		add(jpCon, BorderLayout.CENTER);
		add(jpCheck, BorderLayout.SOUTH);
		setSize(1400,800);
        setLocationRelativeTo(null);
		setVisible(true);
    }
	

	
}

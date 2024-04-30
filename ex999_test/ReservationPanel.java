package ex999_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import flight.Flight;
//import flight.FlightList;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
//import reservationDetails.ReservationDetails;
//import test_jframe.JFrameTest;
//import userInfo.UserInfo;
//import userInfo.UserInfoManager;

public class ReservationPanel {
	
	/*
	 * 항공권 조회 화면
	 *
	 * -------------------------
	 * (상단메뉴)     예매내역 로그아웃
	 * 
	 *
	 *      출발지   도착지
	 *      날짜     인원 
	 * 		
	 *
	 *        조회하기 버튼
	 *
	 * -------------------------
	 *
	 * ! 
	 *
	 * */
	
	JMenuBar jMenu;
	JPanel jpCon;
	JPanel jpCheck;
	
	String date;
	String destination;
	int countPeople;
	
	public ReservationPanel () {
		
		// 상단 바 - 예매내역, 로그아웃
		jMenu = new JMenuBar ();

    	JMenuItem reser = new JMenuItem("예매내역");
    	JMenuItem logout = new JMenuItem("로그아웃");
    	jMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    	
    	jMenu.add(reser);
    	jMenu.add(logout);
   
					
		// 항공권 조회
		jpCon = new JPanel ();
		jpCon.setLayout(new BoxLayout(jpCon, BoxLayout.Y_AXIS));
		
		JPanel startDest1 = new JPanel ();
		JPanel startDest2 = new JPanel ();
		JPanel goPeople1 = new JPanel ();
		JPanel goPeople2 = new JPanel ();

		// 출발지, 도착지
		JLabel jl1 = new JLabel("출발지");
		jl1.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		jl1.setPreferredSize(new Dimension(280, 28));
		
		JLabel jl2 = new JLabel("도착지");
		jl2.setFont(new Font("맑은 고딕", Font.PLAIN, 28));

		
					
		// 출발지 고정, 목적지
		JLabel start = new JLabel("인천(Incheon)");
		start.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		start.setPreferredSize(new Dimension(250, 35));

		String[] destList = {"목적지", "김포", "청주", "김해", "대구", "제주"};
		JComboBox<String> dest = new JComboBox <> (destList);
		dest.setPreferredSize(new Dimension(200, 38));
		dest.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		dest.setBackground(Color.white);
		
		ActionListener alDest = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destination = (String)dest.getSelectedItem();
				System.out.println("목적지 : " + destination);
			}
		};
				
		dest.addActionListener(alDest);
		
		// 가는 날, 인원
		JLabel jl3 = new JLabel("가는 날");
		jl3.setFont(new Font("맑은 고딕", Font.PLAIN, 28));
		jl3.setPreferredSize(new Dimension(200, 40));
		
		JLabel jl4 = new JLabel("탑승객 인원");
		jl4.setFont(new Font("맑은 고딕", Font.PLAIN, 28));

		
				
		// 날짜 선택
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();

				
		// 인원 선택
		Integer[] num = {1, 2, 3};
		JComboBox<Integer> people = new JComboBox <>(num);
		people.setPreferredSize(new Dimension(200, 30));
		people.setBackground(Color.white);
		people.setSelectedIndex(0);

		people.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
					
		ActionListener alPeople = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				countPeople = (Integer)people.getSelectedItem();
				System.out.println("인원 수 : " + countPeople);
				
				
			}
		};
		
		people.addActionListener(alPeople);   
		
		startDest1.add(jl1);
		startDest1.add(jl2);
		startDest2.add(start);
		startDest2.add(dest);
		goPeople1.add(jl3);
		goPeople1.add(jl4);
		goPeople2.add(datePicker);
		goPeople2.add(people);
		
		jpCon.add(Box.createVerticalGlue());
		jpCon.add(Box.createVerticalGlue());
		jpCon.add(startDest1);
		jpCon.add(startDest2);
		jpCon.add(goPeople1);
		jpCon.add(goPeople2);
		jpCon.add(Box.createVerticalGlue());
		jpCon.add(Box.createVerticalGlue());
				
				
		// 조회 버튼
		jpCheck = new JPanel();
		JButton check = new JButton("항공권 조회하기");
		check.setPreferredSize(new Dimension(200, 35));
				
		jpCheck.add(check);
		
		JFrame f = new JFrame();
		f.setJMenuBar(jMenu);
		f.add(jpCon, BorderLayout.CENTER);
		f.setLocationRelativeTo(null);
		f.add(jpCheck, BorderLayout.SOUTH);
		f.setSize(1400,800);
		
		f.setVisible(true);

		// 예매내역 버튼 클릭 시 예매내역 페이지로 이동
		reser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 예매내역 창 호출
				UserInfo userInfo = new UserInfo("id");
				new ReservationDetails(userInfo);
				f.setVisible(false);
			}
		});
		
		
		// 로그아웃 버튼 클릭 시 로그아웃 되고 로그인 페이지로 이동
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 로그인 창 호출
//				logout();
				// new LoginForm();
				f.setVisible(false);
			}
		});
		
		
		// 조회하기 버튼 클릭 시 항공권 조회 페이지 이동
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 항공권 조회 정보
	            String selectedDest = (String) dest.getSelectedItem();
				if(!selectedDest.equals("목적지")) {
					ReservationInfo tempReser = new ReservationInfo(date, destination, countPeople);
					
					new FlightList(tempReser);
					
		            f.setVisible(false);
				}  else {
		            // 유효하지 않은 목적지인 경우
		            JOptionPane.showMessageDialog(f, "목적지를 선택해주세요.");
		        }
				
			}
		});
		
		
	}
	
//	public void logout() {
//		userInfo = null;
//	}
	
	public static void main(String[] args) {
		
		ReservationPanel rp = new ReservationPanel();
		
	}
	

	
}

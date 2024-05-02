package ex999_test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Seat extends JFrame{
	
	JComboBox<String> seatClass;
	JLabel seatPriceCont;
	JButton bookingBtn;
	UserInfo userInfo;
	ReservationInfo tempReser;
	String[] selectedSeatsInfo;
	JMenuBar jmenu;
	int totalPrice;
	
	public Seat () { }   
    
    public Seat(UserInfo userInfo, ReservationInfo tempReser) {	
    	this.userInfo = userInfo;
    	this.tempReser = tempReser;
    	
    	menuBar();
        flightSeat();
        showFrame();
    
	}


    // 상단 메뉴바
    public void menuBar() {
    	jmenu = new JMenuBar();
    	JMenuItem reser = new JMenuItem("예매내역");
    	JMenuItem logout = new JMenuItem("로그아웃");
    	jmenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    	
        jmenu.add(reser);
        jmenu.add(logout);  	

        // 로그아웃 메뉴 아이템에 대한 이벤트 리스너 추가
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그아웃 시 LoginForm으로 이동
				new Logout(userInfo);
				new LoginForm();
                setVisible(false); // 현재 화면 숨기기
            }
        });
        
        // 예매내역 메뉴 아이템에 대한 이벤트 리스너 추가
        reser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 예매내역 화면으로 이동
                ReservationDetails reservationDetails = new ReservationDetails(userInfo);
                setVisible(false); // 현재 화면 숨기기
            }
        });

	}

     
    public void flightSeat() {   
        
        JPanel pnl = new JPanel();
        pnl.setLayout(null);
        
        // 상단 로고 이미지
        ImageIcon logoImg = new ImageIcon("src/img/logo.png");
        Image logo = logoImg.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
        ImageIcon scaleImg = new ImageIcon(logo);
        JLabel logoLb = new JLabel(scaleImg);
        logoLb.setBounds(540,-70,300,300);
        getContentPane().add(logoLb);        
        
        // 좌석 label
        JLabel lblNewLabel = new JLabel("Front");
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setBounds(305, 140, 780, 40);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lblNewLabel.setForeground(Color.white);
        
        
        // 좌석 정보 출력 label
        JLabel selectedSeatCont = new JLabel();
        selectedSeatCont.setBounds(1128,580,150,40);
        selectedSeatCont.setFont(new Font("Arial", Font.PLAIN, 18));
        selectedSeatCont.setForeground(Color.BLUE);
        getContentPane().add(selectedSeatCont);     

        
        // 좌석 버튼
        int buttonWidth = 70;
        int buttonHeight = 50;
        int startX = 300;
        int startY = 200;
        int gapX = 80; // X축 간격
        int gapIncrement = 40; // 오른쪽 여백 증가량
        int gapAccumulator = 0; // 여백 누적값 초기화
                
        JCheckBox[][] seatBtn = new JCheckBox[10][10];
		ArrayList<String> selectedSeats = new ArrayList<>();  // 선택한 좌석들을 저장할 리스트 생성
        
        for (int i = 0; i < 10; i++ ) {
        	for (int j = 1; j <= 9; j++) {
        		
        		JCheckBox button = new JCheckBox(""+(char)('A'+i)+(j+1));
        		button.setBackground(Color.white);
        		
        		// 버튼 크기 고정
        		button.setSize(buttonWidth, buttonHeight);
        		
        		// 버튼 위치 설정
        		int column = j - 1;
        		int buttonX = startX + (column * gapX) + gapAccumulator; // 현재 버튼의 X 좌표
                int buttonY = startY + (i * buttonHeight); // 현재 버튼의 Y 좌표
                
                button.setLocation(buttonX, buttonY);
        		
        		// 3의 배수 번째 버튼일 때 오른쪽 여백 증가
        		if (j % 3 == 0) {
        			gapAccumulator += gapIncrement;
        		}
        		
        		pnl.add(button);
        		seatBtn[i][j] = button;

        		

        		// 선택한 버튼의 좌석이름, 가격 정보 출력하기 
        		button.addActionListener(new ActionListener() {
        			@Override
        			public void actionPerformed(ActionEvent e) {        				

        				// 선택한 좌석번호
        				String selectedSeatText = button.getText();	
        
        		        // 선택한 좌석 번호를 리스트에 담기
        		        selectedSeats.add(selectedSeatText);

        		        
        		        // 선택한 좌석 정보를 배열에 담아 출력
        		        selectedSeatsInfo = selectedSeats.toArray(new String[selectedSeats.size()]);
        		        if (selectedSeatsInfo[0] != null) {
        		        	selectedSeatCont.setText(Arrays.toString(selectedSeatsInfo));
        		        }
        		        
        		        // 배열이 다 채워져야 예매하기 버튼 활성화
        		        if (selectedSeatsInfo.length == tempReser.getCountPeople() && selectedSeatsInfo[tempReser.getCountPeople() - 1] != null) {
        		            bookingBtn.setEnabled(true);
        		        } else {
        		            bookingBtn.setEnabled(false);
        		        }
        		        
        		        // 최대 인원 초과 처리
        		        if (selectedSeats.size() > tempReser.getCountPeople()) {
        		        	// 팝업 창 표시
        		            JOptionPane.showMessageDialog(null, "인원을 초과하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        		            // 마지막에 추가된 좌석번호 삭제
        		            selectedSeats.remove(selectedSeatText);
        		            selectedSeatsInfo = selectedSeats.toArray(new String[selectedSeats.size()]);
        		            selectedSeatCont.setText(Arrays.toString(selectedSeatsInfo));
        		            // 버튼 비활성화
        		            button.setEnabled(false);
        		            
        		        } else {
        		            // 선택한 좌석의 가격 누적
        		            if (selectedSeatText.charAt(0) >= 'A' && selectedSeatText.charAt(0) <= 'B') {
        		                totalPrice += 120000; // 1~2번째 줄
        		            } else if (selectedSeatText.charAt(0) >= 'C' && selectedSeatText.charAt(0) <= 'E') {
        		                totalPrice += 80000; // 3~5번째 줄
        		            } else {
        		                totalPrice += 50000; // 6~10번째 줄
        		            }
        		        }

        		        // 좌석 가격 업데이트
        		        seatPriceCont.setText(totalPrice + "원");
        		        
        		    }
        			
        		});
      		
        	}
        	
            gapAccumulator = 0; // 줄 바뀔 때 오른쪽 여백 초기화

        }
        
        
        // 등급 선택 텍스트 라벨
        JLabel selectClass = new JLabel("좌석 등급");
        selectClass.setBounds(1125,200,150,40);
        selectClass.setFont(new Font("Arial", Font.BOLD, 22));
        getContentPane().add(selectClass); 
        
        // 등급 선택 콤보박스
        String[] title = {"등급 선택", "이코노미", "비즈니스", "퍼스트"};
        seatClass = new JComboBox<>(title);
        seatClass.setBounds(1120,240,200,50);
        getContentPane().add(seatClass);   
        
        ActionListener seatAl = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String seatValue = (String)seatClass.getSelectedItem();
				
				// 등급 선택시 선택한 좌석정보 초기화
		        seatPriceCont.setText("");
		        selectedSeatCont.setText("");
		        
		        // 좌석 담는 리스트 초기화
		        selectedSeatsInfo = new String[selectedSeatsInfo.length];
				
				// 비활성화 누적방지 전체 버튼 활성화
				for (int i=0; i<10; i++) {
					for (int j=1; j<10; j++) {
						seatBtn[i][j].setEnabled(true);
					}
				}
				
				if (seatValue.equals(title[1])) {
					// 이코노미를 선택했을 때
					// button 중에서 1~5번째 줄 button을 비활성화 시킨다.
					for (int i=0; i<5; i++) {
						for(int j=1; j<10; j++) {
							seatBtn[i][j].setEnabled(false);
						}			
					}
					
				} else if(seatValue.equals(title[2])) {				
					// 비즈니스를 선택했을 때
					// 1~2번째 줄과 6~10번째 줄 button을 비활성화 시킨다.
					for (int i = 0; i < 2; i++) {
		                for (int j = 1; j < 10; j++) {
		                    seatBtn[i][j].setEnabled(false);
		                }
		            }
		            for (int i = 5; i < 10; i++) {
		                for (int j = 1; j < 10; j++) {
		                    seatBtn[i][j].setEnabled(false);
		                }
		            }

					
				} else if(seatValue.equals(title[3])) {
					// 퍼스트를 선택했을 때
					// 3~10번째 줄 Button을 비활성화 시킨다.
					for (int i = 2; i < 10; i++) {
		                for (int j = 1; j < 10; j++) {
		                    seatBtn[i][j].setEnabled(false);
		                }
		            }
				}
					
			}				
			
		};
		
		seatClass.addActionListener(seatAl);   
		
		
		// 선택한 좌석 정보 타이틀 텍스트 라벨
        JLabel selectedSeat = new JLabel("선택한 좌석 정보");
        selectedSeat.setBounds(1125,500,150,40);
        selectedSeat.setFont(new Font("Arial", Font.BOLD, 22));
        getContentPane().add(selectedSeat);
        
        
        // ReservationPanel 에서 선택한 인원 출력 텍스트 라벨        
        JLabel selectedNum = new JLabel("인원 : "+tempReser.getCountPeople());        
        selectedNum.setBounds(1125,560,150,30);
        selectedNum.setFont(new Font("Arial", Font.PLAIN, 18));
        getContentPane().add(selectedNum);

        
        // 좌석 가격 출력 label
        seatPriceCont = new JLabel(totalPrice+"");
        seatPriceCont.setBounds(1250,580,150,40);
        seatPriceCont.setFont(new Font("Arial", Font.BOLD, 18));
        seatPriceCont.setForeground(Color.BLUE);
        getContentPane().add(seatPriceCont);   
        
		// 예매하기 버튼 
        bookingBtn = new JButton("예매하기");
        bookingBtn.setBounds(1124,640,200,60);
        bookingBtn.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(bookingBtn);
        
		
        bookingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 예약 정보 저장
				userInfo.setAirline(tempReser.getAirline());
				userInfo.setAirplane(tempReser.getAirplane());
				userInfo.setDestination(tempReser.getDestination());
				userInfo.setDate(tempReser.getDate());
				userInfo.setPeople(tempReser.getCountPeople());
				
				// 예약 정보 텍스트 파일로 저장
				saveMemberReservationToFile(userInfo);

				// 예매내역확인 화면으로 이동
				new ReservationDetails(userInfo);
				
				// 현재 화면 숨기기
				setVisible(false);
				
			}
		});
        
		
		// 최종 패널 붙이기
        getContentPane().add(pnl);
        setVisible(true);
        
    }
	

    public void showFrame() {
        setTitle("좌석 선택");
        setSize(1400,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setJMenuBar(jmenu);
        setVisible(true);
        
    }
    
    private static void saveMemberReservationToFile(UserInfo userInfo) {
        // 파일에 회원 정보 저장
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("membersReservation.txt", true))) {
            bw.write(userInfo.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}

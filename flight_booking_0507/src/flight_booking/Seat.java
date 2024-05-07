package flight_booking;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Seat extends JFrame {

    JComboBox<String> seatClass;
    JLabel seatPriceCont;
    JButton bookingBtn;
    UserInfo userInfo;
    ReservationInfo tempReser;
    String[] selectedSeatsInfo;
    MenuBar jmenu;
    int totalPrice;
    String[] selectedSeats;

    public Seat() {
    }

    public Seat(UserInfo userInfo, ReservationInfo tempReser) {
        this.userInfo = userInfo;
        this.tempReser = tempReser;

        menuBar();
        flightSeat();
        showFrame();
    }

    public void menuBar() {
        jmenu = new MenuBar();
        jmenu.logout.addActionListener(new LogoutActionListener(userInfo, this));
        jmenu.reser.addActionListener(new ReservationDetailsActionListener(userInfo, this));
    }

    public void flightSeat() {

        JPanel pnl = new JPanel();
        pnl.setLayout(null);

        ImageIcon logoImg = new ImageIcon("src/logo/logo.png");
        Image logo = logoImg.getImage().getScaledInstance(220, 60, Image.SCALE_SMOOTH);
        ImageIcon scaleImg = new ImageIcon(logo);
        JLabel logoLb = new JLabel(scaleImg);
        logoLb.setBounds(540, -70, 300, 300);
        getContentPane().add(logoLb);

        JLabel lblNewLabel = new JLabel("Front");
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setBounds(305, 140, 780, 40);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        getContentPane().add(lblNewLabel);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblNewLabel.setForeground(Color.GRAY);

        JLabel selectedSeatCont = new JLabel();
        selectedSeatCont.setBounds(1128, 580, 150, 40);
        selectedSeatCont.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        selectedSeatCont.setForeground(Color.BLUE);
        getContentPane().add(selectedSeatCont);

        int buttonWidth = 60;
        int buttonHeight = 50;
        int startX = 305;
        int startY = 200;
        int gapX = 75;
        int gapIncrement = 60;
        int gapAccumulator = 0;

        JCheckBox[][] seatBtn = new JCheckBox[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= 9; j++) {

                seatBtn[i][j] = new JCheckBox("" + (char) ('A' + i) + (j), false);

                seatBtn[i][j].setSize(buttonWidth, buttonHeight);

                int column = j - 1;
                int buttonX = startX + (column * gapX) + gapAccumulator;
                int buttonY = startY + (i * buttonHeight);

                seatBtn[i][j].setLocation(buttonX, buttonY);

                if (j % 3 == 0) {
                    gapAccumulator += gapIncrement;
                }
                
             // 버튼 배경색&글자색
                if(i<2) {	//퍼스트
                	Color color1 = new Color(0x526D82);
                	seatBtn[i][j].setBackground(color1);
                	seatBtn[i][j].setForeground(Color.WHITE);
                } else if(i<5) {	//비즈니스
                	Color color2 = new Color(0x9DB2BF);
                	seatBtn[i][j].setBackground(color2);
                } else if(i<10) {	//이코노미
                	Color color3 = new Color(0xDDE6ED);
                	seatBtn[i][j].setBackground(color3);
                }


                
                pnl.add(seatBtn[i][j]);

            }
            gapAccumulator = 0;
        }

        JLabel selectClass = new JLabel("좌석 등급");
        selectClass.setBounds(1125, 200, 150, 40);
        selectClass.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        getContentPane().add(selectClass);

        String[] title = { "등급 선택", "이코노미", "비즈니스", "퍼스트" };
        seatClass = new JComboBox<>(title);
        seatClass.setBounds(1120, 240, 200, 50);
        getContentPane().add(seatClass);

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                seatBtn[i][j].setEnabled(false);
                seatBtn[i][j].setSelected(false);
            }
        }

        ActionListener seatAl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seatValue = (String) seatClass.getSelectedItem();

                if (seatValue.equals(title[0])) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 1; j < 10; j++) {
                            seatBtn[i][j].setEnabled(false);
                            seatBtn[i][j].setSelected(false);
                        }
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 1; j < 10; j++) {
                            seatBtn[i][j].setEnabled(true);
                        }
                    }

                    if (seatValue.equals(title[1])) {
                    	for(int i=5; i<10; i++) {
                    		for(int j=1; j<10; j++) {                    			
                    			seatBtn[i][j].setBorderPainted(true);
                    		}
                    	}
                        for (int i = 0; i < 5; i++) {
                            for (int j = 1; j < 10; j++) {
                                seatBtn[i][j].setEnabled(false);
                                seatBtn[i][j].setSelected(false);
                                seatBtn[i][j].setBorderPainted(false);
                            }
                        }
                    } else if (seatValue.equals(title[2])) {
                    	for (int i=2; i<5; i++) {
                    		for(int j=1; j<10; j++) {
                    			seatBtn[i][j].setBorderPainted(true);
                    		}
                    	}
                        for (int i = 0; i < 2; i++) {
                            for (int j = 1; j < 10; j++) {
                                seatBtn[i][j].setEnabled(false);
                                seatBtn[i][j].setSelected(false);
                                seatBtn[i][j].setBorderPainted(false);
                            }
                        }
                        for (int i = 5; i < 10; i++) {
                            for (int j = 1; j < 10; j++) {
                                seatBtn[i][j].setEnabled(false);
                                seatBtn[i][j].setSelected(false);
                                seatBtn[i][j].setBorderPainted(false);
                            }
                        }
                    } else if (seatValue.equals(title[3])) {
                    	for(int i=0; i<2; i++) {
                    		for(int j=1; j<10; j++) {
                    			seatBtn[i][j].setBorderPainted(true);
                    		}
                    	}
                        for (int i = 2; i < 10; i++) {
                            for (int j = 1; j < 10; j++) {
                                seatBtn[i][j].setEnabled(false);
                                seatBtn[i][j].setSelected(false);
                                seatBtn[i][j].setBorderPainted(false);
                            }
                        }
                    }
                }
            }

        };

        seatClass.addActionListener(seatAl);

        selectedSeats = new String[tempReser.getCountPeople()];

        final int[] selectCount = { 0 };

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= 9; j++) {
                seatBtn[i][j].addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        JCheckBox checkBox = (JCheckBox) e.getItem();
                        String seatName = checkBox.getText();
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            System.out.println(seatName + "이(가) 선택되었습니다.");
                            if (selectCount[0] >= tempReser.getCountPeople()) {
                                JOptionPane.showMessageDialog(null, "인원을 초과하였습니다.", "오류",
                                        JOptionPane.ERROR_MESSAGE);
                                checkBox.setSelected(false);
                                return;
                            } else {
                                selectedSeats[selectCount[0]++] = seatName;
                                int price = calculatePrice(seatName);
                                totalPrice += price;
                                seatPriceCont.setText(totalPrice + "원");
                                // 선택된 좌석 정보 업데이트
                                updateSelectedSeats(selectedSeatCont);
                            }
                            
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            System.out.println(seatName + "이(가) 선택 해제되었습니다.");
                            int price = calculatePrice(seatName);
                            totalPrice -= price;
                            seatPriceCont.setText(totalPrice + "원");
                            for (int k = 0; k < selectCount[0]; k++) {
                                if (selectedSeats[k] != null && selectedSeats[k].equals(seatName)) {
                                    selectedSeats[k] = null;
                                    selectCount[0]--;
                                }
                            }
                            // 선택된 좌석 정보 업데이트
                            updateSelectedSeats(selectedSeatCont);
                        }
                        
                        // 배열의 마지막 요소가 채워져야 예매하기 버튼 활성화
                        if(selectedSeats[tempReser.getCountPeople()-1] != null) {
                        	bookingBtn.setEnabled(true);
                        }
                    }
                });
            }
        }

        JLabel selectedSeat = new JLabel("선택한 좌석 정보");
        selectedSeat.setBounds(1125, 500, 150, 40);
        selectedSeat.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        getContentPane().add(selectedSeat);

        JLabel selectedNum = new JLabel("인원 : " + tempReser.getCountPeople());
        selectedNum.setBounds(1125, 560, 150, 30);
        selectedNum.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        getContentPane().add(selectedNum);

        seatPriceCont = new JLabel(totalPrice + "");
        seatPriceCont.setBounds(1250, 580, 150, 40);
        seatPriceCont.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        seatPriceCont.setForeground(Color.BLUE);
        getContentPane().add(seatPriceCont);

        bookingBtn = new JButton("예매하기");
        bookingBtn.setBounds(1124, 640, 200, 60);
        bookingBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        getContentPane().add(bookingBtn);
        bookingBtn.setEnabled(false);

        bookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 예약 정보 저장
                userInfo.setAirline(tempReser.getAirline());
                userInfo.setAirplane(tempReser.getAirplane());
                userInfo.setDestination(tempReser.getDestination());
                userInfo.setDate(tempReser.getDate());
                userInfo.setSeats(selectedSeats);
                
                // 예약 정보 텍스트 파일로 저장
                saveMemberReservationToFile(userInfo);
                // 예매내역확인 화면으로 이동
                new ReservationDetails(userInfo);
                setVisible(false);
            }
        });

        // 최종 패널 붙이기
        getContentPane().add(pnl);
        setVisible(true);
    }

    public void showFrame() {
        setTitle("좌석 선택");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setJMenuBar(jmenu.getMenuBar());
        setVisible(true);
    }

    // 등급별 가격 메서드
    private int calculatePrice(String seatName) {
        int price;
        if (seatName.charAt(0) >= 'A' && seatName.charAt(0) <= 'B') {
            price = 120000;
        } else if (seatName.charAt(0) >= 'C' && seatName.charAt(0) <= 'E') {
            price = 80000;
        } else {
            price = 50000;
        }
        return price;
    }

    // 파일에 회원 정보 저장
    private void saveMemberReservationToFile(UserInfo userInfo) {
        FileWriter fw = null;
    	BufferedWriter bw = null;
    	try {
    		fw = new FileWriter("membersReservation.txt", true);
    		bw = new BufferedWriter(fw);
    		
            bw.write(userInfo.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				if(bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }
    }

    // 선택한 좌석 배열 출력 메서드
    private void updateSelectedSeats(JLabel selectedSeatCont) {
        StringBuilder sb = new StringBuilder();
        for (String seat : selectedSeats) {
            if (seat != null) {
                sb.append(seat).append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        selectedSeatCont.setText(sb.toString());
    }
}

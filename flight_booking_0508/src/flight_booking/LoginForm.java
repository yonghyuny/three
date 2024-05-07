package flight_booking;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


class MembershipChecker {
	
	public static boolean isIdExists(String id) {
 
		FileReader reader = null;
        BufferedReader br = null;
    	// 아이디 중복 체크
        try {
        	reader = new FileReader("members.txt");
        	br = new BufferedReader(reader);
        	
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("아이디: " + id + ",")) {
                    return true;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				if(br != null) {
					br.close();
				}
				if(reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }
        return false;
    }
		
	
	public static boolean isPasswordCorrect(String enteredPassword, String storedHashedPassword) {
        // 비밀번호 해싱
        String enteredPasswordHash = hashPassword(enteredPassword);
        // 해싱된 비밀번호와 저장된 해싱된 비밀번호 비교
        return enteredPasswordHash.equals(storedHashedPassword);
	}

	private static String hashPassword(String password) {
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
}

@SuppressWarnings("serial")
public class LoginForm extends JFrame {

	/* 
	 * 로그인 화면
	 * 
	 * -------------------------
	 * 		
	 * 		ID	텍스트입력
	 * 		PW	비밀번호입력
	 * 
	 * 	   로그인버튼   가입버튼
	 *
	 * -------------------------
	 * 
	 *	! 아이디가 비었을 경우 -> "아이디를 입력하세요"
	 *	! 비밀번호가 비었을 경우 -> "비밀번호를 입력하세요"
	 *
	 * */
	
	// JPanel 생성
	//	ID,PW -> JLabel
	//	아이디 입력바 -> JTextField
	//	비밀번호 입력바 -> JPasswordField
	//	버튼 -> JButton
	
	// 위 JPanel을 JFrame에 add	
	
	private JLabel idLb;
    private JLabel pwLb;
    private JTextField idTf;
    private JPasswordField pwPf;
    private JButton btnLogin;
    private JButton btnJoin;
    private ImageIcon logoImg;
    private JLabel logoLb;
	UserInfo userInfo;
	
    
    public LoginForm() {
		init();
		setDisplay();
		addListeners();
	    showFrame();

	}

    
    public void init() {
   
        Dimension lbSize = new Dimension(80, 30);
        int tfSize = 10;
        Dimension btnSize = new Dimension(100, 25);

        idLb = new JLabel("ID");
        idLb.setPreferredSize(lbSize);
        pwLb = new JLabel("Password");
        pwLb.setPreferredSize(lbSize);

        idTf = new JTextField(tfSize);
        pwPf = new JPasswordField(tfSize);

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(btnSize);
        btnJoin = new JButton("Join");
        btnJoin.setPreferredSize(btnSize);
        

        logoImg = new ImageIcon("src/logo/logo.png");
        Image img = logoImg.getImage().getScaledInstance(240, 70, Image.SCALE_SMOOTH);
        ImageIcon scaleImg = new ImageIcon(img);
		logoLb = new JLabel(scaleImg);
		logoLb.setBounds(0,0,10,10);
    }
    
    public void setDisplay() {
        // FlowLayout 왼쪽 정렬
        FlowLayout flowLeft = new FlowLayout(FlowLayout.CENTER);

        JPanel pnlNorth = new JPanel(new GridLayout(0, 1));
        pnlNorth.add(logoLb);

        JPanel pnlId = new JPanel(flowLeft);
        pnlId.add(idLb);
        pnlId.add(idTf);

        JPanel pnlPw = new JPanel(flowLeft);
        pnlPw.add(pwLb);
        pnlPw.add(pwPf);
        
        pnlNorth.add(pnlId);
        pnlNorth.add(pnlPw);

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnLogin);
        pnlSouth.add(btnJoin);

        pnlNorth.setBorder(new EmptyBorder(200, 20, 0, 20));
        pnlSouth.setBorder(new EmptyBorder(0, 0, 10, 0));      
        
        add(pnlNorth, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    private static String getPasswordForId(String id) {
    	FileReader reader = null;
    	BufferedReader br = null;
          	
    	try {
    		reader = new FileReader("members.txt");
    		br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("아이디: " + id + ",")) {
                    String[] parts = line.split(", ");
                    for (String part : parts) {
                        if (part.startsWith("비밀번호: ")) {
                            return part.substring("비밀번호: ".length());
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				if(br != null) {
					br.close();
				}
				if(reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
        }
        return null;
    }
    
    public void addListeners() {

        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	// join 버튼 선택시 join 화면으로 이동
            	Join joinFrame = new Join(LoginForm.this);
            	joinFrame.setVisible(true);
            	setVisible(false);
            	
                idTf.setText("");
                pwPf.setText("");
            }
        });
        
        btnLogin.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {			
				// 아이디 입력이 비었을 때
				if (idTf.getText().isEmpty()) {
					// showMessageDialog
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					
				} else if (MembershipChecker.isIdExists(idTf.getText())) {	// member.txt 파일 읽어서 확인
					// member.txt 파일에 존재하는 아이디일 때
				    if (pwPf.getPassword().length == 0) {
				        // 비밀번호가 비었을 때
				        JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				    } else {
				        // 비밀번호가 입력되었을 때
				        String enteredPassword = new String(pwPf.getPassword());
				        String storedPassword = getPasswordForId(idTf.getText()); // member.txt 파일에서 해당 아이디의 비밀번호 가져오기
				        if (storedPassword != null && MembershipChecker.isPasswordCorrect(enteredPassword, storedPassword)) {
				        	
				        	// members.txt에서 id, name, phoneNumber 정보를 UserInfo에 저장하기
				        	extractMembers("members.txt", idTf.getText());
				        	
				        	// membersReservation.txt에 저장된 id가 있는 지 확인해서 있으면 예약정보 저장
				        	extractMembersReservation("membersReservation.txt", idTf.getText());        	
				        	
				            // 입력한 비밀번호가 member.txt에 존재하는 해싱 비밀번호와 일치할 때
				            JOptionPane.showMessageDialog(null, "로그인 성공!");
				            setVisible(false);
				            idTf.setText("");
				            pwPf.setText("");
				            // ReservationPanel 화면으로 넘어감
				            new ReservationPanel(userInfo);				            
				            
				        } else {
				            // 입력한 비밀번호가 member.txt에 존재하는 id에 맞는 비밀번호와 일치하지 않을 때
				            JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				        }
				    }
				} else {
					// 존재하지 않는 아이디일 때
					JOptionPane.showMessageDialog(null, "존재하지 않는 id입니다.");		
				}	
			}						
		});        
    }
    
    public void showFrame() {
        setTitle("로그인");
        setSize(1400,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
    }
    
    public UserInfo extractMembers (String fileName, String id) {
    	FileReader reader = null;
    	BufferedReader br = null;
    	
    	try {
			reader = new FileReader(fileName);
    		br = new BufferedReader(reader);
    		String line;
			
			while ((line = br.readLine()) != null) {
				if (line.contains("아이디: " + id + ",")) {
					String name = null;
					String number = null;
					String[] texts = line.split(", ");
					
	                for (String text : texts) {
	                    if (text.startsWith("이름: ")) {
	                        name = text.substring("이름: ".length());
	                    } else if (text.startsWith("전화번호: ")) {
	                        number = text.substring("전화번호: ".length());
	                    }
	                }
	                
	                if (name != null) {
	                	userInfo = new UserInfo (id, name, number);
	                }
	                break;
				}
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    	return userInfo;
    }
    
    public void extractMembersReservation (String fileName, String id) {
    	FileReader reader = null;
    	BufferedReader br = null;
    	
    	try {
    		reader = new FileReader(fileName);
    		br = new BufferedReader(reader);
    		
    		
    		String line;
			while ((line = br.readLine()) != null) {
				if (line.contains("아이디: " + id + ",")) {
					String airline = null;
					String airplane = null;
					String destination = null;
					String date = null;
					String[] texts = line.split(", ");
					int seatCount = texts.length - 7;
					int seatIndex = 0;
					String[] seats = new String[seatCount];
					
	                for (int i = 3; i < texts.length; i++) {
	                    if (texts[i].startsWith("항공사: ")) {
	                    	airline = texts[i].substring("항공사: ".length());
	                    } else if (texts[i].startsWith("항공편: ")) {
	                    	airplane = texts[i].substring("항공편: ".length());
	                    } else if (texts[i].startsWith("도착지: ")) {
	                    	destination = texts[i].substring("도착지: ".length());
	                    } else if (texts[i].startsWith("날짜: ")) {
	                    	date = texts[i].substring("날짜: ".length());
	                    } else if (texts[i].startsWith("좌석 번호: ")) {
	                    	seats[0] = texts[i].substring("좌석 번호: [".length());
	                    	
	                    	if(seatCount == 1) {
	                    		seats[0] = seats[0].substring(0, 2);
	                    	}
	                    	
	                    } else {
	                    	if (seatIndex < seatCount) {
	                    		if(texts[i].contains("]")) {
	                    			texts[i] = texts[i].substring(0, 2);
	                    		}
	                    		seats[++seatIndex] = texts[i];
	                    	}
	                    }
	                }
	                
	                if (airline != null) {
	                	userInfo.setAirline(airline);
	                	userInfo.setAirplane(airplane);
	                	userInfo.setDestination(destination);
	                	userInfo.setDate(date);
	                	userInfo.setSeats(seats);
	                }  
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (br != null) {
				br.close();
				}
				if(reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
    }
}

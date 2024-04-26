package ex999_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

    private UserData users;		// 임시 유저데이터 --> 용현님 txt파일에서 읽어오는걸로 수정 예정
    
    public LoginForm() {
    	
    	users = new UserData(); // 임시 유저데이터 --> 수정 예정
    	
		init();
		setDisplay();
		addListeners();
	    showFrame();
	}
    
    private UserData getUsers() {	 // 임시 유저데이터 --> 수정 예정
		return users;
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
        

        logoImg = new ImageIcon("src/img/t3.png");
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

    public void addListeners() {

        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
					
				} else if (users.userExist(new User(idTf.getText()))) {		// 수정필요 --> txt파일 읽어서 확인
					// 존재하는 아이디일 때
										
					if (idTf.getText().length() > 0) {
						// 비밀번호가 비었을 때
						String pw = new String(pwPf.getPassword());
						if (pw.isEmpty()) {
							JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
							}
					} else if (!users.getUser(idTf.getText()).getPw().equals(String.valueOf(pwPf.getPassword()))) {
						// 비밀번호가 일치하지 않을 때
						//	유저관리 클래스에서 비밀번호 값 가져와서 확인		// 수정필요 --> txt파일 읽어서 확인
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
						
					} else {						
						// 완료되었을 때 
						//	항공권 조회 화면으로 넘어감
						
						//	조회클래스명 변수 = new 조회클래스(LoginForm.this);				// 수정필요 --> txt파일 읽어서 확인
						//	변수.setTaCheck(유저클래스명.getUser(idTf.getText()).toString());
						JOptionPane.showMessageDialog(null, "로그인 성공!");
						setVisible(false);
						idTf.setText("");
						pwPf.setText("");
						
					}
				} else {
					// 존재하지 않는 아이디일 때
					JOptionPane.showMessageDialog(null, "존재하지 않는 id입니다.");
				
				}
			
			}						
		});
               
    }
    
    public void showFrame() {
        setTitle("Login");
        setSize(1400,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
    
	
}


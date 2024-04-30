package ex999_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Join extends JFrame {

    private JTextField nameTf; // 이름 입력 필드
    private JTextField idTf; // 아이디 입력 필드
    private JPasswordField passwordPf; // 비밀번호 입력 필드
    private JComboBox<String> phoneCb;
    private JTextField phoneTf;
    private JButton cancelBtn;	// 취소 버튼
    private LoginForm loginForm;	// 로그인 화면으로 넘어가기 위한 인스턴스 변수

    public Join(LoginForm loginForm) {
    	this.loginForm = loginForm;
        initComponents();
        setLayoutAndListeners();
        showFrame();
    }

    private void initComponents() {
        // 컴포넌트 초기화 및 생성
        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS)); // 수직으로 정렬하기 위해 Y_AXIS 사용

        JPanel jpId = new JPanel();
        JPanel jpPw = new JPanel();
        JPanel jpName = new JPanel();
        JPanel jpPhone = new JPanel();
        JPanel totalBtn = new JPanel();

        JLabel nameLb = new JLabel("이름: ");
        nameLb.setPreferredSize(new Dimension(80, 20)); // 라벨의 크기 설정
        nameTf = new JTextField(20); // 이름 입력 필드 초기화

        JLabel idLb = new JLabel("아이디: ");
        idLb.setPreferredSize(new Dimension(80, 20)); // 라벨의 크기 설정
        idTf = new JTextField(20); // 아이디 입력 필드 초기화

        JLabel passwordLb = new JLabel("비밀번호: ");
        passwordLb.setPreferredSize(new Dimension(80, 20)); // 라벨의 크기 설정
        passwordPf = new JPasswordField(20); // 비밀번호 입력 필드 초기화

        JLabel phoneLb = new JLabel("전화번호:");
        phoneLb.setPreferredSize(new Dimension(80, 20)); // 라벨의 크기 설정
        String[] phone = {"010","02","031","032","051","052"};
        phoneCb = new JComboBox<String>(phone);
        phoneTf = new JTextField(15); // 전화번호 입력 필드 초기화
        

        JButton registerBtn = new JButton("가입하기");
        cancelBtn = new JButton("취소");

        jpId.add(idLb);
        jpId.add(idTf);

        jpPw.add(passwordLb);
        jpPw.add(passwordPf);

        jpName.add(nameLb);
        jpName.add(nameTf);

        jpPhone.add(phoneLb);
        jpPhone.add(phoneCb);
        jpPhone.add(phoneTf);
        

        totalBtn.add(registerBtn);
        totalBtn.add(cancelBtn);

        jpMain.add(Box.createVerticalGlue()); // 상단에 공간 추가
        jpMain.add(Box.createVerticalGlue());
        jpMain.add(Box.createVerticalGlue());
        jpMain.add(jpName);
        jpMain.add(jpId);
        jpMain.add(jpPw);
        jpMain.add(jpPhone);
        jpMain.add(Box.createVerticalGlue());// 하단에 공간 추가
        jpMain.add(Box.createVerticalGlue());
        jpMain.add(Box.createVerticalGlue());

        jpMain.add(totalBtn);

        totalBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 중앙 정렬
        jpName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpId.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPw.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpPhone.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.addActionListener(btnAl);
        cancelBtn.addActionListener(btnAl);

        add(jpMain);
    }

    private void setLayoutAndListeners() {
        // 레이아웃 설정 및 리스너 등록
    	
    	// 취소버튼 클릭시 로그인 화면으로 이동
    	cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cancelBtn 클릭 시 LoginForm 화면으로 이동
                setVisible(false); // Join2 화면 숨기기
                loginForm.setVisible(true); // LoginForm 화면 보이기
            }
        });
    }

    private void showFrame() {
        setTitle("회원 가입");
        setSize(400, 400);
        setLocationRelativeTo(null); // 화면 중앙에 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    ActionListener btnAl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameTf.getText();
            String id = idTf.getText();
            String password = new String(passwordPf.getPassword());
            String phoneNumber = phoneCb.getSelectedItem().toString() + phoneTf.getText();
           
            switch (e.getActionCommand()) {
                case "가입하기":
                    if (!isValidInput(name, id, password)) {
                        JOptionPane.showMessageDialog(null, "입력이 유효하지 않습니다!");
                        return;
                    }
                    if (isIdDuplicated(id)) {
                        JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다!");
                        return;
                    }
                    String hashedPassword = hashPassword(password);
                    Member member = new Member(name, id, hashedPassword, phoneNumber);
                    saveMemberToFile(member);
                    JOptionPane.showMessageDialog(null, "가입 완료!");
                    setVisible(false);
                    loginForm.setVisible(true);
                    break;
                case "취소":
                    // 취소 버튼 동작 추가
                    break;
            }
        }
    };

    private static void saveMemberToFile(Member member) {
        // 파일에 회원 정보 저장
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("members.txt", true))) {
            bw.write(member.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Member {
        private String name;
        private String id;
        private String password;
        private String phoneNumber;

        public Member(String name, String id, String password, String phoneNumber) {
            this.name = name;
            this.id = id;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "이름: " + name + ", 아이디: " + id + ", 비밀번호: " + password + ", 전화번호: "+ phoneNumber;
        }
    }

    private static boolean isIdDuplicated(String id) {
        // 아이디 중복 체크
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

    private static boolean isValidInput(String name, String id, String password) {
        // 입력값 유효성 검사
        if (name.isEmpty() || id.isEmpty() || password.isEmpty()) {
            return false;
        }

        if (!id.matches("[a-zA-Z][a-zA-Z0-9]*")) {
            return false;
        }
        return true;
    }

    private static String hashPassword(String password) {
        // 비밀번호 해싱
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

        

package ex999_test;


public class Memo {
	
	
//	GUI 수업내용 총정리
		
	// swing 사용시 해당 프로젝트 내에 module-info.java 삭제
	
	
	// 이벤트 클래스		리스너 인터페이스		메서드 (이벤트 핸들러)
	// --------------------------------------------------------------
	// ActionEvent		ActionListener		actionPerformed(ActionEvent e)
	// ChangeEvent		ChangeListener		stateChanged(ChangeEvent e)
	// ItemEvent		ItemListener		itemStateChanged(ItemEvent e)
	// KeyEvent			KeyListener			KeyPressed(KeyEvent e)
	//										KeyReleased(KeyEvent e)
	//										KeyTyped(KeyEvent e)
	// ListSelectionEvent ListSelectionListener valueChanged(ListSelectionEvnet e)
	// MouseEvent		MouseListener		mouseClicked(MouseEvent e)
	//										mouseEntered(MouseEvent e)
	//										mouseExited(MouseEvent e)
	//										mousePressed(MouseEvent e)
	//										mouseReleased(MouseEvent e)
	//										mouseReleased(MouseEvent e)
	//					MouseMotionListener mouseDragged(MouseEvent e)
	//										mouseMoved(MouseEvent e)
	// WindowEvent		WindowListener		windowActivated(WindowEvent e)
	//										windowClosed(WindowEvent e)
	//										windowClosing(WindowEvent e)
	//										windowClosing(WindowEvent e)
	//										windowDeactivated(WindowEvent e)		
	
	
	// ActionListener
	//	버튼을 클릭하거나 콤보박스에서 특정 아이템을 선택한 경우에 발생
	//	버튼을 클릭하면 ActionEvent가 발생

	// ItemListener
	//	체크박스, 라디오버튼, 리스트의 항목, 메뉴의 항목이 선택되었거나 해제될 때 발생
	//	ItemListener는 ‘선택 상태’를 나타냄
	//	itemStateChanged(ItemEvent e) : 상태가 변했을때 호출되는 메서드 
	//	ㄴ e.getStateChange() : 선택되면 1, 해제되면 2 반환
	

	//	JFram()	: 보이지 않는 JFrame 생성
	//	JFrame(String title)	: 제목을 가지는 보이지 않는 JFrame 생성
	
	// JFram 메서드
	//	setSize(int width, int height)	: 프레임의 크기 지정
	//	setLocation(int x, int y) 	: 프레임이 보여질 좌표 지정
	//	setBounds(x,y,width,height) : 좌표, 너비, 높이 지정
	//	setVisible(boolean value)	: 화면에 표시여부 결정
	//	void add(Component c) : 만든 요소를 프레임에 붙힌다.
	//	setLayout() : 프레임 안에 요소를 어떻게 배치할 것인가
	//  FlowLayout() : 왼쪽부터 오른쪽으로 배치한다
	//	setDefaultCloseOperation(int operation) : 사용자가 JFrame을 닫을 때 기본적으로 어떤 작업을 할지 결정
	//	ㄴ EXIT_ON_CLOSE : 닫기 단추를 누르면 창을 화면에서 사라지게 하고, 메모리에서도 제거
	//	ㄴ DO_NOTHING_ON_CLOSE : 닫기 단추를 비활성화
	
	
	// 배치관리자
	//	FlowLayout : 왼쪽에서 오른쪽으로 배치하고, 오른쪽에 공간이 없으면 아래로 배치
	//	BorderLayout : 동서남북,중앙 5개의 영역으로 나눔
	//	GridLayout : 2차원 표 모양으로서 n x n 으로 설정해주면 왼>오>위>아래로 배치
	//	CardLayout : 컴포넌트를 포개어 배치 
	// 	Null : 레이아웃을 사용하지 않고 수동으로 배치
	
	
	// JPanel() : 레이아웃이 FlowLayout인 JPanel을 생성
	// JPanel(LayoutManager layout) : 레이아웃 매니저가 layout인 JPanel 생성
	
	// JPanel 메서드
	//	add()	: 컴포넌트를 패널에 추가
	//	remove() : 컴포넌트를 패널에서 제거
	//	setBounds() : 패널의 좌표와 크기를 설정
	//	setLayout() : 패널의 배치관리자를 설정
	
	
	// JButton 메서드
	//	setSzie(int width, int height) : 크기 설정
	//	setLocation(int Left, int Top) : 위치 설정
	//	setBounds(Left, Top, Width, Height) : 위치, 크기 한번에 설정
	//	setHorizontalAlignment(int) : 위치, 크기 한번에 설정
	
	
	// JLabel
	//	텍스트를 위한 라벨 생성	
	//	문자열이나 아이콘을 사용하여 객체 생성
	
		
	// JLabel 메서드
	//	setText(String s)
	//	getText(String s)
	
	
	// JTextField
	//	한 줄에 문자열을 입력할 수 있는 컴포넌트 
	
	// TextField 메서드
	//	setEditable(boolean b) : 수정 가능/불가능 설정	
	
	
	// JTextArea
	//	여러 줄을 입력할 수 있는 컴포넌트
	
	
	// JPasswordField
	//	비밀번호와 같이 입력받은 글자를 보여주지 않아야 할 때 사용
	
	
	// JCheckBox
	//	중복 체크 가능
	
	
	// JRadioButton
	//	중복 체크 불가능
	
	
	// JComboBox
	//	드롭박스
	
	
	// JTable 
	//	데이터를 테이블 형태인 행과 열로 나타내고자 할 때 사용한다.
	
	
	// JScrollPane
	//	필요에 따라 패널에 수평이나 수직 스크롤바를 설정
	

	// JMenuBar
	//	메뉴바 생성	
	//  메뉴를 프레임에 붙일땐 add() 말고 setJMenuBar() 사용
	
	// JMenu
	//	메뉴바에 들어갈 메뉴
	
	// JMenuItem
	//	JMenu에 들어갈 메뉴
	
	
	// 어댑터 : 리스너가 가지고 있는 메서드 중 필요한 것만 재정의하여 사용
	// KeyListener -> KeyAdapter
	// MouseListenr -> MouseAdapter, MouseMotionAdapter
	// WindowListener -> WindowAdapter
	
	
	// ImageIcon
	// 	이미지 불러오기 (이미지 담을 폴더 생성 후 작업)
	//	ImageIcon 변수명 = new ImageIcon("src/img/bunny.jpeg");
	
	
	
	

}

package ex999_test;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


// 공통으로 사용하는 메뉴바
// 다른 클래스에서 사용시 //menuBar.commonMenuBar(); 호출
public class MenuBar {	
	
	JMenuBar jmenu;
	JMenuItem reser;
	JMenuItem logout;
	
	public MenuBar () {
		jmenu = commonMenuBar();
	}
	
	public JMenuBar commonMenuBar() {
		
		// 상단 메뉴바 - 예매내역, 로그아웃
		jmenu = new JMenuBar();
		reser = new JMenuItem("예매내역");
    	logout = new JMenuItem("로그아웃");
    	jmenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    	
    	jmenu.add(reser);
    	jmenu.add(logout);
    	
    	return jmenu;
	}	
	
	public JMenuBar getMenuBar () {
		return jmenu;
	}
	
}

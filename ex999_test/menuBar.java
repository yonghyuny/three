package ex999_test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


// 공통으로 사용하는 메뉴바
// 다른 클래스에서 사용시 //menuBar.commonMenuBar(); 호출
public class menuBar {	
	public static JMenuBar commonMenuBar() {
		
		// 상단 메뉴바 - 예매내역, 로그아웃
		JMenuBar jmenu = new JMenuBar();
    	JMenuItem jm1 = new JMenuItem("예매내역");
    	JMenuItem jm2 = new JMenuItem("로그아웃");
    	jmenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    	
    	jmenu.add(jm1);
    	jmenu.add(jm2);
    	
    	return jmenu;
	}	
	
}

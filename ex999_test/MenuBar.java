package ex999_test;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


// 공통으로 사용하는 메뉴바
// 다른 클래스에서 사용시 //menuBar.commonMenuBar(); 호출
public class MenuBar {
    private JMenuBar jmenu;

    public MenuBar() {
        // 상단 메뉴바 - 예매내역, 로그아웃
        jmenu = new JMenuBar();

        JMenuItem jm1 = new JMenuItem("예매내역");
        JMenuItem jm2 = new JMenuItem("로그아웃");

        jmenu.add(jm1);
        jmenu.add(jm2);
        
        jmenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
    }
    
    public JMenuBar getMenuBar() {
        return jmenu;
    }
}

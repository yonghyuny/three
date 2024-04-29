package test_jframe;

import javax.swing.*;

public class JFrameTest extends JFrame{
	
	public JFrameTest () {
		super("Test");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
}

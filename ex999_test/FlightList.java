package ex999_test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

//import reservation.ReservationInfo;



public class FlightList {
	
	UserInfo userInfo;
	ReservationInfo tempReser;
	JLabel destinationLabel;
	JLabel dateLabel;
	
	public FlightList() { }
	
	public FlightList(UserInfo userInfo, ReservationInfo tempReser) {
		
		this.userInfo = userInfo;
		this.tempReser = tempReser;
		String date = tempReser.getDate();
		String destination = tempReser.getDestination();
		
		JFrame f = new JFrame("항공권 리스트"); // 제목
        f.setLayout(new BorderLayout());
        
        
      //메뉴바
        JMenuBar jmb = new JMenuBar();
        JMenuItem reser = new JMenuItem("예매내역");
        JMenuItem logout = new JMenuItem("로그아웃");
        jmb.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
        
        jmb.add(reser);
        jmb.add(logout);
        
        
        
        //출발지 도착지 날짜 정보 패널
        JPanel infoPanel = new JPanel();
        JLabel departLb = new JLabel("출발지");
        JLabel lb1 = new JLabel("인천"); 
        
        destinationLabel = new JLabel("도착지 : " + destination);
        dateLabel = new JLabel("날짜 : " + date + "확인용 : " + userInfo.getName() + userInfo.getNumber());
        
        infoPanel.add(departLb);
        infoPanel.add(lb1);
        infoPanel.add(destinationLabel);
        infoPanel.add(dateLabel);
        f.add(infoPanel, BorderLayout.NORTH);
        
                
      //항공명 
        
        
        String[] title = {"항공사명", "항공명",  "출발시간", "도착시간", "소요시간",};  
        String[][] data = {
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"실험","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어","KAL-91", "06:45", "09:20", "직항, 02시간 35분",},
           };
        
        
        DefaultTableModel model = new DefaultTableModel(data, title);
        JTable table = new JTable(model);
        table.setRowHeight(50);
        
        
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(Box.createHorizontalStrut(200), BorderLayout.EAST);
        jp.add(Box.createHorizontalStrut(200), BorderLayout.WEST);
        
        
        
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row  = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    int option = JOptionPane.showConfirmDialog(table, "구매하시겠습니까?", "구매 확인", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // "예"를 클릭한 경우
                        String rowData = ""; // 클릭한 행의 정보를 저장할 문자열
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            rowData += table.getValueAt(row, i) + " "; // 각 열의 데이터를 연결
                        }
                        JOptionPane.showMessageDialog(table, "구매 정보: " + rowData);
                        
                        // 항공사, 항공명 정보 ReservationInfo에 저장하기
                        tempReser.setAirline(data[row][0]);
                        tempReser.setAirplane(data[row][1]);
                        
                        
                        new Seat(userInfo, tempReser);
                		f.setVisible(false);
                        
                    } else if (option == JOptionPane.NO_OPTION) {
                        // "아니오"를 클릭한 경우
                        JOptionPane.showMessageDialog(table, "구매가 취소되었습니다.");
                    }
                }
            }
        });

        // 수직 및 수평 스크롤바 설정
        JScrollPane js = new JScrollPane(table);
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jp.add(js);
       
        
        
        //테이블 내용 가운데 정렬
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = table.getColumnModel();
        
        for(int i = 0; i < tcm.getColumnCount(); i++) {
        	tcm.getColumn(i).setCellRenderer(dtcr);
        }
        
        f.setJMenuBar(jmb);
        f.add(jp, BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1600,800);
        f.setVisible(true);
        
        // 예매내역 버튼 클릭 시 예매내역 페이지로 이동
        reser.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// 예매내역 창 호출
				new ReservationDetails(userInfo);
        		f.setVisible(false);
        	}
        });
        
        // 로그아웃 버튼 클릭 시 로그아웃 되고 로그인 페이지로 이동
        logout.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		// 로그인 창 호출
        		logout();
        		new LoginForm();
        		f.setVisible(false);
        	}
        });

	}
	
	public void logout() {
		userInfo = null;
	}
}
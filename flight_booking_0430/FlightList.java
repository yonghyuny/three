package flight;

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

import reservation.ReservationInfo;



public class FlightList {
	
	ReservationInfo tempReser;
	JLabel destinationLabel;
	JLabel dateLabel;
	
	public FlightList() { }
	
	public FlightList(ReservationInfo tempReser) {
		
		this.tempReser = tempReser;
		String date = tempReser.getDate();
		String destination = tempReser.getDestination();
		
		JFrame f = new JFrame("항공권 리스트"); // 제목
        f.setLayout(new BorderLayout());
        
        
      //메뉴바
        JMenuBar jmb = new JMenuBar();
        JMenuItem jm1 = new JMenuItem("예매내역");
        JMenuItem jm2 = new JMenuItem("로그아웃");
        jmb.setLayout(new FlowLayout(FlowLayout.RIGHT, 12, 5));
        
        jmb.add(jm1);
        jmb.add(jm2);
        
        jm2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
        //출발지 도착지 날짜 정보 패널
        JPanel infoPanel = new JPanel();
        JLabel departLb = new JLabel("출발지");
        JLabel lb1 = new JLabel("인천"); 
        
        destinationLabel = new JLabel("도착지 : " + destination);
        dateLabel = new JLabel("날짜 : " + date);
//        JLabel destiLb = new JLabel("도착지");
//        JLabel lb2 = new JLabel(destination);
//        JLabel timeLb = new JLabel("날짜");
//        JLabel lb3 = new JLabel(date);
        
        infoPanel.add(departLb);
        infoPanel.add(lb1);
        infoPanel.add(destinationLabel);
//        infoPanel.add(lb2);
        infoPanel.add(dateLabel);
//        infoPanel.add(lb3);
        f.add(infoPanel, BorderLayout.NORTH);
        
                
      //항공명 
        
        
        String[] title = {"항공사명", "출발시간", "도착시간", "소요시간",};  
        String[][] data = {
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"아시아나", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",},
            {"진에어", "06:45", "09:20", "직항, 02시간 35분",}
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
	}
	
    public ReservationInfo getReservationInfo() {
        return tempReser;
    }
	
	
//	public static void main(String[] args) {
//       
//        
//        FlightList flightList = new FlightList();
//
//    }
}
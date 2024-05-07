package flight_booking;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


@SuppressWarnings("serial")
public class FlightList extends JFrame{	
	UserInfo userInfo;
	ReservationInfo tempReser;
	JLabel destinationLabel;
	JLabel dateLabel;
	JPanel jp;
	MenuBar jmenu;
	
	String date;
	String destination;
	
	public FlightList() { }
	
	public FlightList(UserInfo userInfo, ReservationInfo tempReser) {
		
		this.userInfo = userInfo;
		this.tempReser = tempReser;
		date = tempReser.getDate();
		destination = tempReser.getDestination();
        
        //상단 메뉴바
        menuBar();
        
        // 항공 리스트
        fligtList ();
        
        showFrame();

	}
	
    // 상단 메뉴바
    public void menuBar() {
    	jmenu = new MenuBar();	

    	 // 로그아웃
    	jmenu.logout.addActionListener(new LogoutActionListener(userInfo, this));
        
    	// 예매내역
    	jmenu.reser.addActionListener(new ReservationDetailsActionListener(userInfo, this));

    }
    
    public void fligtList () {
    	//출발지 도착지 날짜 정보 패널
        JPanel infoPanel = new JPanel();
        JLabel departLb = new JLabel("출발지: ");
        JLabel lb1 = new JLabel("인천"+" "); 
        
        destinationLabel = new JLabel("도착지 : " + destination+ " ");
        dateLabel = new JLabel("날짜 : " + date);
        
        departLb.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        lb1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        destinationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        dateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        
        infoPanel.add(Box.createVerticalStrut(200));
        infoPanel.add(departLb);
        infoPanel.add(lb1);
        infoPanel.add(destinationLabel);
        infoPanel.add(dateLabel);
        add(infoPanel, BorderLayout.NORTH);
        
                
        //항공명 
        String[] title = {"항공사명", "항공명",  "출발시간", "도착시간", "소요시간",};  
        String[][] data = {
            {"진에어", "JIN058" ,"06:45", "09:20", "직항,02시간35분"},
            {"이스타항공", "JIN058" ,"07:05", "09:50", "직항,02시간45분"},
            {"진에어", "JIN871" ,"07:25", "10:00", "직항,02시간35분"},
            {"티웨이항공", "TWA873" ,"07:45", "10:15", "직항,02시간30분"},
            {"에어서울", "SEO037" ,"07:50", "10:30", "직항,02시간40분"},
            {"아시아나항공", "ANA758" ,"09:00", "11:20", "직항,02시간20분"},
            {"진에어", "JIN657" ,"09:45", "12:15", "직항,02시간30분"},
            {"이스타항공", "EST187" ,"15:10", "17:30", "직항,02시간20분"},
            {"에어부산", "BS848" ,"15:50", "18:10", "직항,02시간20분"},
            {"대한항공", "KAL123" ,"16:25", "18:45", "직항,02시간20분"},
            {"대한항공", "KAL418" ,"16:45", "19:15", "직항,02시간30분"},
            {"이스타항공", "EST157" ,"17:25", "19:45", "직항,02시간20분"},
            {"티웨이항공", "TWA788" ,"18:35", "21:05", "직항,02시간30분"},
            {"에어서울", "SEO205" ,"20:30", "22:50", "직항,02시간35분"},
            {"에어부산", "BS177" ,"21:35", "23:55", "직항,02시간20분"},      
           };
        
        
        DefaultTableModel model = new DefaultTableModel(data, title);
        JTable table = new JTable(model);
        table.setRowHeight(50);
        
        //테이블 양옆에 여백
        jp = new JPanel(new BorderLayout());
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
                		setVisible(false);
                        
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
    }
    
    public void showFrame () {
        setJMenuBar(jmenu.getMenuBar());
        add(jp, BorderLayout.CENTER);
		setLocation(getX( )+ 250, getY() + 110);
        setSize(1600,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
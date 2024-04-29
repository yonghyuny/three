package reservation;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ReservationCalender extends JFrame{
	
	public ReservationCalender () {
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		add(datePicker);
		setBounds(300,300,400,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ReservationCalender();
	}
		
	
}

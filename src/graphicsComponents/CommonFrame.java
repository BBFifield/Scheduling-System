package graphicsComponents;

import java.awt.Component;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import main.SpaceSystem;
import main.UserValidator;
import schedule.Booking;
import schedule.Room;
import schedule.Month;
import users.User;


public abstract class CommonFrame extends JFrame {

	
	public CommonFrame() {
	}
	
	public void initializeList(JList list, Collection values) {
		clearList(list);
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		for(Object value: values) {
			listModel.addElement(value);
		}
	}
	
	public abstract void setUserLabel(String userLabel);
	
	public void clearList(JList list) {
		DefaultListModel listModel = (DefaultListModel) list.getModel();
		listModel.removeAllElements();
	}
	
	public void logout() {
		LoginPage login = new LoginPage();
		login.setVisible(true);
		this.dispose();
	}
	
	public abstract Component returnComponent(int componentNum);
}


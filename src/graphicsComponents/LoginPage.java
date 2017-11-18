package graphicsComponents;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Action;
import main.SpaceSystem;

public class LoginPage {
	JFrame frmLoginToSpace;
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton btnLogin;
	private SpaceSystem system;
	/**
	 * @wbp.parser.entryPoint
	 */
	public LoginPage(SpaceSystem system) {
		this.system = system;
		frmLoginToSpace = new JFrame();
		frmLoginToSpace.setTitle("Login to Space System");
		frmLoginToSpace.getContentPane().setLayout(null);
		
		userNameField = new JTextField();
		userNameField.setBounds(117, 71, 161, 20);
		frmLoginToSpace.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 127, 161, 20);
		frmLoginToSpace.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(170, 46, 108, 14);
		frmLoginToSpace.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 102, 46, 14);
		frmLoginToSpace.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(150, 158, 89, 23);
		frmLoginToSpace.getContentPane().add(btnLogin);
	}
	
	public boolean validate(String userName, String password) {
		File usersFile = new File("../resources/users.txt");
		try {
			Scanner in = new Scanner(usersFile);
			String line;
			String userNameCurrent = "";
			String passwordCurrent = "";
			while(!in.hasNextLine()) {
				line = in.nextLine();
				String columnCurrent;
				for(int i = 0; i < 3; i++) {
					columnCurrent = in.next();
					if(i == 1) {
						userNameCurrent = columnCurrent;
					}
					else if(i == 2) {
						passwordCurrent = columnCurrent;
					}
				}
				if(userNameCurrent.equals(userName) && passwordCurrent.equals(password)) {
					return true;
				}
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogin) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				if(userName.isEmpty()) {
					JOptionPane.showMessageDialog(frmLoginToSpace, "Please provide a username");
				}
				else {
					if(validate(userName, password)) {
						MainFrame window = new MainFrame();
						window.addSystem(system);
						system.addGui(window);
						window.frame.setVisible(true);
					}
				}
			}
			
		}
	}
	
}

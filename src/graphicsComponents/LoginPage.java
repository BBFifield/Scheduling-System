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

public class LoginPage extends JFrame {
	
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton btnLogin;
	private SpaceSystem system;
	/**
	 * @wbp.parser.entryPoint
	 */
	public LoginPage(SpaceSystem system) {
		this.system = system;
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login to Space System");
		this.getContentPane().setLayout(null);
		
		userNameField = new JTextField();
		userNameField.setBounds(117, 71, 161, 20);
		this.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 127, 161, 20);
		this.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(170, 46, 108, 14);
		this.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 102, 46, 14);
		this.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new LoginListener());
		btnLogin.setBounds(150, 158, 89, 23);
		this.getContentPane().add(btnLogin);
	}
	
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogin) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				System.out.println(userName);
				if(system.validate(userName, password)) {
					MainFrame window = new MainFrame();
					window.addSystem(system);
					system.addGui(window);
					window.setVisible(true);
					LoginPage.this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(LoginPage.this, "Wrong username or password entered");
				}
			}
		}
	}
}

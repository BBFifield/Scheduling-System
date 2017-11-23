package graphicsComponents;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import main.SpaceSystem;
import main.UserValidator;
import schedule.Room;

public class LoginPage extends JFrame {
	
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton btnLogin;
	private SpaceSystem system;
	/**
	 * @wbp.parser.entryPoint
	 */
	public LoginPage() {
		this.system = system;
		initialize();
	}
	
	public void initialize() {
		this.setBounds(450,100, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Login to Space System");
		this.setLayout(null);
		
		userNameField = new JTextField();
		userNameField.setBounds(117, 71, 161, 20);
		this.add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 127, 161, 20);
		this.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(170, 46, 108, 14);
		this.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 102, 108, 14);
		this.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new LoginListener());
		btnLogin.setBounds(150, 158, 89, 23);
		this.add(btnLogin);
	}
	
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogin) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				try {
					if(UserValidator.validate(userName, password)) {
						system = new SpaceSystem();
						MainFrame window = new MainFrame(system);
						window.setUserLabel("Welcome " + system.searchUser(UserValidator.userLoggedIn).getName() + "!");
						system.addGui(window);
						window.setVisible(true);
						LoginPage.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(LoginPage.this, "Wrong username or password entered");
					}
				}
				catch(FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

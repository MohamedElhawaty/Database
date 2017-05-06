package View;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {
	public Login() {
		getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(170, 55, 114, 19);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(170, 110, 114, 19);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(167, 160, 117, 25);
		getContentPane().add(loginButton);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField passwordField;
}

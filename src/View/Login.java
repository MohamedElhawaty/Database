package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controller;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

public class Login extends JFrame implements WindowListener{
	
	private JFrame frame = this;
	
	public Login() {
		getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(75, 55, 246, 19);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(75, 85, 246, 19);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(75, 142, 117, 25);
		getContentPane().add(loginButton);
		loginButton.addActionListener(new loginAction());
		
		signupButton = new JButton("Sign up");
		signupButton.setBounds(204, 142, 117, 25);
		getContentPane().add(signupButton);
		signupButton.addActionListener(new signupAction());
		
		controller =  Controller.getInstance();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField passwordField;
	private JButton signupButton;
	
	private Controller controller;
	
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	
	private class loginAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			User user = controller.login(nameField.getText().toLowerCase(), passwordField.getText());
			if(user != null){
				MainView mainView = new MainView(user);
				mainView.setVisible(true);
				frame.setVisible(false);
			}
		}
		
	}
	
	private class signupAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserView userView = new UserView(new User(), true);
			userView.setVisible(true);
			frame.setVisible(false);
		}
		
	}
	
	public static void showError(String message){
		JOptionPane.showMessageDialog(null, message , "Error", JOptionPane.ERROR_MESSAGE);
	}
}

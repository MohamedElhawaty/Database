package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.Controller;
import model.User;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UserView extends JFrame implements WindowListener{
	private JTextField userNameField;
	private JTextField passwordField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextField addressField;
	
	private JButton saveButton;
	private Controller controller;
	private User user;
	private boolean signup;
	
	public UserView(User user, boolean signup) {
		
		this.user = user;
		this.signup = signup;
		//controller =  Controller.getInstance();
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel userNameLabel = new JLabel("User Name: ");
		getContentPane().add(userNameLabel, "4, 4");
		
		userNameField = new JTextField();
		getContentPane().add(userNameField, "8, 4, fill, default");
		userNameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password: ");
		getContentPane().add(passwordLabel, "4, 6");
		
		passwordField = new JTextField();
		getContentPane().add(passwordField, "8, 6, fill, default");
		passwordField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		getContentPane().add(firstNameLabel, "4, 8");
		
		firstNameField = new JTextField();
		getContentPane().add(firstNameField, "8, 8, fill, default");
		firstNameField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		getContentPane().add(lastNameLabel, "4, 10");
		
		lastNameField = new JTextField();
		getContentPane().add(lastNameField, "8, 10, fill, default");
		lastNameField.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email: ");
		getContentPane().add(emailLabel, "4, 12");
		
		emailField = new JTextField();
		getContentPane().add(emailField, "8, 12, fill, default");
		emailField.setColumns(10);
		
		JLabel phoneLabel = new JLabel("Phone: ");
		getContentPane().add(phoneLabel, "4, 14");
		
		phoneField = new JTextField();
		getContentPane().add(phoneField, "8, 14, fill, default");
		phoneField.setColumns(10);
		
		JLabel addressLabel = new JLabel("Address: ");
		getContentPane().add(addressLabel, "4, 16");
		
		addressField = new JTextField();
		getContentPane().add(addressField, "8, 16, fill, default");
		addressField.setColumns(10);
		
		
		saveButton = new JButton("Save");
		saveButton.setBounds(200, 200, 200, 200);
		getContentPane().add(saveButton);
		saveButton.addActionListener(new saveAction());
		
		
		if(!signup){
			update(user);
		}
	}
	

	
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
	
	private void update(User user){
		userNameField.setText(user.getUserName());
		passwordField.setText(user.getPassword());
		firstNameField.setText(user.getFirstName());
		lastNameField.setText(user.getLastName());
		emailField.setText(user.getEmail());
		phoneField.setText(user.getPhoneNumber());
		addressField.setText(user.getShippingAddress());
	}
	
	private class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			User temp = new User();
			temp.setUserName(userNameField.getText().toLowerCase());
			temp.setPassword(passwordField.getText());
			temp.setFirstName(firstNameField.getText().toLowerCase());
			temp.setLastName(lastNameField.getText().toLowerCase());
			temp.setEmail(emailField.getText().toLowerCase());
			temp.setPhoneNumber(phoneField.getText().toLowerCase());
			temp.setShippingAddress(addressField.getText().toLowerCase());
			if(signup){
				if(controller.signup(temp)){
					user = temp;
				}
				update(user);
				// if done show message success or failed
			}else{
				/// i need editInformation to return something to indicate if finish success or not
				if(controller.editInformation(temp)){
					user = temp;
				}
				update(user);
				// if done show message success or failed
				// if faild update by user else update by temp
			}
		}
		
	}
}

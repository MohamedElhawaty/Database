package View;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.CardLayout;
import javax.swing.JTable;

public class MainView extends JFrame {
	private JTextField textField;
	private JTable table;
	public MainView() {
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 5, 32, 24);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(88, 8, 114, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(214, 5, 117, 25);
		getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(30, 285, 302, -223);
		getContentPane().add(table);
	}
}

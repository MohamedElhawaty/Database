package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.Controller;
import model.Book;

public class BookView extends JFrame implements WindowListener{
	private JTextField ISBNField;
	private JTextField titleField;
	private JTextField publisherField;
	private JTextField yearField;
	private JTextField priceField;
	private JTextField copiesField;
	private JTextField thresholdField;
	private JTextField categoryField;
	private JTextField authorsField;
	
	private JButton saveButton;
	private Controller controller;
	private Book book;
	private boolean create;
	
	
	public BookView(Book book, boolean create) {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel ISBNLabel = new JLabel("ISBN: ");
		getContentPane().add(ISBNLabel, "4, 4");
		
		ISBNField = new JTextField();
		getContentPane().add(ISBNField, "8, 4, fill, default");
		ISBNField.setColumns(10);
		
		
		JLabel titleLabel = new JLabel("Title: ");
		getContentPane().add(titleLabel, "4, 6");
		
		titleField = new JTextField();
		getContentPane().add(titleField, "8, 6, fill, default");
		titleField.setColumns(10);
		
		
		JLabel publisherLabel = new JLabel("Publisher: ");
		getContentPane().add(publisherLabel, "4, 8");
		
		publisherField = new JTextField();
		getContentPane().add(publisherField, "8, 8, fill, default");
		publisherField.setColumns(10);
		
		
		JLabel yearLabel = new JLabel("Year: ");
		getContentPane().add(yearLabel, "4, 10");
		
		yearField = new JTextField();
		getContentPane().add(yearField, "8, 10, fill, default");
		yearField.setColumns(10);
		
		
		JLabel priceLabel = new JLabel("price: ");
		getContentPane().add(priceLabel, "4, 12");
		
		priceField = new JTextField();
		getContentPane().add(priceField, "8, 12, fill, default");
		priceField.setColumns(10);
		
		
		JLabel copiesLabel = new JLabel("# Cpoies: ");
		getContentPane().add(copiesLabel, "4, 14");
		
		copiesField = new JTextField();
		getContentPane().add(copiesField, "8, 14, fill, default");
		copiesField.setColumns(10);
		
		
		JLabel thresholdLabel = new JLabel("Threshold: ");
		getContentPane().add(thresholdLabel, "4, 16");
		
		thresholdField = new JTextField();
		getContentPane().add(thresholdField, "8, 16, fill, default");
		thresholdField.setColumns(10);
		
		
		JLabel categoryLabel = new JLabel("Category: ");
		getContentPane().add(categoryLabel, "4, 18");
		
		categoryField = new JTextField();
		getContentPane().add(categoryField, "8, 18, fill, default");
		categoryField.setColumns(10);
		
		
		JLabel authorLabel = new JLabel("Authors: ");
		getContentPane().add(authorLabel, "4, 20");
		
		authorsField = new JTextField();
		getContentPane().add(authorsField, "8, 20, fill, default");
		authorsField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(200, 200, 200, 200);
		getContentPane().add(saveButton);
		saveButton.addActionListener(new saveAction());
		
		//controller =  Controller.getInstance();
		this.book = book;
		this.create = create;
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
	
	
	private class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Book temp = new Book();
			temp.setISBN(Integer.valueOf(ISBNField.getText().toLowerCase()));
			temp.setTitle(titleField.getText().toLowerCase());
			temp.setPublisherName(publisherField.getText().toLowerCase());
			temp.setYear(yearField.getText().toLowerCase());
			temp.setPrice(Double.valueOf(priceField.getText().toLowerCase()));
			temp.setNumberOfCopies(Integer.valueOf(copiesField.getText().toLowerCase()));
			temp.setThreshold(Integer.valueOf(thresholdField.getText().toLowerCase()));
			temp.setCategory(categoryField.getText().toLowerCase());
			temp.setAuthors(getAuthors(authorsField.getText().toLowerCase()));
			if(create){
				/// call create book
			}else{
				/// call edit book
			}
		}
		
		private ArrayList<String> getAuthors(String authers){
			String []allAuthers = authers.split("\\s*\\,\\s*");
			ArrayList<String> list = new ArrayList<String>();
			for(String auther : allAuthers){
				list.add(auther);
			}
			return list;
		}
		
	}
	
}

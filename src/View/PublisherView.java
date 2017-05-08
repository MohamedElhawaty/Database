package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Publisher;

public class PublisherView extends JFrame {
	
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	
	private Controller controller;
	private JButton saveButton;
	private Publisher publisher;
	private boolean create;
	
	public PublisherView(Publisher publisher, boolean create){
		
		this.publisher = publisher;
		this.create = create;
		
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
		
		JLabel nameLabel = new JLabel("Publisher Name: ");
		getContentPane().add(nameLabel, "4, 4");
		
		nameField = new JTextField();
		getContentPane().add(nameField, "8, 4, fill, default");
		nameField.setColumns(10);
		
		
		JLabel addressLabel = new JLabel("Addressess: ");
		getContentPane().add(addressLabel, "4, 6");
		
		addressField = new JTextField();
		getContentPane().add(addressField, "8, 6, fill, default");
		addressField.setColumns(10);
		
		
		JLabel phoneLabel = new JLabel("Phone Numbers: ");
		getContentPane().add(phoneLabel, "4, 8");
		
		phoneField = new JTextField();
		getContentPane().add(phoneField, "8, 8, fill, default");
		phoneField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(200, 200, 200, 200);
		getContentPane().add(saveButton);
		saveButton.addActionListener(new saveAction());
		
		if(!create){
			update(publisher);
		}
		
	}
	
	private void update(Publisher publisher){
		nameField.setText(publisher.getName());
		addressField.setText(publisher.getAddresses().toString());
		phoneField.setText(publisher.getPhoneNumbers().toString());
	}
	
	private class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Publisher temp = new Publisher(nameField.getText().toLowerCase());
			temp.setAddresses(getElements(addressField.getText().toLowerCase()));
			temp.setPhoneNumbers(getElements(phoneField.getText().toLowerCase()));
			if(create){
				/// call create publisher
				//if faild update by book else update by temp & book = temp
				if(controller.addPublisher(temp)){
					publisher = temp;
				}
				update(publisher);
			}else{
				/// call edit publisher
			}
		}
		
		private ArrayList<String> getElements(String elemetns){
			String []allelemetns = elemetns.split("\\s*\\,\\s*");
			ArrayList<String> list = new ArrayList<String>();
			for(String element : allelemetns){
				list.add(element);
			}
			return list;
		}
		
	}
	
}

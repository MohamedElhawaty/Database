package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.Controller;
import model.Order;

public class OrderView extends JFrame {

	private JTextField IDField;
	private JTextField ISBNField;
	private JTextField quantityField;
	
	private Controller controller;
	private JButton saveButton;
	private Order order;
	private boolean create;
	
	public OrderView(Order order, boolean create){
		this.order = order;
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
		
		JLabel IDLabel = new JLabel("Publisher Name: ");
		getContentPane().add(IDLabel, "4, 4");
		
		IDField = new JTextField();
		getContentPane().add(IDField, "8, 4, fill, default");
		IDField.setColumns(10);
		
		
		JLabel ISBNLabel = new JLabel("Addressess: ");
		getContentPane().add(ISBNLabel, "4, 6");
		
		ISBNField = new JTextField();
		getContentPane().add(ISBNField, "8, 6, fill, default");
		ISBNField.setColumns(10);
		
		
		JLabel quantityLabel = new JLabel("Phone Numbers: ");
		getContentPane().add(quantityLabel, "4, 8");
		
		quantityField = new JTextField();
		getContentPane().add(quantityField, "8, 8, fill, default");
		quantityField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(200, 200, 200, 200);
		getContentPane().add(saveButton);
		saveButton.addActionListener(new saveAction());
		
		if(!create){
			update(order);
		}
	}
	
	private void update(Order order){
		IDField.setText(String.valueOf(order.getOrderId()));
		ISBNField.setText(String.valueOf(order.getISBN()));
		quantityField.setText(String.valueOf(order.getQuantity()));
	}   
	
	private class saveAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Order temp = new Order();	//// it's Error 
			temp.setOrderId(Integer.valueOf(IDField.getText().toLowerCase()));
			temp.setISBN(Integer.valueOf(ISBNField.getText().toLowerCase()));
			temp.setQuantity(Integer.valueOf(quantityField.getText().toLowerCase()));
			if(create){
				/// call create order
				if(controller.placeOrder(temp)){
					order = temp;
				}
				update(order);
			}else{
				/// call edit order
//				if(controller.editOrder(temp)){
//					order = temp;
//				}
//				update(order);
			}
		}
		
	}
	
}

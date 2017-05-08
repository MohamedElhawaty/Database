package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Book;
import model.User;

public class ShoppingCartView extends JFrame {
	
	private JTable table;
	
	private final String [] buttons = {"Buy", "Delete Book", "Delete All"};
	private ActionListener[] actions;
	private User user;
	private Vector<String> columnNames = new Vector<String>();
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	
	private Controller controller;
	
	public ShoppingCartView(User user) {
	
		this.user = user;
		getContentPane().setLayout(null);
			 
		
		table = new JTable();
		if(user != null){
			LinkedHashMap<Book,Integer> books = user.getShoppingCart();
			updateTable(books);
		}
		
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//table.setBounds(12, 285, 320, -250);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

		
		
		actions = new ActionListener[3];
		actions[0] = new BuyAction();
		actions[1] = new DeleteBookAction();
		actions[2] = new DeleteAllAction();
		
		int x = 333, y = 33, w = 117, h = 25;
		for(int i = 0 ; i < 3 ; i++){
			JButton button = new JButton(buttons[i]);
			button.setBounds(x, y+=(h+5), w, h);
			button.addActionListener(actions[i]);
			getContentPane().add(button);
		}
		
		
			
	}
	
	private void updateTable(LinkedHashMap<Book,Integer> books){
		columnNames.add("ISBN");
		columnNames.add("Title");
		columnNames.add("Publisher");
		columnNames.add("Year");
		columnNames.add("Price");
		columnNames.add("#Copies");
		columnNames.add("Threshold");
		columnNames.add("Category");
		columnNames.add("Quantity");
		columnNames.add("Authers");
		
		
		int count = 0;
		for(Entry<Book, Integer> entry : books.entrySet()){
			Vector<String> tuple = new Vector<String>();
			Book book = entry.getKey();
			int quantity = entry.getValue();
			count += quantity;
			tuple.add(String.valueOf(book.getISBN()));
			tuple.add(book.getTitle());
			tuple.add(book.getPublisherName());
			tuple.add(book.getYear());
			tuple.add(String.valueOf(book.getPrice()));
			tuple.add(String.valueOf(book.getNumberOfCopies()));
			tuple.add(String.valueOf(book.getThreshold()));
			tuple.add(book.getCategory());
			tuple.add(String.valueOf(quantity));
			tuple.add(book.getAuthors().toString());
			data.add(tuple);
		}
		
		Vector<String> tuple = new Vector<String>();
		tuple.add("");
		tuple.add("Total price");
		tuple.add("");
		tuple.add("");
		//tuple.add(String.valueOf(user.getTotalPrice()));
		tuple.add("");
		tuple.add("");
		tuple.add("");
		tuple.add(String.valueOf(count));
		tuple.add("");
		data.add(tuple);
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table.setModel(model);
		
	}
	
	private class BuyAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.checkout();
		}
	}
	private class DeleteBookAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer ISBN = Integer.valueOf((String)table.getValueAt(table.getSelectedRow(), 0));
			//user.removeBookFromShoppingCart(ISBN);
			updateTable(user.getShoppingCart());
		}
	}
	private class DeleteAllAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//user.removeAll(); or 
			updateTable(user.getShoppingCart());
		}
	}
}

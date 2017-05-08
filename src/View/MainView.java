package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Book;
import model.Manager;
import model.Order;
import model.Publisher;
import model.User;

public class MainView extends JFrame {
	private JTextField searchField;
	private JTable table;
	private JComboBox comboBox;
	private JFrame frame = this;
	
	private static Vector<String> columnNames = new Vector<String>();
	private static Vector<Vector<String>> data = new Vector<Vector<String>>();
	
	/// NOTE the values of this strings should be the same in DB
	private final String [] attributes = {"ISBN", "Title", "Publisher", "Year", "Price", "numberOfCopies", "threshold", "category", "salesNumber", "authors"};
	private final String [] buttons = {"profile", "Add to Cart", "Show Cart", "New Book", "Edit Book",
				"Place Order","Show Orders" , "Confirm Order", "Show Customers", "Promote Customer", "Total Sales", "Top 5 Customer", "Top 10 Books", "Add Publisher"};
	private ActionListener[] actions;
	private String selectedAttribute;
	private User user;
	private Controller controller;
	private static ArrayList<Book> searchBooks = new ArrayList<Book>();
	private static ArrayList<User> customer = new ArrayList<User>();
	private static ArrayList<Order> orders = new ArrayList<Order>();
	
	private final static int BOOK = 0, CUSTOMER = 1, ORDER = 2;
	private static int state = BOOK;
	
	
	public MainView(User user) {
		
		this.user = user;
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox(attributes);
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(12, 6, 100, 24);
		comboBox.setEditable(true);
		comboBox.addActionListener(new ComboBoxAction());
		getContentPane().add(comboBox);
		
		searchField = new JTextField();
		searchField.setBounds(116, 8, 96, 19);
		getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(224, 5, 90, 25);
		searchButton.addActionListener(new searchAction());
		getContentPane().add(searchButton);
		
		

		table = new JTable();
		if(state == BOOK){
			updateTableBooks(searchBooks);
		}else if(state == CUSTOMER){
			updateTableCustomer(customer);
		}else if(state == ORDER){
			
		}
		
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//table.setBounds(30, 285, 302, -223);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
		
		
		actions[0] = new ProfileAction();
		actions[1] = new AddToCartAction();
		actions[2] = new ShowCartAction();
		actions[3] = new NewBookAction();
		actions[4] = new EditBookAction();
		actions[5] = new PlaceOrderAction();
		actions[6] = new ConfirmOrderAction();
		actions[7] = new ShowOrderAction();
		actions[8] = new ShowCustomerAction();
		actions[9] = new promoteCustomerAction();
		actions[10] = new TotalSalesAction();
		actions[11] = new Top5CustomersAction();
		actions[12] = new Top10BooksAction();
		actions[13] = new Top10BooksAction();
		
		
		int x = 321, y = 12, w = 117, h = 25;
		for(int i = 0 ; i < 3 ; i++){
			JButton button = new JButton(buttons[i]);
			button.setBounds(x, y+=(h+5), w, h);
			button.addActionListener(actions[i]);
			getContentPane().add(button);
		}
		
		if(user instanceof Manager){
			for(int i = 3 ; i < 14 ; i++){
				JButton button = new JButton(buttons[i]);
				button.setBounds(x, y+=(h+5), w, h);
				button.addActionListener(actions[i]);
				getContentPane().add(button);
			}
		}
			
		JButton logoutbutton = new JButton("logout");
		logoutbutton.setBounds(x, y+=(h+5), w, h);
		logoutbutton.addActionListener(new LogoutAction());
		getContentPane().add(logoutbutton);	
		
		
	}
	
	
	private void updateTableBooks(ArrayList<Book> books){
		state = BOOK;
		columnNames.clear();
		columnNames.add("ISBN");
		columnNames.add("Title");
		columnNames.add("Publisher");
		columnNames.add("Year");
		columnNames.add("Price");
		columnNames.add("#Copies");
		columnNames.add("Threshold");
		columnNames.add("Category");
		columnNames.add("Authers");
		
		if(books == null || books.size() <= 0){
			Vector<String> tuple = new Vector<String>();
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			data.add(tuple);
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}else{
			for(Book book : books){
				Vector<String> tuple = new Vector<String>();
				tuple.add(String.valueOf(book.getISBN()));
				tuple.add(book.getTitle());
				tuple.add(book.getPublisherName());
				tuple.add(book.getYear());
				tuple.add(String.valueOf(book.getPrice()));
				tuple.add(String.valueOf(book.getNumberOfCopies()));
				tuple.add(String.valueOf(book.getThreshold()));
				tuple.add(book.getCategory());
				tuple.add(book.getAuthors().toString());
				data.add(tuple);
			}
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}
	}
	
	private void updateTableCustomer(ArrayList<User> users){
		state = CUSTOMER;
		columnNames.clear();
		columnNames.add("User Name");
		columnNames.add("First Name");
		columnNames.add("last Name");
		columnNames.add("Email");
		columnNames.add("Phone");
		columnNames.add("Address");

		if(users == null || users.size() <= 0){
			Vector<String> tuple = new Vector<String>();
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			tuple.add("");
			data.add(tuple);
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}else{
			for(User user : users){
				Vector<String> tuple = new Vector<String>();
				tuple.add(String.valueOf(user.getUserName()));
				tuple.add(user.getFirstName());
				tuple.add(user.getLastName());
				tuple.add(user.getEmail());
				tuple.add(user.getPhoneNumber());
				tuple.add(user.getShippingAddress());
				data.add(tuple);
			}
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}
	}
	
	private void updateTableOrders(ArrayList<Order> orders){
		state = ORDER;
		columnNames.clear();
		columnNames.add("ID");
		columnNames.add("ISBN");
		columnNames.add("Quantitiy");

		if(orders == null || orders.size() <= 0){
			Vector<String> tuple = new Vector<String>();
			tuple.add("");
			tuple.add("");
			tuple.add("");
			data.add(tuple);
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}else{
			for(Order order : orders){
				Vector<String> tuple = new Vector<String>();
				tuple.add(String.valueOf(order.getOrderId()));
				tuple.add(String.valueOf(order.getISBN()));
				tuple.add(String.valueOf(order.getQuantity()));
				data.add(tuple);
			}
			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			table.setModel(model);
		}
	}
	
	
	private class ComboBoxAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedAttribute = (String) comboBox.getSelectedItem();
		}
	}
	
	
	private class searchAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			searchBooks = controller.searchBook(selectedAttribute.toLowerCase(), searchField.getText().toLowerCase());
			updateTableBooks(searchBooks);
		}
	}
	
	
	private class ProfileAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			UserView userView = new UserView(user, false);
			userView.setVisible(true);
		}
	}
	
	
	private class AddToCartAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(state == BOOK){
				String toBuy = JOptionPane.showInputDialog(frame, "How many copies you want to buy?");
				Book selectedBook = searchBooks.get(table.getSelectedRow());
				user.addBookToShoppingCart(selectedBook, Integer.valueOf(toBuy));
			}
		}
	}
	
	
	private class ShowCartAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ShoppingCartView cartView = new ShoppingCartView(user);
			cartView.setVisible(true);
		}
	}
	
	
	private class NewBookAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BookView bookView = new BookView(new Book(), true);
			bookView.setVisible(true);
		}
	}
	
	private class EditBookAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(state == BOOK){
				Book selectedBook = searchBooks.get(table.getSelectedRow());
				BookView bookView = new BookView(selectedBook, false);
				bookView.setVisible(true);
			}
			
		}
	}
	
	private class PlaceOrderAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// need to empty constructor for order
			OrderView orderView = new OrderView(new Order(), true);
			orderView.setVisible(true);
		}
	}
	
	private class ShowOrderAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			orders = controller.getAllOrder();
			updateTableOrders(orders);
		}
	}
	
	private class ConfirmOrderAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(state == ORDER){
				Order selectedOrder = orders.get(table.getSelectedRow());
				controller.confirmOrder(selectedOrder);
			}
		}
	}
	
	private class ShowCustomerAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			customer = controller.getAllCustomers();
			updateTableCustomer(customer);
		}
	}
	
	private class promoteCustomerAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(state == CUSTOMER){
				User selectedUser = customer.get(table.getSelectedRow());
				controller.promote(selectedUser);
			}
		}
	}
	
	private class TotalSalesAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "total sales: "+ controller.getTotalSales(), "total sales", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class Top5CustomersAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			customer = controller.getTopFiveCustomers();
			updateTableCustomer(customer);
		}
	}
	
	private class Top10BooksAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			searchBooks = controller.getTopTenBooks();
			updateTableBooks(searchBooks);
		}
	}
	
	private class AddPublisherAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			PublisherView publisherView = new PublisherView(new Publisher(), true);
			publisherView.setVisible(true);
		}
	}
	
	private class LogoutAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Login login = new Login();
			login.setVisible(true);
			frame.setVisible(false);
		}
	}
}

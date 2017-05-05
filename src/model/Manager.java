package model;

import java.sql.*;

import controller.Controller;

public class Manager extends User {

  public void addBook(Book book) throws SQLException {
    book.addToDatabase();

  }

  public void modifyBook(Book book) throws SQLException {
    book.modify();
  }

  public void placeOrder(Order order) throws SQLException {
    // adding order
    order.addToOrderTable();

  }

  public void confirmOrder(Order order) throws SQLException {
    // delete order from placeTable
    order.delete();
  }

  public void promoteCustomer(User user) throws SQLException {
    user.promote();
  }

  public ResultSet getTotalSales() throws SQLException {
    String query = "Select sum(sales_number) from sales where  selling_date between (current_Date() - Interval 1 Month) "
        + "And Current_Date()";
    return Controller.stmt.executeQuery(query);

  }

  public ResultSet getTopFiveCustomers() throws SQLException {
    String query = "Select user_name , sum(sales_number) from sales"
        + "group by user_name "
        + "order by sales_number DESC "
        + "limit 5";
    return Controller.stmt.executeQuery(query);
  }

  public ResultSet getTopTenBooks() throws SQLException {
    String query = "Select ISBN , sum(sales_number) from sales"
        + "group by ISBN "
        + "order by sales_number DESC "
        + "limit 10";
    return Controller.stmt.executeQuery(query);
  }

  public ResultSet getAllCustomers() throws SQLException {
    String query = "Select * from user where isManager = false";
    return Controller.stmt.executeQuery(query);
  }
}

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
    String query = "Select sum(salesnumber) where sellingDate between (current_Date() - Interval 1 Month) "
        + "And Current_Date()";
    return Controller.stmt.executeQuery(query);

  }

  public ResultSet getTopFiveCustomers() throws SQLException {
    String query = "";
    return Controller.stmt.executeQuery(query);
  }

  public ResultSet getTopTenBooks() throws SQLException {
    String query = "";
    return Controller.stmt.executeQuery(query);
  }

  public ResultSet getAllCustomers() throws SQLException {
    String query = "Select * from user where isManager = false";
    return Controller.stmt.executeQuery(query);
  }
}

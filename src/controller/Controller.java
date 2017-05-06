package controller;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class Controller {
  public static Statement stmt;
  private  Connection con;
  private User user ;
  public Controller() {
    startController();
  }

  private void startController() {
    // TODO Auto-generated method stub
    try {
      this.con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/bookstore", "root", "root");
      Controller.stmt = this.con.createStatement();

    } catch (SQLException e) {
      // View.showError(e.getMessage());

    }

    // delete dates longer than 3 months
    String query = "delete from `sales`"
        + "WHERE selling_date  < (current_Date() - Interval 3 Month)";
    try {
      Controller.stmt.executeUpdate(query);
    } catch (SQLException e) {
      // View.showError(e.getMessage());
    }
  }
// if user not found ( wrong password or user name ) return null
  public User login(String name, String password) {
    String query = "select * from user"
        + "WHERE name  = " + name + " and password = "+  password;
    try {
      User us = null;
      ResultSet rs =Controller.stmt.executeQuery(query); 
      if (rs.next()){
        boolean isManager = rs.getBoolean("isManager");
        if(isManager){
          us = new Manager();
        }else{
          us = new User();
        }
        us.setEmail(rs.getString("Email"));
        us.setFirstName(rs.getString("Fname"));
        us.setLastName(rs.getString("Lname"));
        us.setPassword(rs.getString("password"));
        us.setPhoneNumber(rs.getString("phoneNumber"));
        us.setShippingAddress(rs.getString("shippingAddress"));
        us.setUserName(rs.getString("name"));
      }
      this.user = us;
      return us;
    } catch (SQLException e) {
      // View.showError(e.getMessage());
    }
    return null;
  }

  public void signup(User user) {
    String query = new String();
    if(user.getShippingAddress() != null && user.getPhoneNumber() != null){
      query = "Insert into user (name,password,Lname,Fname"
          + ",Email,phoneNumber,shippingAddress)"
          + " values ( " + user.getUserName() + " , " 
        +user.getPassword() + " , " 
        +user.getLastName() + " , " 
        +user.getFirstName() + " , " 
        +user.getEmail() + " , " 
        +user.getPhoneNumber() + " , " 
        +user.getShippingAddress() + " ) "; 
    }else if(user.getShippingAddress() == null && user.getPhoneNumber() == null){
      query = "Insert into user (name,password,Lname,Fname"
          + ",Email)"
          + " values ( " + user.getUserName() + " , " 
        +user.getPassword() + " , " 
        +user.getLastName() + " , " 
        +user.getFirstName() + " , " 
        +user.getEmail() + " ) "; 
    }else if(user.getShippingAddress() == null){
      query = "Insert into user (name,password,Lname,Fname"
          + ",Email,phoneNumber)"
          + " values ( " + user.getUserName() + " , " 
        +user.getPassword() + " , " 
        +user.getLastName() + " , " 
        +user.getFirstName() + " , " 
        +user.getEmail() + " , " 
        +user.getPhoneNumber() + " ) "; 
    }else{
      query = "Insert into user (name,password,Lname,Fname"
          + ",Email,shippingAddress)"
          + " values ( " + user.getUserName() + " , " 
        +user.getPassword() + " , " 
        +user.getLastName() + " , " 
        +user.getFirstName() + " , " 
        +user.getEmail() + " , " 
        +user.getShippingAddress() + " ) "; 
    }
    try {
      Controller.stmt.executeUpdate(query);
    } catch (SQLException e) {
      // View.showError(e.getMessage());
    }  

  }
  // ----------------------------------------------- user --------------------------------------------
  public void editInformation(User us) {
      try {
        us.editInformation();
        this.user = us;
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }  
  
  public ArrayList<Book> searchBook(String attrubite, String value) {
    try {
      ResultSet rs = (this.user).searchBook(attrubite,value);
      ArrayList<Book> books = new ArrayList<>();
      while (rs.next()){
        Book book = new Book();
        book.setCategory(rs.getString("category"));
        book.setISBN(rs.getInt("ISBN"));
        book.setNumberOfCopies(rs.getInt("number_of_copies"));
        book.setPrice(rs.getInt("selling_price"));
        book.setPublisherName(rs.getString("publisher_name"));
        book.setThreshold(rs.getInt("threshold"));
        book.setTitle(rs.getString("title"));
        book.setYear(rs.getString("publication_year"));
        books.add(book);
      }
      return books;
    } catch (SQLException e) {
      // View.showError(e.getMessage());
    }
    return null;
  }
  
  public void checkout() {
    try {
      this.con.setAutoCommit(false);
    } catch (SQLException e) {
      // View.showError(e.getMessage());

    }
    
    try {
      this.user.checkOut();
      this.con.commit();
    } catch (SQLException e1) {
      
      // View.showError(e.getMessage());
      try {
        this.con.rollback();
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }
    
    
    try {
      this.con.setAutoCommit(true);
    } catch (SQLException e) {
      // View.showError(e.getMessage());

    }

  }

  //-----------------------------------manager--------------------------
  public void addBook(Book book ){
    if(user instanceof Manager){
      try {
        ((Manager)this.user).addBook(book);
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }
  
  public void modifyBook(Book book ){
    if(user instanceof Manager){
      try {
        ((Manager)this.user).modifyBook(book);
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }

  public void placeOrder(Order order){
    if(user instanceof Manager){
      try {
        ((Manager)this.user).placeOrder(order);
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }
  
  public void confirmOrder(Order order){
    if(user instanceof Manager){
      try {
        ((Manager)this.user).confirmOrder(order);
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }
  
  public void promoteCustomer(User user){
    if(user instanceof Manager){
      try {
        ((Manager)this.user).promoteCustomer(user);
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }
  
  public Integer getTotalSales() {
    if(user instanceof Manager){
      try {
        ResultSet rs = ((Manager)this.user).getTotalSales();
        if (rs.next()){
          return rs.getInt(1);
        }
        else {
          return 0;
        }
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
    return null;
  }
  
  public ArrayList<User> getTopFiveCustomers(){
    if(user instanceof Manager){
      try {
        ResultSet rs = ((Manager)this.user).getTopFiveCustomers();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){
          User us = new User();
          us.setSalesNumber(rs.getInt("sum(sales_number)"));
          us.setUserName(rs.getString("user_name"));
          users.add(us);
        }
        return users;
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
    return null;
  }
  
  public ArrayList<Book> getTopTenBooks(){
    if(user instanceof Manager){
      try {
        ResultSet rs = ((Manager)this.user).getTopTenBooks();
        ArrayList<Book> books = new ArrayList<>();
        while (rs.next()){
          Book book = new Book();
          book.setISBN(rs.getInt("ISBN"));
          book.setSalesNumber(rs.getInt("sum(sales_number)"));
        }
        return books;
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
    return null;
  }
  
  public ArrayList<User> getAllCustomers(){
    if(user instanceof Manager){
      try {
        ResultSet rs = ((Manager)this.user).getAllCustomers();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){
          boolean isManager = rs.getBoolean("isManager");
          User us ;
          if(isManager){
            us = new Manager();
          }else{
            us = new User();
          }
          us.setEmail(rs.getString("Email"));
          us.setFirstName(rs.getString("Fname"));
          us.setLastName(rs.getString("Lname"));
          us.setPassword(rs.getString("password"));
          us.setPhoneNumber(rs.getString("phoneNumber"));
          us.setShippingAddress(rs.getString("shippingAddress"));
          us.setUserName(rs.getString("name"));
          users.add(us);
        }
        return users;
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
    return null;
  }
  
  public void promote(User us){
    if(user instanceof Manager){
      try {
       us.promote();
        
      } catch (SQLException e) {
        // View.showError(e.getMessage());
      }
    }else{
   // View.showError("NOT ALLOWED");
    }    
  }
  
  
}

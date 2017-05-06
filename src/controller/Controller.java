package controller;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class Controller {
  public static  Statement stmt ; 
  public Controller() throws SQLException{
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/bookstore", "root", "root");
    Controller.stmt = con.createStatement();
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * sheel 25er 3 shoor men el sales
     */
  }
  
  public User login(String name , String password){
	  
	  return null;
  }
  
   public void signup(User user){
	  
  }
   
   public void editInformation(User user){
   }
   
   public ArrayList<Book> searchBook(String attrubite , String value){
	   return null;
   }
   
   
   }
  
}

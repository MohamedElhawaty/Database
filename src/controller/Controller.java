package controller;

import java.sql.*;

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
  
}

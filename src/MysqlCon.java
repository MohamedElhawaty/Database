import java.sql.*;

class MysqlCon {
  public static void main(String args[]) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/bookstore", "root", "root");
      // here sonoo is database name, root is username and password
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from publisher");
      while (rs.next())
        System.out.println(rs.getString(1) + "  " );
      con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
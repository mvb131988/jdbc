package root.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainUpdate {

  public static void main(String[] args) {
    Connection c = connection();
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "update transactions set amount = amount*2+1 where tx_id = 'tx-001'";
      int rows = stmt.executeUpdate(sql);
      System.out.println("Updated rows " + rows);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try { if (stmt != null) stmt.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (c != null) c.close(); } catch (Exception e) {e.printStackTrace();};
    }
  }
  
  private static Connection connection() {
    Properties connectionProps = new Properties();
    connectionProps.put("user", "postgres");
    connectionProps.put("password", "postgres");
    
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/db1",
          connectionProps);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return conn;
  } 
  
}

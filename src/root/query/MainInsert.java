package root.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainInsert {

  public static void main(String[] args) {
    Connection c = connection();
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "insert into transactions(tx_id, amount, currency, user_id) values('tx-004', 40, 'USD', 'u-004')";
      stmt.executeUpdate(sql);
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

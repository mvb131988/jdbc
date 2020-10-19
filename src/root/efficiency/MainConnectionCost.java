package root.efficiency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainConnectionCost {

  public static void main(String[] args) {
    long start0 = System.currentTimeMillis();
    Connection c = connection();
    long stop0 = System.currentTimeMillis();
    System.out.println(stop0-start0);
    
    Statement stmt = null;
    ResultSet rs = null;
    
    long start1 = System.currentTimeMillis();
    try {
      stmt = c.createStatement();
    
      String sql = "select * from transactions";
      
      rs = stmt.executeQuery(sql);
      while(rs.next()){
        String txId = rs.getString("tx_id");
        Integer amount = rs.getInt("amount");
        String currency = rs.getString("currency");
        String userId = rs.getString("user_id");
        
        System.out.println("[" + txId + "," 
                               + amount + "," 
                               + currency + "," 
                               + userId + "]");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (stmt != null) stmt.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (c != null) c.close(); } catch (Exception e) {e.printStackTrace();};
    }
    long stop1 = System.currentTimeMillis();
    System.out.println(stop1-start1);
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

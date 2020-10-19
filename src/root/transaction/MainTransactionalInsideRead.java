package root.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainTransactionalInsideRead {

  public static void main(String[] args) {
    Connection c = connection();
    Statement stmt0 = null;
    Statement stmt1 = null;
    Statement stmt2 = null;
    ResultSet rs = null;
    
    try {
      c.setAutoCommit(false);
      
      stmt0 = c.createStatement();
      String sql0 = "insert into users(user_id, name) values('u-005', 'Bob')";
      stmt0.executeUpdate(sql0);
      
      stmt1 = c.createStatement();
      String sql1 = "insert into transactions(tx_id, amount, currency, user_id) values('tx-005', 40, 'USD', 'u-005')";
      stmt1.executeUpdate(sql1);
      
      stmt2 = c.createStatement();
      String sql2 = "select * from transactions";
      
      rs = stmt2.executeQuery(sql2);
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
      
      c.commit();
    } catch (SQLException e) {
      //silent fail
    }
    finally {
      try { if (stmt0 != null) stmt0.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (stmt1 != null) stmt1.close(); } catch (Exception e) {e.printStackTrace();};
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

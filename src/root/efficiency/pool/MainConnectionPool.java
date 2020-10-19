package root.efficiency.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainConnectionPool {

  public static void main(String[] args) {
    long start0 = System.currentTimeMillis();
    ConnectionPool cp = new ConnectionPool();
    long stop0 = System.currentTimeMillis();
    System.out.println("Init connection pool " + (stop0-start0));
    
    start0 = System.currentTimeMillis();
    Connection c = cp.get();
    stop0 = System.currentTimeMillis();
    System.out.println("First connection " + (stop0-start0));
    
    Statement stmt0 = null;
    Statement stmt1 = null;
    ResultSet rs = null;

    /////insert
    start0 = System.currentTimeMillis();
    try {
      c.setAutoCommit(false);
      
      stmt0 = c.createStatement();
      String sql = "insert into transactions(tx_id, amount, currency, user_id) values('tx-004', 40, 'USD', 'u-004')";
      stmt0.executeUpdate(sql);
      c.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (stmt0 != null) stmt0.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (c != null) c.close(); } catch (Exception e) {e.printStackTrace();};
    }
    stop0 = System.currentTimeMillis();
    System.out.println("Insert " + (stop0-start0));
    
    /////select
    start0 = System.currentTimeMillis();
    c = cp.get();
    stop0 = System.currentTimeMillis();
    System.out.println("Second connection " + (stop0-start0));
    
    start0 = System.currentTimeMillis();
    try {
      c.setAutoCommit(false);
      stmt1 = c.createStatement();
      String sql = "select * from transactions";
      rs = stmt1.executeQuery(sql);
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
      e.printStackTrace();
    }
    finally {
      try { if (rs != null) rs.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (stmt1 != null) stmt1.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (c != null) c.close(); } catch (Exception e) {e.printStackTrace();};
    }
    stop0 = System.currentTimeMillis();
    System.out.println("Select " + (stop0-start0));
  }
  
}

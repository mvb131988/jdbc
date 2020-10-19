package root.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainSelect {

  /**
    create table transactions (
      id serial primary key,
      tx_id varchar not null,
      amount integer not null,
      currency varchar not null,
      user_id varchar not null,
      
      constraint uq_tx_id unique (tx_id)
    );
    
    insert into transactions(tx_id, amount, currency, user_id) 
    values('tx-001', 10, 'USD', 'u-001');
    insert into transactions(tx_id, amount, currency, user_id) 
    values('tx-002', 20, 'USD', 'u-002');
    insert into transactions(tx_id, amount, currency, user_id) 
    values('tx-003', 30, 'USD', 'u-003');
    
    https://stackoverflow.com/questions/4507440/must-jdbc-resultsets-and-statements-be-closed-separately-although-the-connection
    
   * @param args
   */
  public static void main(String[] args) {
    Connection c = connection();
    
    Statement stmt = null;
    ResultSet rs = null;
    
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

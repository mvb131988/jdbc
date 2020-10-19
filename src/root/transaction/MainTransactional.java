package root.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * https://stackoverflow.com/questions/51736424/why-do-i-have-to-use-rollback-in-jdbc#:~:text=You%20need%20to%20explicitly%20rollback,Explicitly%20rolling%20back%20prevents%20that.
 *
 */
public class MainTransactional {

  public static void main(String[] args) {
    Connection c = connection();
    Statement stmt0 = null;
    Statement stmt1 = null;
    try {
      c.setAutoCommit(false);
      
      stmt0 = c.createStatement();
      String sql0 = "insert into users(user_id, name) values('u-005', 'Bob')";
      stmt0.executeUpdate(sql0);
      
      stmt1 = c.createStatement();
      String sql = "insert into transactions(tx_id, amount, currency, user_id) values('tx-005', 40, 'USD', 'u-005')";
      stmt1.executeUpdate(sql);
      
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

package root.efficiency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//https://stackoverflow.com/questions/30587736/what-is-pre-compiled-sql-statement
public class MainPreparedStatement {

  public static void main(String[] args) {
    long start0 = System.currentTimeMillis();
    preparedStatementInsert();
    long stop0 = System.currentTimeMillis();
    System.out.println(stop0-start0);
  }
  
  private static void statementInsert() {
    Connection c = connection();
    
    //'tx-004', 40, 'USD', 'u-004')
    Statement stmt = null;
    try {
      c.setAutoCommit(false);
      stmt = c.createStatement();
      
      for(int i=10; i<10_000; i++) {
        String query =
            "insert into transactions(tx_id, amount, currency, user_id) "
            + "values(" 
            + ("'tx-00" + i*8) + "'," 
            + i + ","  
            + "'USD',"
            + ("'u-00" + (i*8-9) + "'")
            + ")";
        stmt.executeUpdate(query);
      }
      
      c.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try { if (stmt != null) stmt.close(); } catch (Exception e) {e.printStackTrace();};
      try { if (c != null) c.close(); } catch (Exception e) {e.printStackTrace();};
    }
  }
  
  private static void preparedStatementInsert() {
    Connection c = connection();
    
    String query =
        "insert into transactions(tx_id, amount, currency, user_id) values(?, ?, ?, ?)";
   
    //'tx-004', 40, 'USD', 'u-004')
    PreparedStatement ps = null;
    try {
      c.setAutoCommit(false);
      
      ps = c.prepareStatement(query);
      for(int i=10; i<10_000; i++) {
        ps.setString(1, "tx-00" + i);
        ps.setInt(2, i);
        ps.setString(3, "USD");
        ps.setString(4, "u-00" + i);
        ps.executeUpdate();
        
//        ps.addBatch();
//        if (i % 100 == 0 || i == 9999) {
//          ps.executeBatch();
//        }
      }
      
      c.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
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

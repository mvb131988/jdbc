package root.efficiency.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

  private List<Connection> freeConnections;
  private List<Connection> busyConnections;
  
  public ConnectionPool() {
    freeConnections = new LinkedList<Connection>();
    busyConnections = new LinkedList<Connection>();
    
    freeConnections.add(connection());
    freeConnections.add(connection());
  }
  
  private Connection connection() {
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
  
  public synchronized Connection get() {
    if(freeConnections.size() == 0) return null; 
    Connection c = freeConnections.remove(0);
    busyConnections.add(c);
    return new ReusableConnection(this, c);
  }
  
  public List<Connection> getFreeConnections() {
    return freeConnections;
  }

  public void setFreeConnections(List<Connection> freeConnections) {
    this.freeConnections = freeConnections;
  }

  public List<Connection> getBusyConnections() {
    return busyConnections;
  }

  public void setBusyConnections(List<Connection> busyConnections) {
    this.busyConnections = busyConnections;
  }

}

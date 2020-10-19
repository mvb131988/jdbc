package root.efficiency.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ReusableConnection implements Connection {
  
  private ConnectionPool connectionPool;
  private Connection c;
  
  public ReusableConnection(ConnectionPool connectionPool, Connection c) {
    this.connectionPool = connectionPool;
    this.c = c;
  }
  
  @Override
  public void close() throws SQLException {
    synchronized(connectionPool) {
      if(connectionPool.getBusyConnections().size() == 0) return;
      c = connectionPool.getBusyConnections().remove(0);
      connectionPool.getFreeConnections().add(c);
    }
  }
  
  @Override
  public void commit() throws SQLException {
    c.commit();
  }
  
  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {
    c.setAutoCommit(autoCommit);
  }
  
  @Override
  public Statement createStatement() throws SQLException {
    return c.createStatement();
  }
  
  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(String sql) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String nativeSQL(String sql) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getAutoCommit() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void rollback() throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isClosed() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public DatabaseMetaData getMetaData() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setReadOnly(boolean readOnly) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isReadOnly() throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setCatalog(String catalog) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getCatalog() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setTransactionIsolation(int level) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getTransactionIsolation() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clearWarnings() throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Class<?>> getTypeMap() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setHoldability(int holdability) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getHoldability() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Savepoint setSavepoint() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Savepoint setSavepoint(String name) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void rollback(Savepoint savepoint) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
      throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Clob createClob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Blob createBlob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NClob createNClob() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SQLXML createSQLXML() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isValid(int timeout) throws SQLException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setClientInfo(String name, String value) throws SQLClientInfoException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setClientInfo(Properties properties) throws SQLClientInfoException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getClientInfo(String name) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Properties getClientInfo() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSchema(String schema) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getSchema() throws SQLException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void abort(Executor executor) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getNetworkTimeout() throws SQLException {
    // TODO Auto-generated method stub
    return 0;
  }

}

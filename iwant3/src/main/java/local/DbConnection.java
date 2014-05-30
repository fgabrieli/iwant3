package local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class DbConnection {
  Connection db;

  public DbConnection(String dbName, String user, String passwd) throws SQLException,
      InstantiationException, IllegalAccessException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");

    connect(dbName, user, passwd);
  }

  public ArrayList<HashMap<String, String>> select(String query) {
    ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String, String>>();

    try {
      Statement statement = null;
      statement = db.createStatement();

      // Execute the query.
      ResultSet rs = statement.executeQuery(query);

      // Get column count.
      ResultSetMetaData metaData = rs.getMetaData();
      int columnCount = metaData.getColumnCount();

      while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
          String columnName = metaData.getColumnName(i);
          String value = rs.getString(columnName);
          HashMap<String, String> entry = new HashMap<String, String>();
          entry.put(columnName, value);
          entries.add(entry);
        }
      }
      rs.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return entries;
  }

  private void connect(String dbName, String user, String passwd)
      throws SQLException {
    Properties connectionProperties = new Properties();
    connectionProperties.setProperty("user", user);
    connectionProperties.setProperty("password", passwd);

    db = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
        connectionProperties);
  }
}

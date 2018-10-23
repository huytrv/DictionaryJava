package Dictionary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
  
public class QueryDataDictionary {
 
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
 
      Connection connection = MySQLConnUtils.getMySQLConnection();
 
      Statement statement = connection.createStatement();
 
      String sql = "Select word, detail from tbl_edict";
 
      ResultSet rs = statement.executeQuery(sql);
 
      while (rs.next()) {
          String empNo = rs.getString("word");
          String empName = rs.getString("detail");

          if (empNo.equals("fine")) {
          System.out.println("EmpNo:" + empNo);
          System.out.println("EmpName:" + empName);
          break;
          }
      }
      connection.close();
  }
 
}
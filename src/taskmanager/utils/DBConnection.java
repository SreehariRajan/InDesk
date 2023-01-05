/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskmanager.utils;
import java.sql.*;
/**
 *
 * @author Sreehari Rajan
 */
public class DBConnection {
    String url="jdbc:mysql://localhost:3306/taskmanager";
    String user="root";
    String password="root";
    
    public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    Connection con=null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url,user,password);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return con;
  }
  
  public void closeConnection(Connection con,Statement stmt) throws SQLException {
    stmt.close();
    con.close();
  }
  
}

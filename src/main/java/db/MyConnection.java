package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection{
  public  static Connection connection=null;
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ytproject?useSSL=false","root","mysql_pass_word");
    System.out.println("connection established");
    return connection;
  }

  public static void closeConnection(){
    if(connection!=null){
      try{
        connection.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws Exception{
    MyConnection.getConnection();
  }

}
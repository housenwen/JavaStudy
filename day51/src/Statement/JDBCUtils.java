package Statement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
   static String driverClass = null;
   static String url = null;
   static String user = null;
   static String password = null;

   static {
       Properties pro = new Properties();
       try {
           FileInputStream fis = new FileInputStream("jdbc.properties");
           pro.load(fis);
           driverClass = pro.getProperty("driverClass");
           url = pro.getProperty("url");
           user = pro.getProperty("user");
           password = pro.getProperty("password");

       } catch (Exception e) {
           e.printStackTrace();
       }

   }

   public static Connection getConnection(){
       Connection con = null;
       try {
          con = DriverManager.getConnection(url,user,password);
       }catch (Exception e){
           e.printStackTrace();
       }
       return con;
   }
   public static void release(Connection conn, Statement stmt, ResultSet rs){
       try {
           if (rs!=null){
               rs.close();
           }
       }catch (Exception e){
           System.out.println("rs"+e.getMessage());
       }
       try {
           if(stmt!=null){
               stmt.close();
           }
       }catch (Exception e){
           System.out.println("stmt"+e.getMessage());
       }
       try {
           if (conn!=null){
               conn.close();
           }
       }catch (Exception e){
           System.out.println("conn"+e.getMessage());
       }
   }
}

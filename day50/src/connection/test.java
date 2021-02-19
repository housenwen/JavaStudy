package connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class test2 {
   @Test
    public void test() throws SQLException,NoClassDefFoundError {
       Connection conn = JDBCUtils.getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select * from user");
       while (rs.next()){
           String s1 = rs.getString(1);
           String s2 = rs.getString(2);
           String s3 = rs.getString(3);
           String s4 = rs.getString(4);
           System.out.println(s1+' '+s2+' '+s3+' '+s4);
       }
       JDBCUtils.release(conn,stmt,rs);
   }
}

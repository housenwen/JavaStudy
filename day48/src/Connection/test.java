package Connection;

import CallableStatement.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    @Test
    public void test() throws SQLException {
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
    }
}

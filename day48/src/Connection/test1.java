package Connection;

import CallableStatement.JDBCUtils;
import org.junit.Test;

import java.sql.*;

public class test1 {
    public static void main(String[] args) throws SQLException {
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

        System.out.println("-----------------------------------");
        rs = stmt.executeQuery("select * from account");
        while (rs.next()){
            String s1 = rs.getString(1);
            String s2 = rs.getString(2);
            String s3 = rs.getString(3);
            String s4 = rs.getString(4);
            System.out.println(s1+' '+s2+' '+s3+' '+s4);
        }

        System.out.println("------------------------------------");
        rs = stmt.executeQuery("select * from transaction_details");
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

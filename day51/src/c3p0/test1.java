package c3p0;

import Statement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test1 {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from emp";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String city = rs.getString(3);
            System.out.println(id+" "+name+" "+city);
        }
        JDBCUtils.release(conn,stmt,rs);
    }
}

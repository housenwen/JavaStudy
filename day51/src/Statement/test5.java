package Statement;

import java.sql.Connection;
import java.sql.Statement;

public class test5 {
    public static void main(String[] args) throws Exception {
        Connection con = JDBCUtils.getConnection();
        Statement stmt = con.createStatement();
        String sql = "delete from emp where id in (1,2)";
//        ResultSet rs = stmt.executeQuery(sql);
        int sum = stmt.executeUpdate(sql);
        System.out.println(sum);
        JDBCUtils.release(con,stmt,null);

    }
}

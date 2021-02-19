package Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class test4 {
    public static void main(String[] args) throws Exception {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String tel = rs.getString(3);
            String address = rs.getString(4);
            System.out.println(id + " " + name + " " + tel + " " + address);
        }

        JDBCUtils.release(connection, statement, rs);
    }
}

package Statement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test2 {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day06", "root", "root");
        Statement stmt = conn.createStatement();

        String sql = "select * from user";

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
//                int id = rs.getInt("id");
            String id = rs.getString(1);
            String name = rs.getString(2);
            String tel = rs.getString(3);
            String address = rs.getString(4);
            System.out.println(id + " " + name + " " + tel + " " + address);
        }

        rs.close();
        stmt.cancel();
        conn.close();
    }
}

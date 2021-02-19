package Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test3 {
    public static void main(String[] args) throws Exception {

//        DriverManager driverManager = new DriverManager("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day05","root","root");
        Statement statement = conn.createStatement();

        String sql = "select * from city";

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String cid = rs.getString(3);

            System.out.println(id+" "+name+" "+cid);
        }
        conn.close();
        statement.close();
        rs.close();
    }
}

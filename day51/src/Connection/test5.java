package Connection;

import Statement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class test5 {
    public static void main(String[] args) throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into user values (4 ,?,?,null )";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1,"小王");
        ps.setString(2,"12345672");

        int sum = ps.executeUpdate();

        System.out.println(sum);

        System.out.println("-------------------");
        sql = "UPDATE  user set address =? where id = ?";

        ps = connection.prepareStatement(sql);
        ps.setString(1,"北京");
        ps.setString(2,"4");
        sum = ps.executeUpdate();
        System.out.println(sum);
        JDBCUtils.release(connection,ps,null);
    }

}

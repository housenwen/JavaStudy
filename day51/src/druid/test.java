package druid;

import Statement.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws Exception {

        Properties info = new Properties();
        FileInputStream fis = new FileInputStream("druid.properties");
        info.load(fis);

        System.out.println(info);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(info);

        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;

        conn = dataSource.getConnection();

        String sql = "select * from emp";

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()){
            String id = rs.getString(1);
            String name = rs.getString(2);
            String city = rs.getString(3);
            System.out.println(id + ":::" + name + "===" + city);
        }
        JDBCUtils.release(conn,stmt,rs);
    }
}

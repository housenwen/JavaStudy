package druid;

import Statement.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class test1 {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
//        FileInputStream fis = new FileInputStream("druid");
        //相对src目录 反射
        InputStream fis = test1.class.getClassLoader().getResourceAsStream("druid.properties");

//        InputStream fis = test1.class.getResourceAsStream("/druid.properties");

        pro.load(fis);

        System.out.println(pro);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);

        Connection conn = dataSource.getConnection();
        System.out.println(dataSource.getClass());

        //从数据源中得到10个连接对象
        for (int i = 1; i <= 11; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println("第" + i + "个连接对象：" + connection);
            if (i == 4) {
                connection.close();
                //放回到连接池中
                }

                String sql = "select * from emp where name=? and city=?";

                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, "刘备");
                stmt.setString(2, "北京");

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String city = rs.getString(3);
                    System.out.println(id + ":::" + name + "===" + city);
                }
//        else {
//            System.out.println("没有查到对应的用户信息!");
//        }

//                JDBCUtils.release(conn, stmt, rs);


            }
        }}

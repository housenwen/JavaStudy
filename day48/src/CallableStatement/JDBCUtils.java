package CallableStatement;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static String driverClass = null;
    static String url = null;
    static String user = null;
    static String password = null;

    static {
        // 需求： 通过properties对象读取 外部配置的内容
        Properties prop = new Properties();

        try {
            FileInputStream in=new FileInputStream("jdbc.properties");
            // 加载外部的配置文件
            prop.load(in);
            // 读取外部配置文件的内容
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            // 注册驱动
            Class.forName(driverClass);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    // 获得连接
    public static Connection getConnection() {
        Connection con = null;
        try  {
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
    public static void release(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("rs:" + e.getMessage());
        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("stmt:" + e.getMessage());
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("conn:" + e.getMessage());
        }
    }

}


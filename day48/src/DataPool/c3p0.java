package DataPool;

import CallableStatement.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class c3p0 {
    @Test
    public void test() throws Exception{
        // 需求：遍历emp的用户。
        // 首先创建c3p0的数据库连接池构造方法ComboPooledDataSource(configName) configName表示新增的名字
        ComboPooledDataSource cpds = new ComboPooledDataSource("day04_db");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 从连接池中获取连接
            conn = cpds.getConnection();
            // 获得发送sql的对象
            String sql = "select * from emp";
            stmt = conn.prepareStatement(sql);
            // 执行sql 获得结果
            rs = stmt.executeQuery();
            // 处理结果
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("name");
                String password= rs.getString("password");

                System.out.println("C3P0::::: " + id + ">>>" + username + ">>>" + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源: 关闭stmt 和 rs
//            JDBCUtils.release(conn, stmt, rs);
        }
    }
}

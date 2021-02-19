package c3p0;

import Statement.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test {
    public static void main(String[] args) throws Exception{
        // 需求：遍历emp的用户。
        // 首先创建c3p0的数据库连接池
        ComboPooledDataSource cpd = new ComboPooledDataSource();
        Connection conn = null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        // 从连接池中获取连接
        conn = cpd.getConnection();
        // 获得发送sql的对象
        String sql = "select * from emp";
        stmt = conn.prepareStatement(sql);
        // 执行sql 获得结果
        rs = stmt.executeQuery();
        // 处理结果
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String city = rs.getString(3);
            System.out.println("C3P0::::: " + id + ">>>" + name + ">>>" + city);
        }
        JDBCUtils.release(conn,stmt,rs);
    }
}

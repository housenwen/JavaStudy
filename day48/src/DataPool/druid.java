package DataPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class druid {

        public static void main(String[] args) throws Exception {
            //加载properties文件的内容到Properties对象中
            Properties info = new Properties();
            //加载项目下的属性文件 相对项目根目录
        FileInputStream fis = new FileInputStream("druid.properties");
            //相对src目录
//            InputStream fis = Test01.class.getClassLoader().getResourceAsStream("druid.properties");
            //从输入流中加载属性
            info.load(fis);
            System.out.println(info);
            //创建DRUID连接池，使用配置文件中的参数
            DataSource dataSource = (DataSource) DruidDataSourceFactory.createDataSource(info);
            //从DRUID连接池中取出连接
            //Connection conn = dataSource.getConnection();
            //System.out.println("conn = " + conn);
// 需求: 根据用户名和密码 查询用户信息
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // 获得连接
                conn = dataSource.getConnection();
                // 获得发送sql的对象
                String sql = "select * from emp where name=? and city=?";
                pstmt = conn.prepareStatement(sql);
                // 如果有问号,需要 设置参数,注意:下标从1开始
                pstmt.setString(1, "刘备");
                pstmt.setString(2, "北京");
                // 执行sql 获得结果
                rs = pstmt.executeQuery();
                // 处理结果
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String city = rs.getString("city");

                    System.out.println(id + ":::" + name + "===" + city);
                } else {
                    System.out.println("没有查到对应的用户信息!");
                }
            } catch (Exception e) {
            } finally {
//            JDBCUtils.release(conn, pstmt, rs);
            }
        }
    }


package Connection;

import Statement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test4 {
    public static void main(String[] args) throws Exception{
        // 需求: 根据用户名和密码 查询用户信息
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 获得连接
            conn = JDBCUtils.getConnection();
            // 获得发送sql的对象
            String sql = "select * from user where user_name=? and telephone=?";
            pstmt = conn.prepareStatement(sql);
            // 如果有问号,需要 设置参数,注意:下标从1开始
            pstmt.setString(1, "小明");
            pstmt.setString(2, "133808029292");
            // 执行sql 获得结果
            rs = pstmt.executeQuery();
            // 处理结果
            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String pwd = rs.getString(3);

                System.out.println(id + ":::" + username + "===" + pwd);
                System.out.println("登陆成功！");
            } else {
                System.out.println("没有查到对应的用户信息!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, pstmt, rs);
        }
    }
}

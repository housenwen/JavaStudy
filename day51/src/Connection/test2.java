package Connection;

import Statement.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class test2 {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Statement stmt= conn.createStatement();
        String uname = "小花";
        String telephone = "13530303";
        String sql = "SELECT * FROM USER WHERE user_name='"+uname+"' AND telephone='"+telephone+"'";
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()){
            String id = rs.getString(1);
            String user_name = rs.getString(2);
            String tel = rs.getString(3);
            System.out.println(id+":::"+user_name+"==="+tel);
            System.out.println("登录成功");
        }else {
            System.out.println("登陆失败");
        }
        JDBCUtils.release(conn,stmt,rs);


    }
}

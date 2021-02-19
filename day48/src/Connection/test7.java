package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test7 {
    public static void main(String[] args) throws Exception{
        //todo 4）查询客户信息，并封装成客户类返回；
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("select * from user where user_name=?");
        pstm.setString(1,"小明");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            String s1 = rs.getString(1);
            String s2 = rs.getString(2);
            String s3 = rs.getString(3);
            String s4 = rs.getString(4);
            User user = new User();
            user.setId(s1);
            user.setUserName(s2);
            user.setTelephone(s3);
            user.setAddress(s4);
            System.out.println(user);
        }

        JDBCUtils.release(conn,pstm,rs);
    }
}

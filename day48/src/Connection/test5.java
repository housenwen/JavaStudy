package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test5 {
    public static void main(String[] args) throws SQLException {
        //todo 2）查询客户A有哪些账户信息？
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("select account.* from account,user " +
                "where account.user_id=user.id and user.user_name=?");
        pstm.setString(1,"小明");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            String s1 = rs.getString(1);
            String s2 = rs.getString(2);
            String s3 = rs.getString(3);
            Double s4 = rs.getDouble(4);
            System.out.println(s1+' '+s2+' '+s3+' '+s4);
        }
        JDBCUtils.release(conn,pstm,rs);
    }
}

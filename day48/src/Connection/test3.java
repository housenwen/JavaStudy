package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class test3 {
    public static void main(String[] args) throws Exception{
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into account values(?,?,?,?)");
        pstm.setString(1,"4");
        pstm.setString(2,"3");
        pstm.setString(3,"普通卡");
        pstm.setDouble(4,500);
        pstm.executeUpdate();
        JDBCUtils.release(conn,pstm,null);
    }
}

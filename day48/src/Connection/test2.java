package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test2 {

    //todo 1)完成客户，账户，交易明细信息的录入功能；
    public static void main(String[] args) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into user values(?,?,?,?)");
        pstm.setString(1,"3");
        pstm.setString(2,"小花");
        pstm.setString(3,"13530303");
        pstm.setString(4,"南京");
        pstm.executeUpdate();
        JDBCUtils.release(conn,pstm,null);

    }

}

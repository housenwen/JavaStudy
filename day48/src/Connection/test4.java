package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class test4 {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into transaction_details values(?,?,?,?,?,?)");
        pstm.setString(1,"4");
        pstm.setString(2,"网银");
        pstm.setString(3,"3");
        pstm.setString(4,"4");
        pstm.setDate(5, Date.valueOf("2020-03-04"));
        pstm.setDouble(6,200.00);
        pstm.executeUpdate();
        JDBCUtils.release(conn,pstm,null);

    }
}

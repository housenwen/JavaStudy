package Connection;

import CallableStatement.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test6 {
    public static void main(String[] args) throws SQLException {
        //todo 3）查询客户A在2020年的支出总金额；
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement("select sum(td.tr_amount) as allMoney from user," +
                "account,transaction_details as td " +
                "where user.id=account.user_id " +
                "and account.account_id=td.tr_acc_id " +
                "and user.user_name=?;");
        pstm.setString(1, "小明");
//        pstm.setString(2, "小明");
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            double aDouble = rs.getDouble(1);
            System.out.println(aDouble);
        }
        JDBCUtils.release(conn, pstm, rs);
    }
}

package CallableStatement;

import Statement.JDBCUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class test1 {
    public static void main(String[] args) throws Exception{

        Connection conn = JDBCUtils.getConnection();
        String sql = "{call transfer(?,?,?,?)}";
        CallableStatement cst = conn.prepareCall(sql);
        cst.setString(1,"a");
        cst.setString(2,"b");
        cst.setFloat(3,100f);

        cst.registerOutParameter(4, Types.INTEGER);

        cst.execute();
        int result = cst.getInt(4);
        System.out.println("返回的结果为："+result);

        JDBCUtils.release(conn,cst,null);

    }
}

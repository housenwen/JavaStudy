package Connection;

import Statement.JDBCUtils;

import java.sql.Connection;
import java.sql.Statement;

public class test1 {
    public static void main(String[] args) throws Exception {
        Connection con = JDBCUtils.getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE account set money=money-100 where name ='a'");
//        int x = 1 / 0;
        stmt.executeUpdate("UPDATE account SET money = money + 100 WHERE name='b'");
        con.commit();

        try {
            if (con!=null){
                con.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

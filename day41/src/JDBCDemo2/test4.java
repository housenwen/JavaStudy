package JDBCDemo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test4 {
    public static void main(String[] args) throws SQLException {
        //todo 1.建立连接 参数1：协议+访问的数据库。参数2：用户名。参数3：密码。
        //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "root", "root");

        //todo 2. 创建statement ， 跟数据库打交道，一定需要这个对象
        Statement statement = connection.createStatement();

        String sql = "update t_stu set age= 26 where name ='张三'";
        int result = statement.executeUpdate(sql);

        if(result >0 ){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }

    }
}

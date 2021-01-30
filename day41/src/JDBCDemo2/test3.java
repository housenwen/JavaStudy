package JDBCDemo2;

import java.sql.*;

public class test3 {
    public static void main(String[] args) throws SQLException {
        //todo 1.建立连接 参数1：协议+访问的数据库。参数2：用户名。参数3：密码。
        //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "root", "root");

        //todo 2. 创建statement ， 跟数据库打交道，一定需要这个对象
        Statement statement = connection.createStatement();

        String sql = "select * from t_stu";
        ResultSet set = statement.executeQuery(sql);

        while (set.next()){
            String name = set.getString("name");
            int age = set.getInt("age");

            System.out.println(name + "   " + age);
        }

    }
}

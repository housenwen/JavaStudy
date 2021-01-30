package JDBCDemo2;

import java.sql.*;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //todo 1.注册驱动 ->防二次注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //todo 1.建立连接 参数1：协议+访问的数据库。参数2：用户名。参数3：密码。
        //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "root", "root");

        //todo 2. 创建statement ， 跟数据库打交道，一定需要这个对象
        Statement statement = connection.createStatement();


        String sql = "select * from t_stu";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");

            System.out.println(name+" \t "+age);
        }

//        insert
//        INSERT INTO t_stu (NAME , age) VALUES ('wangqiang',28)

        String sql1 = "insert into t_stu values(4,'aobama',59)";
        int result = statement.executeUpdate(sql1);
        if (result>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

    }
}

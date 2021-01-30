package JDBCdemo;

import java.sql.*;

public class Maintest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {



        /**
         * 1. 资源释放工作的整合
         * 2. 驱动防二次注册
         *    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         *    	Driver 这个类里面有静态代码块，一上来就执行了，所以等同于我们注册了两次驱动。 其实没这个必要的。
         *    	//静态代码块 ---> 类加载了，就执行。 java.sql.DriverManager.registerDriver(new Driver());
         */


//        Driver driver = new com.mysql.jdbc.Driver();
//        DriverManager.registerDriver(driver);
        //todo 1.注册驱动 ->防二次注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //todo 2.建立连接 参数1：协议+访问的数据库。参数2：用户名。参数3：密码。
        //DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/student", "root", "root");

        //todo 3. 创建statement ， 跟数据库打交道，一定需要这个对象
        Statement statement = connection.createStatement();



       //todo 4. 执行查询 ， 得到结果集 执行sql ，得到ResultSet

        String sql = "select * from t_stu";
        ResultSet resultSet = statement.executeQuery(sql);

        //todo 5. 遍历查询每一条记录
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            System.out.println("id="+id + "===name="+name+"==age="+age);

        }

//        todo 6.释放资源
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqlEx) { } // ignore
            resultSet = null;
        }

    }
}



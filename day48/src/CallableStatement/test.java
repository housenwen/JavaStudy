package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class test {
    public static void main(String[] args) throws SQLException {

//1）获取连接Connection的对象
        Connection conn = JDBCUtils.getConnection();
        //2.编写sql
        // 存储过程中的参数使用 ? 占位
        //存储过程：CREATE  procedure transfer(fromSub VARCHAR(20),toSub VARCHAR(20),m FLOAT,OUT flag INT)
        String sql = "{call transfer(?,?,?,?)}";//transfer表示调用的存储过程的名字
        //3.获取CallableStatement

        /*
            CallableStatement prepareCall(String sql) 创建一个 CallableStatement 对象来调用数据库存储过程。
         */
        CallableStatement cst = conn.prepareCall(sql);
        //4.设置真实参数
        cst.setString(1,"tom"); // 给第一个?设置真实值
        cst.setString(2,"rose"); // 给第二个?设置真实值
        cst.setFloat(3,100f); // 给第三个?设置真实值
        /*
            	在执行存储过程之前，必须注册所有 OUT 参数的类型。注册参数类型使用registerOutParameter方法来完成的。
                void registerOutParameter(int parameterIndex, int sqlType)
          			按顺序位置 parameterIndex 将 OUT 参数注册为 JDBC 类型 sqlType。
                    	参数：
                    	parameterIndex：第一个参数是 1，第二个参数是 2，依此类推
                    	sqlType：java.sql.Types 定义的 JDBC 类型代码。例如：Types.INTEGER
         */
        cst.registerOutParameter(4, Types.INTEGER);
        //5.执行sql:boolean execute() 在此 PreparedStatement 对象中执行 SQL 语句，该语句可以是任何种类的 SQL 语句。
        cst.execute();

        /*
            6.获取返回值:使用CallableStatement的getXXX方法将取回参数值:
            int getInt(int parameterIndex) 以 Java 编程语言中 int 值的形式获取指定的 JDBC的INTEGER 参数的值。
            参数：表示获取的值属于第几个占位符，上述占位符中属于第四个，所以这里写4
         */
        int result = cst.getInt(4);
        System.out.println("返回结果为: "+result);
        //关流
//        JDBCUtils.release(conn,cst,null);
    }
}

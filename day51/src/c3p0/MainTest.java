package c3p0;

import java.sql.*;

public class MainTest {
    public static void main(String[] args) throws Exception {


        try {

            //1.注册驱动
//            Driver driver = new com.mysql.jdbc.Driver() ;
//            DriverManager.registerDriver(driver) ;

            //2.建立连接
            Connection conn = DriverManager.getConnection("jdbc:mysql:///day04_db", "root", "root");
            //3.创建statement,
            Statement st = conn.createStatement();

            //4.执行查询，得到结果
            String sql = "select * from user";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {


//                int uid = rs.getInt("uid");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String iphone = rs.getString("phone");

                int uid = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String iphone = rs.getString(4);
                System.out.println(uid + " " + username + " " + password + " " + iphone);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

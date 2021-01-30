package JDBCdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DriverManager2 {
    public static void main(String[] args) throws IOException {
        String driverClass = null;
        String url = null;
        String name = null;
        String password = null;
        //todo 1.创建一个属性配置。
        Properties properties = new Properties();

        //todo 2.导入输入流。
        InputStream in = new FileInputStream("JDBC.properties");

        //todo 使用类加载器去读取src文件下的资源文件，后面在servlet
//        InputStream in = DriverManager2.class.
//                getResourceAsStream("JDBC.properties");
        properties.load(in);
        //todo 3.读取属性
        name = properties.getProperty("name");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driverClass = properties.getProperty("driverClass");

        System.out.println(name);
        System.out.println(password);
        System.out.println(url);
        System.out.println(driverClass);
    }
}

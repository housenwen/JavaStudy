import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Demo {

    public static void main(String[] args) throws Exception {
        OracelDriverClass oracelDriverClass = new OracelDriverClass();
        oracelDriverClass.print();

        //jdbc操作数据库
        Class.forName("");
        Connection connection = DriverManager.getConnection("");
        PreparedStatement ps = connection.prepareStatement("");
        ps.executeUpdate();
    }
}

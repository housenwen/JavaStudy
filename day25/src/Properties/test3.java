package Properties;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class test3 {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
        pro.load(new FileReader("test2_1.txt"));

        Set<String> strings = pro.stringPropertyNames();
        for (String key:strings){
            System.out.println(key+"..."+pro.getProperty(key));
        }

    }
}

package Properties;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class test2 {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
        pro.setProperty("品名","IPhone 11 max");
        pro.setProperty("颜色","暗绿色");
        pro.setProperty("储存容量","256G");
        pro.setProperty("价格","10999");

        FileWriter fw = new FileWriter("test2_1.txt");

        Set<String> strings = pro.stringPropertyNames();

        for (String key:strings){
            System.out.println(key+"="+pro.getProperty(key));
            fw.write(key+"="+pro.getProperty(key));
//            fw.write("\r\n");
            fw.write(System.getProperty("line.separator"));
        }
        fw.flush();
    }
}

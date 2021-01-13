package Properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class test4 {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
        BufferedReader br = new BufferedReader(new FileReader("test2_1.txt"));
        String line;
        while ((line=br.readLine())!=null){
            String [] str = line.split("=");
            pro.setProperty(str[0],str[1]);
        }
        Set<String> keySet = pro.stringPropertyNames();

        for (String key:keySet){
            System.out.println(key+"="+pro.getProperty(key));
        }
        br.close();
    }
}

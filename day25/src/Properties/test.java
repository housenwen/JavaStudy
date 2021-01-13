package Properties;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class test {
    public static void main(String[] args) throws IOException {

//        Hashtable properties = new Properties();
        Properties properties = new Properties();

//        properties.put("fileName","a.txt");
//        properties.put("length","209385038");
//        properties.put("location","d:\\a.text");
        properties.setProperty("fileName","a.txt");
        properties.setProperty("length","209385038");
        properties.setProperty("location","d:\\a.text");

        System.out.println(properties);

        System.out.println(properties.get("fileName"));
        System.out.println(properties.get("length"));
        System.out.println(properties.get("location"));

        Set<String> strings = properties.stringPropertyNames();
        for (String key:strings){
            System.out.println(key+"====="+properties.getProperty(key));
        }


    }
}

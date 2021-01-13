package ClassWord;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class test1 {
    public static void main(String[] args) throws IOException {

        Properties pro = new Properties();

        BufferedReader br = new BufferedReader(new FileReader("in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output"));


        String line = null;

        while ((line = br.readLine()) != null) {
            String[] strings = line.split("\\.");
            pro.setProperty(strings[0], strings[1]);
        }
        br.close();

        Set<String> setKey = pro.stringPropertyNames();

        for (int i = 1; i <= setKey.size(); i++) {

            String key = String.valueOf(i);
            String value = pro.getProperty(key);
            String s = key + "=" + value;

            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }
}

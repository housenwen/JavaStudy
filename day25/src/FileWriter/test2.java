package FileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("fw",true);

        char[] chars = "黑马程序员".toCharArray();
        fw.write("\r\n");

        fw.write(chars);

//        fw.write(chars,2,2);
//        fw.write(String.valueOf("\r\n".getBytes()));
        fw.write("\r\n");

        char[] chars1 = "我最帅".toCharArray();
        fw.write(chars1);
        fw.write("\r\n");


        fw.flush();
        fw.close();

    }
}

package ClassWork;

import java.io.FileWriter;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("bbb.txt");

//        char[] chars = "Hello World".toCharArray();
//
//        fw.write(chars);
        fw.write("Hello world");

        fw.flush();

    }
}

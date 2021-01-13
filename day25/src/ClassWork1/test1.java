package ClassWork1;

import java.io.FileOutputStream;
import java.io.IOException;

public class test1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("d:\\aaa.txt");

        char[] chars = "I love Java".toCharArray();

        for (char c:chars){
            fos.write((byte) c);
//            fos.write("\r\n".getBytes());
        }

       fos.close();
    }
}

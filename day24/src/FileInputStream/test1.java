package FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class test1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("read.txt");
        int read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println((char) read);
        read = fis.read();
        System.out.println( read);

        fis.close();
    }
}

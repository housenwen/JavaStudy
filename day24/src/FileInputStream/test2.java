package FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("read.txt");
        int b;
        while ((b=fis.read())!=-1){
            System.out.println((char) b);

        }
        fis.close();
    }
}

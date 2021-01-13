package ClassWord;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("jdk8.txt");
        FileOutputStream fos = new FileOutputStream("copy.txt");

        int b;

        while ((b=fis.read())!=-1){
            fos.write(b);
        }

        fis.close();
        fos.close();

        long end = System.currentTimeMillis();

        System.out.println(end-start);
    }

}

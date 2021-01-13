package ClassWork1;

import java.io.FileInputStream;
import java.io.IOException;


public class test3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("data");
//        FileOutputStream fos = new FileOutputStream("data");

        byte[] buffer = new byte[1024];
        int len = -1;

        while ((len = fis.read(buffer)) != -1) {
            System.out.print(new String(buffer,0,len));
        }

        fis.close();
//        fos.close();
    }
}

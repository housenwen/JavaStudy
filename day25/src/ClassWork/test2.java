package ClassWork;

import java.io.FileInputStream;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("data.txt");

        int len;

        byte[] b = new byte[1024];

        while ((len=fis.read(b))!=-1){
            System.out.println(new String(b,0,len));
        }
    }
}

package FOS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test01 {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("d:\\test.txt");

        FileOutputStream fos = new FileOutputStream("testCopy.txt",true);

        byte[] words = new byte[1024];

        int len;


        while ((len=fis.read(words))!=-1){

            fos.write(words,0,len);
            fos.write("\r\n".getBytes());

        }

        fis.close();
        fos.close();

    }
}

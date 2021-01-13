package PrintStream;

import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("aaa");

        FileOutputStream fos = new FileOutputStream("bbb");

//        IOUtils.copy(fis,fos);

        int b;
        while ((b=fis.read())!=-1){
            fos.write((char) b);
        }

        fis.close();
        fos.close();


    }
}

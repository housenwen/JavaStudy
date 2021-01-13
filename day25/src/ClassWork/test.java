package ClassWork;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class test {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("data");
        FileOutputStream fos = new FileOutputStream("data",true);


        fos.write(97);

        int b ;

        while ((b=fis.read())!=-1){
            System.out.println((char) b);

        }

        fis.close();
        fos.close();

    }
}

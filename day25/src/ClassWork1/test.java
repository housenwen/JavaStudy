package ClassWork1;

import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("d:\\aaa.txt",true);

        fos.write("\r\n".getBytes());
        fos.write(97);

        fos.close();
    }
}

package FileOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class work3  {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.txt",true);
        byte[] b = "abcdefg".getBytes();

        fos.write(b);

        fos.close();


    }
}

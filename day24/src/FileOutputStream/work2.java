package FileOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class work2 {
    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("fos.txt");

        byte[] b = "abcdefg".getBytes();

        fos.write(b,2,2);

        fos.close();

    }
}

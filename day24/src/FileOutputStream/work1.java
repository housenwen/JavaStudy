package FileOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class work1 {
    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("fos.txt");

        byte[] b = "黑马程序员".getBytes();

        fos.write(b);

        fos.close();

    }
}

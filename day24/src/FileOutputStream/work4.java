package FileOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class work4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("fos.txt",true);

        byte[] words = {97,98,99,100,101};

        for (int i = 0; i < words.length; i++) {
            fos.write(words[i]);
            fos.write("\r\n".getBytes());
        }
        fos.close();

    }
}

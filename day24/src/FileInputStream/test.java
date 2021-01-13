package FileInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {

        File file = new File("read.txt");
        FileInputStream fis = new FileInputStream(file);

        FileInputStream fis1 = new FileInputStream("b.txt");

    }
}

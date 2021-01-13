package FileReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;


public class test {
    public static void main(String[] args) throws IOException {

        File file = new File("d:\\test.txt");
        FileReader fr = new FileReader(file);

        FileReader fr1 = new FileReader("d:\\aaa.txt");


    }
}

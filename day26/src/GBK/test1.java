package GBK;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class test1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("d:\\1.txt");
        FileReader fr = new FileReader("bbb");
        int read;

        while ((read=fis.read())!=-1){
            System.out.println((char) read);
        }
        fis.close();
    }
}

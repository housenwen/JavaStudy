package GBK;

import java.io.FileReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("d:\\1.txt");

        int read;

        while ((read=fr.read())!=-1){
            System.out.println((char) read);
        }

        fr.close();
    }
}

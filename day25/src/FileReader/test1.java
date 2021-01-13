package FileReader;

import java.io.FileReader;
import java.io.IOException;

public class test1 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("test_copy");

        int b ;

        while ((b=fr.read())!=-1){
            System.out.println((char) b);

        }
        fr.close();

    }
}

package ClassWork1;

import java.io.FileInputStream;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("bbb.txt");

        int temp ;

        while ((temp=fis.read())!=-1){
            System.out.println((char) temp);
        }
        fis.close();

    }
}

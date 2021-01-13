package ClassWork;

import java.io.FileInputStream;
import java.io.IOException;

public class test4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("fw");

        int temp ;

        while ((temp=fis.read())!=-1){
            System.out.println((char) temp);
        }
        fis.close();
    }
}

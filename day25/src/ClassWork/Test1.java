package ClassWork;

import java.io.FileReader;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException{

        FileReader  fr= new FileReader("Student.txt");

        int len;

//        byte[] b = new byte[1024];

        while ((len=fr.read())!=-1){

            System.out.println((char) len);

        };



    }
}

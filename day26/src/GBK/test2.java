package GBK;

import org.omg.CORBA.FREE_MEM;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test2 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("d:\\1.txt"));
//        InputStreamReader isr1 = new InputStreamReader(new FileInputStream("bbb") , "GBK");
        InputStreamReader isr1 = new InputStreamReader(new FileInputStream("bbb"));


        int read;
        while ((read=isr.read())!=-1){
            System.out.println((char) read);
        }
        isr.close();

        while ((read=isr1.read())!=-1){
            System.out.println((char) read);
        }
        isr1.close();

    }
}

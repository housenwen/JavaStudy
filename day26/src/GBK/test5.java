package GBK;

import java.io.*;

public class test5 {
    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(new FileInputStream("output"),"GBK");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("bbb"));

        int read ;

        char[] bytes = new char[1024];

        while ((read=isr.read(bytes))!=-1){
            osw.write(bytes,0,(char) read);
        }
        isr.close();
        osw.close();

    }
}

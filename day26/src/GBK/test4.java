package GBK;

import java.io.*;

public class test4 {
    public static void main(String[] args) throws IOException {

        InputStreamReader isr = new InputStreamReader(new FileInputStream("d:\\1.txt"),"GBK");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("aaa"));
        int read;

        while ((read=isr.read())!=-1){
            osw.write((char) read);
        }
        isr.close();
        osw.close();

    }
}

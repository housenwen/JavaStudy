package BufferedInPutStream;

import java.io.*;

public class test1 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("jdk8.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.txt"));

        int len;
        byte[] bytes = new byte[1024];

        while ((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();

    }
}

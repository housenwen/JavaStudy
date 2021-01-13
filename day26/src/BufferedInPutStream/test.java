package BufferedInPutStream;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("jdk8.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.txt"));

        int b;

        while ((b=bis.read())!=-1){
            bos.write(b);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        bis.close();
        bos.close();

    }
}

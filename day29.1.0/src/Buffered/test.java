package Buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bufin = new BufferedInputStream(new FileInputStream(
                "123.txt"));//itcast表示模块名
        byte[] b = new byte[1024];
        int len = 0;
//        while ((len = bufin.read(b)) != -1) {System.out.println(new String(b));}
        len = bufin.read(b);
        System.out.println(new String(b,0,len));
        bufin.close();
    }
}

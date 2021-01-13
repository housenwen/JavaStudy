package FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class test4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("read.txt");

        int len;
        byte[] b = new byte[2];
        while ((len=fis.read(b))!=-1){
            // 每次读取后,把数组的有效字节部分，变成字符串打印
            System.out.println(new String(b,0,len));//  len 每次读取的有效字节个数
        }

        fis.close();
    }
}

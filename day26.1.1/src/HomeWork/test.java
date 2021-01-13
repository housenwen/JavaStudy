package HomeWork;

import java.io.*;
// TODO 请将D:盘下的一个文件复制到E:盘下，例如：d:\视频.itcast，复制到E:\视频.itcast。
//
//   TODO   请使用“字节缓冲流”：BufferedInputStream和BufferedOutputStream实现

public class test {
    public static void main(String[] args) throws IOException {

        BufferedInputStream bis= new BufferedInputStream(new FileInputStream("d:\\aaa.txt"));

        BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream("e:\\bbb.txt"));

        int b;

        while ((b=bis.read())!=-1){
            bos.write((char) b);
        }

        bis.close();
        bos.close();
    }
}

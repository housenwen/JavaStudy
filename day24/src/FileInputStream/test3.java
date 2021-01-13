package FileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("read.txt");
        int len;
        //TODO 定义字节数组，作为装字节数据的容器
        byte [] b = new byte[2];
        while ((len=fis.read(b))!=-1){
            System.out.println(new String(b));
        }
        fis.close();
    }
}

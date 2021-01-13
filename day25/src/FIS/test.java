package FIS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
// 1.创建流对象
        // 1.1 指定数据源
        FileInputStream fis = new FileInputStream("d:\\test.txt");
        // 1.2 指定目的地
        FileOutputStream fos = new FileOutputStream("test_copy",true);

        // 2.读写数据
        // 2.1 定义数组

        byte [] b = new byte[1024];

        int len;
// 2.3 循环读取
        while ((len=fis.read(b))!=-1){
            // 2.4 写出数据
            fos.write(b,0,len);
            fos.write("\r\n".getBytes());
        }
        fos.close();
        fis.close();

    }
}

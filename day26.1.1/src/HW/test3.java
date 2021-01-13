package HW;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class test3 {
    public static void main(String[] args) throws Exception {
        // 定义文件路径
        String fileName = "test4_1.txt";
        // 2.创建流对象
        // 2.1 转换输入流,指定GBK编码
        Reader isr = new InputStreamReader(new FileInputStream(fileName) , "GBK");
        // 3.读写数据
        // 3.1 定义数组
        char[] cbuf = new char[1024];
        // 3.2 定义长度
        int len;
        // 3.3 循环读取
        while ((len = isr.read(cbuf))!=-1) {
            // 循环写出
            System.out.println(new String(cbuf,0,len));
        }
        // 4.释放资源

        isr.close();

    }
}

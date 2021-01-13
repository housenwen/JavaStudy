package GBK;

import java.io.*;

public class test6 {
    public static void main(String[] args) throws IOException{
        // 1.定义文件路径
        String srcFile = "aaa";
        String destFile = "bbb";
        // 2.创建流对象
        // 2.1 转换输入流,指定GBK编码
        Reader isr = new InputStreamReader(new FileInputStream(srcFile));
        // 2.2 转换输出流,默认utf8编码
        Writer osw = new OutputStreamWriter(new FileOutputStream(destFile),"GBK");
        // 3.读写数据
        // 3.1 定义数组

        // 3.2 定义长度
        int len;
        // 3.3 循环读取
        while ((len = isr.read())!=-1) {
            // 循环写出
            osw.write((char) len);
        }
        // 4.释放资源
        osw.close();
        isr.close();
    }
}

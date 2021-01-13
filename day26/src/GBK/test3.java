package GBK;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class test3 {
    public static void main(String[] args) throws IOException {
        // 定义文件路径
        String FileName = "aaa";
        // 创建流对象,默认UTF8编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(FileName));
        // 写出数据
        osw.write("你好"); // 保存为6个字节
        osw.close();

        // 定义文件路径
        String FileName2 = "bbb";
        // 创建流对象,指定GBK编码
        OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream(FileName2),"GBK");
        // 写出数据
        osw2.write("你好");// 保存为4个字节
        osw2.close();
    }
}

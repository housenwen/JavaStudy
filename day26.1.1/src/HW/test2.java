package HW;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class test2 {
    public static void main(String[] args) throws IOException {
        // 定义文件路径
        String FileName = "test4_1.txt";
        // 创建流对象,GBK编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(FileName),"GBK");
        osw.write("我要学好Java，我要月薪过万！！");
        osw.close();

    }
}

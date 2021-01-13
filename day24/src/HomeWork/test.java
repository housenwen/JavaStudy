package HomeWork;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
// 使用File对象创建流对象
        File file = new File("read.txt");
        FileOutputStream fos = new FileOutputStream(file);
        // 使用文件名称创建流对象
        FileOutputStream fos1 = new FileOutputStream("b.txt");

    }
}

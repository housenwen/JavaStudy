package commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {
//       todo 两个参数：参数一：文件
//        参数二：字符集
        String s = FileUtils.readFileToString(new File("test11"),"UTF-8");
        System.out.println(s);

        System.out.println("-------------------------");

        //todo 读内容到字节数组
        byte[] bytes = FileUtils.readFileToByteArray(new File("test11"));
        System.out.println(bytes.length);

        System.out.println("------------------------------");

        //todo 逐行读取
        List<String> list = FileUtils.readLines(new File("test11"));
        for (String str:list){
            System.out.println(str);
        }

        System.out.println("----------------------------------");

        //todo 迭代器读取：
        LineIterator iterator = FileUtils.lineIterator(new File("test11"));
        while (iterator.hasNext()){
            System.out.println(iterator.nextLine());
        }

    }
}

package CK;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class test2 {
    public static void main(String[] args) throws IOException {
//       todo 两个参数：参数一：文件
//        参数二：字符集
        File file = new File("text2_1.txt");
        String s = FileUtils.readFileToString(file,"UTF-8");
        System.out.println(s);

        System.out.println("--------------------------");
//todo 读内容到字节数组
        byte[] bytes = new byte[1024];
        bytes = FileUtils.readFileToByteArray(file);
//        System.out.println(Arrays.toString(bytes));
        for (byte b:bytes){
            System.out.print(((char) b));
        }
        System.out.println("---------------------------");
        //todo 逐行读取
        List<String> list = FileUtils.readLines(file);
        for (String str:list){
            System.out.println(str);
        }
        System.out.println("--------------------");
        //todo 迭代器读取：
        LineIterator iterator = FileUtils.lineIterator(file);
        while (iterator.hasNext()){
            System.out.println(iterator.nextLine());
        }

    }
}

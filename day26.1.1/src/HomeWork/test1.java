package HomeWork;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
////TODO 请定义“字符缓冲输出流”BufferedWriter将集合中的数据写入到文件：temp.txt中，每个名字一行。
public class test1 {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();

        list.add("“迪丽热巴”");

        list.add("“古力娜扎”");

        list.add("“周杰伦”");

        list.add("“蔡徐坤”");

        BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));

        for (String s:list){
            bw.write(s);
            bw.newLine();
        }

        bw.close();

    }
}

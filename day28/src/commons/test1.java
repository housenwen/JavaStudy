package commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {

        //todo 写入字符串
        FileUtils.write(new File("b.txt"),
                "我们都是祖国的一朵花\r\n","UTF-8",true);
        FileUtils.write(new File("b.txt"),"\r\n","UTF-8",true);
        FileUtils.write(new File("b.txt"),
                "祖国母亲需要我们！","UTF-8",true);

        //todo 写入数组
        FileUtils.writeByteArrayToFile(new File("c.txt"),"上海交通大学".getBytes(),true);

        //todo 写入列表
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"北京","上海","南京");

        FileUtils.writeLines(new File("d.txt"),
                "UTF-8",list,"=",true);

    }
}

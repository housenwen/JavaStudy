package CK;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("“迪丽热巴”");
        list.add("“古力娜扎”");
        list.add("“周杰伦”");
        list.add("“蔡徐坤”");

        FileUtils.writeLines(new File("text2_1.txt"),
                "UTF-8",list,"\r\n",true);

    }
}

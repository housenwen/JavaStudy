package CK;

import org.apache.commons.io.FileUtils;

import javax.jnlp.ClipboardService;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class test4 {
    public static void main(String[] args) {
        try {
         List<String> list =  FileUtils.readLines(new File("test4_1.txt"));
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

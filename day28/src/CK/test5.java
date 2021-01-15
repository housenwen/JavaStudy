package CK;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class test5 {
    public static void main(String[] args) throws IOException {

        File file = new File("test4_1.txt");
        FileUtils.write(file,"我要学好Java，我要月薪过万！！","UTF-8",true);

    }
}

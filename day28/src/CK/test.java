package CK;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        File file1 = new File("d:\\aaa.txt");
        FileUtils.copyFileToDirectory(file1,new File("E:\\"));

    }
}

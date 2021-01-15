package commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class test2 {
    public static void main(String[] args) throws IOException {
        /**
         * 复制文件 ***
         */
        //todo 复制文件到文件或文件夹
        FileUtils.copyFile(new File("b.txt"),new File("bbb.txt"));
        FileUtils.copyFileToDirectory(new File("d.txt"),
                new File("d:\\develop\\JetBrains\\ideaproject\\day28\\HK"));

        //TODO 复制文件夹 -> 封装文件夹

        File oldFile = new File("d:\\develop\\JetBrains\\ideaproject\\day28\\HK");
        File newFile = new File("d:\\develop\\JetBrains\\ideaproject\\day28\\AK");

        //todo 复制文件夹
        FileUtils.copyDirectory(oldFile,newFile);
        //todo 复制文件夹到某个文件夹
        FileUtils.copyDirectoryToDirectory(oldFile,newFile);

    }
}

package commons;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.Collection;

//todo 获取文件的大小，文件夹大小，封装文件夹，获取子孙集，遍历集合，取路径。
public class test11 {
    public static void main(String[] args) {
        //获取文件的大小
        long length = FileUtils.sizeOf(new File("test11"));
        System.out.println(length);
        //获取文件夹大小
        long length1 = FileUtils.sizeOf(new File("d:\\多级目录"));
        System.out.println(length1);
        //封装文件夹
        File file = new File("d:\\develop\\JetBrains\\ideaproject\\day26");
//        File file = new File("d:\\develop\\JetBrains\\ideaproject\\day28");
        //获取其子孙集，返回的是Collection集合。
        // 参数一:文件  参数二：文件过滤器 参数三：目录过滤器
//        EmptyFileFilter.NOT_EMPTY:非空文件
//        new SuffixFileFilter(".java");后缀".java的文件";
//        DirectoryFileFilter.INSTANCE;全部子孙集
        Collection<File> files = FileUtils.listFiles(file,
                new SuffixFileFilter(".java"),DirectoryFileFilter.INSTANCE);
        //遍历集合，取路径
        for (File f :files){
            System.out.println(f.getAbsolutePath());
        }





    }
}

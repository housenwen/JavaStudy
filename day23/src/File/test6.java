package File;

import java.io.File;
import java.io.IOException;

public class test6 {
    public static void main(String[] args) throws IOException {
//        文件创建
        File f = new File("bbb.txt");
        System.out.println("是否存在"+f.exists());
        System.out.println("是否创建"+f.createNewFile());
        System.out.println("是否存在"+f.exists());

        System.out.println("---------------------");
//        目录创建
        File f2 = new File("newDir");
        System.out.println("是否存在"+f2.exists());
        System.out.println("是否创建"+f2.mkdir());
        System.out.println("是否存在"+f2.exists());

        System.out.println("----------------------");
//       创建多级目录
        File f3 = new File("newDira\\newDirb");
        System.out.println("是否创建"+f3.mkdir());
        File f4 = new File("newDira\\newDirb");
        System.out.println("是否创建"+f3.mkdirs());

        System.out.println("------------------");
//        文件删除
        System.out.println(f.delete());
        System.out.println("--------------------");
//        目录删除
        System.out.println(f2.delete());
        System.out.println(f4.delete());//TODO API中说明：delete方法，如果此File表示目录，则目录必须为空才能删除。

    }
}

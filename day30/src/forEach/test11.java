package forEach;

import java.util.Arrays;

public class test11 {
    public static void main(String[] args) {
//        把“E:\Workspace\201909\day14\RegexTest.java”文件路径，按照 \ 切割
        String name = "E:\\Workspace\\201909\\day14\\RegexTest.java";
        String[] splits = name.split("\\\\");
        System.out.println(Arrays.toString(splits));
    }
}

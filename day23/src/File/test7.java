package File;
import java.io.File;
import java.io.IOException;
//TODO 调用listFiles方法的File对象，表示的必须是实际存在的目录，否则返回null，无法进行遍历。
public class test7 {
    public static void main(String[] args) throws IOException {
        File dir = new File("d:\\ccc");

        //获取当前目录下的文件以及文件夹的名称。
        String[] names = dir.list();
        for (String name : names) {
            System.out.println(name);
        }
        //获取当前目录下的文件以及文件夹对象，只要拿到了文件对象，那么就可以获取更多信息
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
    }
}

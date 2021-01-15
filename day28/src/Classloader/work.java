package Classloader;

public class work {
    public static void main(String[] args) {

        System.out.println(work.class.getClassLoader());//todo sun.misc.Launcher$AppClassLoader
        System.out.println(String.class.getClassLoader());//todo null(API中说明：一些实现可能使用null来表示引导类加载器。
        //todo 如果此类由引导类加载器加载，则此方法将在此类实现中返回null。 )

    }
}

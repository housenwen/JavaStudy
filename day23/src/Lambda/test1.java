package Lambda;

public class test1 {
    public static void main(String[] args) {

        // TODO 请分别使用Lambda【标准格式】及【省略格式】调用invokeDirect方法

        invokeDirect(()->{
            System.out.println("张艺谋导演拍电影了！");
        });
        invokeDirect(()-> System.out.println("冯小刚导演拍电影了！"));

    }
    private static void invokeDirect(Director director) {
        director.makeMovie();
    }
}

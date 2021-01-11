package Login;
/**
 * //把方法写在类中好，还是main方法好？
 *
 *
 * try{} catch(){}和throws：
 * 工作中排查bug用哪个？
 * 自定义异常用哪个？
 *
 *
 * 自定义异常工作中常用吗？
 *
 */

public class Demo {
    public static void main(String[] args) throws LoException {
        LoException lo = new LoException();
        while (true) {
            try {
                lo.Log();
                break;
            } catch (LoException e) {
                System.out.println(e);
            }

        }

    }
}

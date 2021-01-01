package itheima;
//以上可以简化，代码如下：
public class InnerDemo2 {
    public static void main(String[] args) {
        /*
       	创建匿名内部类,直接传递给showFly(FlyAble f)
        */
        showFly( new FlyAble(){
            public void fly() {
                System.out.println("我飞了~~~");
            }
        });
    }

    public static void showFly(FlyAble f) {
        f.fly();
    }
}
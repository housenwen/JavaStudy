package itheima;

public class TestPhone {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        p1.setBrand("小米");
        p1.setColor("黑色");
        p1.setPrice(3998);
        p1.Call();
        p1.Message();

        Phone p2 = new Phone("苹果",5999,"白色");
        p2.Call();
        p2.Message();

    }
}

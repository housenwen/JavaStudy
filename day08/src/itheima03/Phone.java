package itheima03;

public class Phone {
    String brand;
    String price;
    public void Call(String name){
        System.out.println("打电话给"+name);
    }

    public void message(){
        System.out.println("群发短信");
    }
}
class TestPhone{

    public static void main(String[] args) {
        Phone P1 = new Phone();
        P1.brand = "苹果";
        P1.price = "3000";
        System.out.println(P1.brand+"手机价格"+P1.price);
        P1.Call("张三");
        P1.message();
    }
}

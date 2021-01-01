package itheima01;

public class BaoMa extends Car implements GPS{

    public BaoMa() {
    }

    public BaoMa(String breed, String price) {
        super(breed, price);
    }

    @Override
    public void gps() {
        System.out.println("我有GPS！");
    }
}

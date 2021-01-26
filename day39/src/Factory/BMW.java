package Factory;

public class BMW implements Car{
    @Override
    public void run() {
        System.out.println("宝马车时速80公里冲锋");
    }
}

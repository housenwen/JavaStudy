package Factory;

public class Benz implements Car{
    @Override
    public void run() {
        System.out.println("奔驰时速100公里冲刺");
    }
}

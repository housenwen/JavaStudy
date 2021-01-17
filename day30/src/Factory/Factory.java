package Factory;

public class Factory {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Car bmw = carFactory.createCar("bmw-718");
        bmw.run();
        Car benz = carFactory.createCar("benz-921");
        benz.run();
    }
}
interface Car {
    public void run();
}
 class Bmw implements Car {
    @Override
    public void run() {
        System.out.println("宝马以每小时500公里的速度在奔跑.....");
    }
}
 class Benz implements Car {
    @Override
    public void run() {
        System.out.println("奔驰汽车以每秒1米的速度在挪动.....");
    }
}
class CarFactory {
    /**
     * @param id : 车的标识
     *           bmw: 代表需要创建Bmw类对象
     *           benz : 代表需要创建Benz类对象
     *           如果传入的车标识不正确,代表当前工厂生成不了当前车对象,则返回null
     * @return
     */
    public Car createCar(String id){
//        if("bmw".equals(id)){
//            return new Bmw();
//        }
        if(id.contains("bmw")){
            return new Bmw();
        }
        else if(id.contains("benz")){
            return new Benz();
        }
        return null;
    }
}

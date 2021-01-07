package RentSystem;


import HeimaZuche.RentServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RentSystem {
    //租到的车list集合
    public static ArrayList<Vehicle> rentVehicle = new ArrayList<Vehicle>();
    //用来存储租车数据map集合
    public static Map<String,Map<String,ArrayList<Vehicle>>> data = new HashMap<>();
    //Map<类别, Map<品牌,List<机动车>>>

    //初始化数据
    static {
//        创建轿车和客车map集合
        HashMap<String,ArrayList<Vehicle>> mapCar = new HashMap<>();
        HashMap<String,ArrayList<Vehicle>> mapBus = new HashMap<>();

//        创建大众轿车的list集合
        ArrayList<Vehicle> dazong = new ArrayList<>();
        Vehicle vehicle1 = new CAR("200", "大众", "赣BTK001", "探歌280T");
        Vehicle vehicle2 = new CAR("300", "大众", "赣BTK002", "探歌140T");

        dazong.add(vehicle1);
        dazong.add(vehicle2);
        mapCar.put("大众", dazong);

//        创建奔驰轿车的list集合
        ArrayList<Vehicle> benchi = new ArrayList<Vehicle>();
        Vehicle vehicle3 = new CAR("400", "奔驰", "沪BTK003", "2021 C 200");
        Vehicle vehicle4 = new CAR("500", "奔驰", "沪BTK004", "2021 C 260");

        benchi.add(vehicle3);
        benchi.add(vehicle4);
        mapCar.put("奔驰",benchi);

        data.put("轿车",mapCar);

//        创建申沃客车list集合
        ArrayList<Vehicle> shenwo = new ArrayList<>();

        Vehicle vehicle5 = new BUS("1500", "申沃", "沪BTK666", 50);
        Vehicle vehicle6 = new BUS("1500", "申沃", "沪BTK777", 50);

        shenwo.add(vehicle5);
        shenwo.add(vehicle6);

        mapBus.put("申沃",shenwo);

        data.put("客车",mapBus);

    }

    public static void main(String[] args) {
        System.out.println("***************欢迎来到黑马租车***************");
        Scanner sc = new Scanner(System.in);
//        创建业务逻辑对象
        RentServiceImp1 rentS = new RentServiceImp1();

        while (true){
            System.out.println("请选择你的服务: 1) 租车服务   2) 还车服务  3) 退出系统");
            String choose = sc.nextLine();
            switch (choose){

                case "1":
                    rentS.rentFlower();
                    break;
                case "2":
                    rentS.returnFlower();
                    break;
                case "3":
                    System.out.println("谢谢使用再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }

        }

    }

}

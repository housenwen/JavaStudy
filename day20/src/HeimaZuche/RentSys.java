package HeimaZuche;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

public class RentSys {
    public static ArrayList<MotoVehicle> rentVehicles = new ArrayList<>();//租到的车list集合

    //用来存储数据map集合
    public static Map<String, Map<String, ArrayList<MotoVehicle>>> data = new HashMap<>();

    //Map<类别, Map<品牌,List<车>>>
    static {

        //初始化数据
        HashMap<String, ArrayList<MotoVehicle>> carBrand = new HashMap<>();
        ArrayList<MotoVehicle> dazong = new ArrayList<>();
        dazong.add(new Car("200", "大众", "赣BTK001", "探歌280T"));
        dazong.add(new Car("300", "大众", "赣BTK002", "探歌140T"));
        carBrand.put("大众", dazong);


        ArrayList<MotoVehicle> benchi = new ArrayList<>();
        benchi.add(new Car("400", "奔驰", "沪BTK003", "2021 C 200"));
        benchi.add(new Car("500", "奔驰", "沪BTK004", "2021 C 260"));
        carBrand.put("奔驰", benchi);

        data.put("轿车", carBrand);

        HashMap<String, ArrayList<MotoVehicle>> busBrand = new HashMap<>();
        ArrayList<MotoVehicle> shenwo = new ArrayList<>();
        shenwo.add(new Bus("1500", "申沃", "沪BTK666", 50));
        shenwo.add(new Bus("1500", "申沃", "沪BTK777", 50));
        busBrand.put("申沃", shenwo);

        data.put("客车", busBrand);

    }


    public static void main(String[] args) {
        System.out.println("***************欢迎来到黑马租车***************");
        Scanner sc = new Scanner(System.in);

        //业务逻辑对象
        RentServiceImp rentServiceImp = new RentServiceImp();

        while (true) {
            System.out.println("请选择你的服务: 1) 租车服务   2) 还车服务  3) 退出系统");
            String choose = sc.nextLine();
            if ("1".equals(choose)) {
                //System.out.println("租车服务");

                rentServiceImp.rentFlower();

            } else if ("2".equals(choose)) {
                rentServiceImp.returnFlower();


            } else if ("3".equals(choose)) {
                System.out.println("退出系统");
                break;
            }
        }

    }

}

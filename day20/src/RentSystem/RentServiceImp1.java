package RentSystem;

import java.util.*;

public class RentServiceImp1 implements RentService {
    @Override
    public void rentFlower() {

        Scanner sc = new Scanner(System.in);
        String category;//选择的机动车类别
        Map<String, ArrayList<Vehicle>> vehicleBrands; //品牌对应的车Map集合
        String brand;//选择的品牌
        ArrayList<Vehicle> motoVehicles;//指定品牌的所有车list集合,vehicleBrands的值集合
        Vehicle chooseVehicle; //选中的车对象

        System.out.println("请选择你要租车的类别：");

        final Map<String, Map<String, ArrayList<Vehicle>>> data = RentSystem.data;
        //data集合的机动车类别，map的key值集合
        Set<String> categories = data.keySet();

        while (true){

            System.out.println("当前有汽车类别："+categories);
            category = sc.nextLine();

            if (!data.containsKey(category)){
                //选中的品牌对应的车Map集合
                System.out.println("输入有误，重新输入！");
            }else {
                vehicleBrands = data.get(category);
                break;
            }

        }
        while (true){

            System.out.println(category + "类型汽车，有如下品牌，请选择");
            Set<String> brands = vehicleBrands.keySet();
            System.out.println(brands);
            brand = sc.nextLine();
            if (vehicleBrands.containsKey(brand)){
                motoVehicles = vehicleBrands.get(brand);
                break;
            }else {
                System.out.println("输入有误，重新输入");
            }
        }
        //选择品牌指定类型汽车
        if(motoVehicles.isEmpty()){
            System.out.println("不好意思，您来迟一步，该品牌车型已经租借完毕！");
            return;
        }
        while (true){

            System.out.println(brand + "有以下汽车：");

            //遍历选中的品牌车的集合：
            for (int i = 0; i < motoVehicles.size(); i++) {

                System.out.println("[" + i + "]  " + motoVehicles.get(i).toString());

            }

            System.out.println("请输入您需要的车型对应的序号：");

               int index = sc.nextInt();
               if (index < 0 || index >= motoVehicles.size()){
                   System.out.println("输入有误，请重新选则");
                   System.out.println();

               }
               else {
                   chooseVehicle = motoVehicles.remove(index);//错用get当remove
                   showVehicleMessage(category, chooseVehicle);
                   RentSystem.rentVehicle.add(chooseVehicle);
                   break;
               }
        }

        //确定天数，计算租金
        System.out.println("请输入要租借的天数：");
        int days = sc.nextInt();
        double money = chooseVehicle.calRent(days);
        System.out.println("尊敬的顾客，您此次租借的车是" +
                chooseVehicle.getBrand() +
                "车牌为" + chooseVehicle.getId() +
                "租借的时间为" + days + "天，租金为" + money + "元。祝用车愉快，一路平安！");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void showVehicleMessage(String category, Vehicle chooseVehicle) {

        if (category.contains("轿车")){

            CAR car = (CAR) chooseVehicle;

            System.out.println(
                    "您要租的轿车信息如下：" + car.getBrand() + " " + car.getType() +
                            ",车牌号为：" + car.getId() +
                            ",租金每天" + car.getPrice() + "元");

        }else if (category.contains("客车")){

            BUS bus = (BUS) chooseVehicle;

            System.out.println(
                    "您要租的轿车信息如下：" + bus.getBrand() + bus.getSeats() + "座" +
                            ",车牌号为：" + bus.getId() +
                            ",租金每天" + bus.getPrice() + "元");

        }

        }


    @Override
    public void returnFlower() {
        
        Scanner sc = new Scanner(System.in);
        //租到的车list集合
        ArrayList<Vehicle> returnVehicle = RentSystem.rentVehicle;
        //要返还的机动车
        Vehicle  vehicleRe = null;
        
        if (returnVehicle.isEmpty()){
            System.out.println("您当前没有租用机动车！");
            return;
        }
        System.out.println("您租用的汽车如下：");

        for (int i = 0; i < returnVehicle.size(); i++) {
            System.out.println("[" + i + "] " + returnVehicle.get(i).toString());
        }

        System.out.println("请选择您要归还的汽车：");

        String str = sc.nextLine();
        int index = Integer.parseInt(str);
        if (index < 0 || index > returnVehicle.size()) {
            System.out.println("输入错误，请重新输入");
        }
        else {

            vehicleRe = returnVehicle.get(index);

        }

        //将汽车放回到集合中
        Map<String,ArrayList<Vehicle>> brand = null;

        if (vehicleRe instanceof CAR){
            brand = RentSystem.data.get("轿车");

        }else if (vehicleRe instanceof BUS){
            brand = RentSystem.data.get("客车");
        }

//       引用数据类型的"=" 即是赋值也是给与相同的内存地址，子变父也变
        ArrayList<Vehicle> vehicles = brand.get(vehicleRe.getBrand());
        vehicles.add(vehicleRe);

        
        RentSystem.rentVehicle.remove(vehicleRe);
        System.out.println("还车结束！");

    }}


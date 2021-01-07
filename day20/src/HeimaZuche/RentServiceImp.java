package HeimaZuche;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RentServiceImp implements RentService{
    @Override
    public void rentFlower() {
        Scanner sc = new Scanner(System.in);

        String category;//选择的类别
        Map<String, ArrayList<MotoVehicle>> vehicleBrands;//品牌对应的车Map集合
        String brand;//选择的品牌
        ArrayList<MotoVehicle> motoVehicles;//指定品牌的所有车list集合
        MotoVehicle chooseVehicle;//选中的车对象


        System.out.println("请选择你要租车的类别：");
        final Map<String, Map<String, ArrayList<MotoVehicle>>> data = RentSys.data;
        Set<String> categories = data.keySet();
        //选择类别
        while (true) {
            System.out.println("当前有汽车类别：" + categories);
            category = sc.nextLine();
            if (!data.containsKey(category)) {
                System.out.println("类别输入有误，请重新输入！");
            } else {
                vehicleBrands = data.get(category);
                break;
            }
        }
        //选择品牌
        while (true) {
            System.out.println(category + "类型汽车，有如下品牌，请选择");
            Set<String> brands = vehicleBrands.keySet();
            System.out.println(brands);
            brand = sc.nextLine();
            if (vehicleBrands.containsKey(brand)) {
                motoVehicles = vehicleBrands.get(brand);
                break;
            } else {
                System.out.println("输入品牌有误，请重新选择");
            }
        }
        //选择品牌指定类型汽车
        if (motoVehicles.size() == 0) {
            System.out.println("不好意思，您来迟一步，该品牌车型已经租借完毕！");
            return;
        }

        while (true) {

            System.out.println(brand + "有以下汽车：");
            for (int i = 0; i < motoVehicles.size(); i++) {
                System.out.println("[" + i + "]  " + motoVehicles.get(i).toString());
            }

            System.out.println();
            System.out.println("请输入您需要的车型对应的序号：");
            int index = sc.nextInt();

            if (index < 0 || index >= motoVehicles.size()) {
                System.out.println("输入有误，请重新选则");
                System.out.println();
            } else {
                chooseVehicle = motoVehicles.remove(index);
                showVehicleMessage(category, chooseVehicle);
                RentSys.rentVehicles.add(chooseVehicle);

                break;
            }
        }

        //确定天数，计算租金
        System.out.println("请输入要租借的天数：");
        int days = sc.nextInt();
        float money = chooseVehicle.calRent(days);

        //打印租借信息
        System.out.println("尊敬的顾客，您此次租借的车是" +
                chooseVehicle.getBrand() +
                "车牌为" + chooseVehicle.getId() +
                "租借的时间为" + days + "天，租金为" + money + "元。祝用车愉快，一路平安！");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Override
    public void returnFlower() {
        Scanner sc = new Scanner(System.in);
        ArrayList<MotoVehicle> returnVehicles = RentSys.rentVehicles;
        MotoVehicle returnVehicle = null;

        if (returnVehicles.isEmpty()) {
            System.out.println("您当前没有租借汽车");
            System.out.println();
            return;
        }

        System.out.println("尊敬的用户你好，租借的车有如下：");
        for (int i = 0; i < returnVehicles.size(); i++) {
            System.out.println("[" + i + "] " + returnVehicles.get(i).toString());
        }

        System.out.println("请选择您要归还的汽车：");
        int index = sc.nextInt();
        if (index < 0 || index > returnVehicles.size()) {
            System.out.println("输入错误，请重新输入");
        } else {
            returnVehicle = returnVehicles.get(index);
        }

        Map<String, ArrayList<MotoVehicle>> brand = null;
        //将汽车放回到集合中
        if (returnVehicle instanceof Car) {
            brand = RentSys.data.get("轿车");
        } else if (returnVehicle instanceof Bus) {
            brand = RentSys.data.get("客车");
        }

        ArrayList<MotoVehicle> motoVehicles = brand.get(returnVehicle.getBrand());
        motoVehicles.add(returnVehicle);
        RentSys.rentVehicles.remove(returnVehicle);
        System.out.println("还车结束！");

    }

    /**
     * 显示信息
     *
     * @param category
     * @param motoVehicle
     */
    public void showVehicleMessage(String category, MotoVehicle motoVehicle) {
        if ("轿车".equals(category)) {
            Car car = (Car) motoVehicle;
            System.out.println(
                    "您要租的轿车信息如下：" + car.getBrand() + " " + car.getType() +
                            ",车牌号为：" + car.getId() +
                            ",租金每天" + car.getRent() + "元");
        } else if ("客车".equals(category)) {
            Bus bus = (Bus) motoVehicle;
            System.out.println(
                    "您要租的轿车信息如下：" + bus.getBrand() + bus.getSeats() + "座" +
                            ",车牌号为：" + bus.getId() +
                            ",租金每天" + bus.getRent() + "元");
        }

    }
}

package HeimaZuche;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArrayList<MotoVehicle> dazong = new ArrayList<>();
        dazong.add(new Car("200", "大众", "赣BTK001", "探歌280T"));
        dazong.add(new Car("300", "大众", "赣BTK002", "探歌140T"));

        ArrayList<MotoVehicle> returnVehicles = dazong;
//        returnVehicles = null;
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
    }
}

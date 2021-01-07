package ZuChe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Motovehicle> list1 = new ArrayList<>();

        Motovehicle m1 = new Car("奔驰XXX","赣BTK001",500);
        Motovehicle m2 = new Car("宝马XXX","赣BTK002",400);
        Motovehicle m3 = new Car("奥迪Xxx","赣BTK003",400);

        Motovehicle m4 = new Bus("申沃客车50座 ","沪BTK666",1500);

        Collections.addAll(list1,m1,m2,m3,m4);

//        System.out.println(list1);


        while (true) {

            System.out.println("**************欢迎来到黑马租车系统********************");
            System.out.println("请选择您的服务：1) 租车服务 2)还车服务 3)退出");
            String op = sc.nextLine();

            switch (op) {

                case "1":
                    Zuche(list1,sc);
                    break;
                case "2":
                    huanChe(list1,sc);
                    break;
                case "3":
                    System.out.println("谢谢使用！再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，重新输入！");
                    break;


            }


        }


    }

    private static void huanChe(ArrayList<Motovehicle> list, Scanner sc) {



    }

    private static void Zuche(ArrayList<Motovehicle> list, Scanner sc) {

        System.out.println("请选择你要租车的类别：[轿车,客车]");
        String str = sc.nextLine();
        System.out.println(str+"类型汽车，有如下品牌，请选择");
        if (str.equals("轿车")){
            System.out.println("[奔驰,宝马,奥迪]");
        }else {
            System.out.println("申沃客车50座");
        }

        String str1 = sc.nextLine();

        System.out.println(str1+"有如下汽车:");

        double price = 0;

        for (Motovehicle c :list) {

            if (c.getBrand().contains(str1)){

                System.out.println(c.getBrand()+",车牌号为："+c.getType()+",每天租金"+c.getPrice()+"天");

                price = c.getPrice();

                c.YouHui();
            }

        }
        System.out.println("请输入你要租借的天数");

        int num = sc.nextInt();

        double sum = 0;

        if (num>7){
            sum=num*price*0.9;
        }else if (num>30){
            sum = num*price*0.8;
        }else if (num>150){
            sum = num*price*0.5;
        }

        System.out.println("您此次租借的车是"+str1+"租借的时间为："+num+"天"+"租金为："+sum+"元");

    }
}

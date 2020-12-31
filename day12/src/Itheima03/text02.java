package Itheima03;

import java.util.ArrayList;
//1)定义水果类 Fruit,提供以下属性 String name(名称) double price(价格)
// 提供空参满参构造方法,set/get 方法
// 2)在测试类的 main 方法中完成以下操作
// 创建三个水果对象,名字价格如下:
// 苹果", 1.8, 橙子", 3.5, "西瓜", 1.5
// 获取三个水果对象的价格互相比较，算出价格最低的水果,
// 然后打印最低价格水果的全部属 性
public class text02 {
    public static void main(String[] args) {

        ArrayList<Fruit> list = new ArrayList<>();

        Fruit fruit1 = new Fruit("苹果",1.8);
        Fruit fruit2 = new Fruit("橙子",3.5);
        Fruit fruit3 = new Fruit("西瓜",1.5);

        list.add(fruit1);
        list.add(fruit2);
        list.add(fruit3);

        Fruit f = list.get(0);

        double dou = f.getPrice();


        for (int i = 0; i < list.size(); i++) {
            Fruit fruit = list.get(i);
            System.out.println(fruit.getName()+"..."+fruit.getPrice());
            if (dou>fruit.getPrice()){
                dou=fruit.getPrice();

            }

        }
        System.out.println("最便宜的水果价格："+dou);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrice()==dou){
                System.out.println(list.get(i).getName()+"是最便宜的水果价格："+list.get(i).getPrice());
            }
        }
    }
}

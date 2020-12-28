package itheima06;


import java.util.ArrayList;

//4、定义“手机类”Phone包含空参构造、满参构造和以下成员变量：
//
//名称name（String类型） 价格price（double类型） 类型type（String类型）
//
//生成以上成员变量的set/get方法
//
//定义测试类Test，完成以下要求：
//
//	①定义public static ArrayList<Phone> filter(ArrayList<Phone> list,double price) {...}方法:
//	要求：遍历list集合，将list中价格大于参数price的元素存入到另一个ArrayList 中并返回
//
// 	②在main方法内完成以下要求: a.根据以下内容创建并初始化3个Phone对象
//
//  {"小米MIX2",2999,"新机皇"}
//
// {"Iphone8", 5888,"火爆新机"}
//
//{"VIVO X9s",1998,"火爆新机"}
//
//b.创建一个ArrayList list_phone，将上面的3个Phone对象添加到list_phone中，
// 调用 filter方法传入list_phone和2000，根据返回的list集合输出所有元素信息

import java.util.ArrayList;
public class test01 {
    public static void main(String[] args) {
        ArrayList<Phone> list_phone = new ArrayList<>();

        Phone p1 = new Phone("小米MIX2",2999,"新机皇");
        Phone p2 = new Phone("Iphone8", 5888,"火爆新机");
        Phone p3 = new Phone("VIVO X9s",1998,"火爆新机");

        list_phone.add(p1);
        list_phone.add(p2);
        list_phone.add(p3);

        ArrayList<Phone> ppList =  filter(list_phone,2000);

        for (int i = 0; i < ppList.size(); i++) {

            Phone pp = ppList.get(i);

            System.out.println(pp.getName()+"\t"+pp.getPrice()+"\t"+pp.getType());

        }

    }
    public static ArrayList<Phone> filter(ArrayList<Phone> list, double price){

        for (int i = 0; i < list.size(); i++) {
            Phone p = list.get(i);
            double pri = p.getPrice();
            if (pri<price){
                list.remove(p);
                i--;
            }
        }
        ArrayList<Phone> list1 = list;
        return list1;
    }
}

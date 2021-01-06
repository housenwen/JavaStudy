package itheima03;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class test01 {
    public static void main(String[] args) {

        Map<String,Map<String,ArrayList<String>>> map = new HashMap<>();

        Map<String,ArrayList<String>> m1 = new HashMap<>();

        ArrayList<String> list = new ArrayList<>();

        Collections.addAll(list, "中原区","金水区","二七区","管城回族区");

        m1.put("郑州市",list);

        list = new ArrayList<>();

        Collections.addAll(list,"鼓楼区","龙亭区","顺河回族区","禹王台区");

        m1.put("开封市",list);

        list = new ArrayList<>();

        Collections.addAll(list,"西工区","老城区","涧西区","洛龙区");

        m1.put("洛阳市",list);

//        System.out.println(m1);

        map.put("河南省", m1);

//        System.out.println(map);

        //创建集合保存河北省的信息

        m1 = new HashMap<>();

        list= new ArrayList<>();

        Collections.addAll(list,"长安区","桥东区","桥西区","新华区；");

        m1.put("石家庄市",list);

        list = new ArrayList<>();
        Collections.addAll(list,"路北区","路南区","古冶区","开平区");
        m1.put("唐山市",list);

        list = new ArrayList<>();
        Collections.addAll(list,"海港区","山海关区","北戴河区","昌黎县");
        m1.put("秦皇岛市",list);

//        System.out.println(m1);

        map.put("河北省",m1);

        for (String sheng:map.keySet()){

            System.out.println(sheng+":");

            Map<String,ArrayList<String>> map2 = map.get(sheng);

            for (String shi:map2.keySet()){
                System.out.println("\t"+shi+":"+map2.get(shi));
            }

        }

    }
}


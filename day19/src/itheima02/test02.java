package itheima02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class test02 {
    public static void main(String[] args) {
         /*
        	有两个班的学员，分别存储在两个Map中
        */
        //第一个班：
        Map<String,String> map1 = new HashMap<>();
        map1.put("it001","迪丽热巴");
        map1.put("it002","古力娜扎");

        //第二个班：
        Map<String,String> map2 = new HashMap<>();
        map2.put("heima001","蔡徐坤");
        map2.put("heima002","李易峰");

        //将两个班的map存储到一个ArrayList中
        ArrayList<Map<String,String>> allList = new ArrayList<>();
        allList.add(map1);
        allList.add(map2);

        //遍历allList，取出每个Map
        for(Map<String,String> map : allList){
            //遍历map
            Set<String> keys = map.keySet();
            for(String key : keys){
                System.out.println(key + " - " + map.get(key));
            }
        }
    }
}


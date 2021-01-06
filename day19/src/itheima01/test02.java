package itheima01;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class test02 {
    public static void main(String[] args) {

        LinkedHashMap<String ,String > map = new LinkedHashMap<>();

        map.put("周杰伦","蔡依林");
        map.put("刘德华","柳岩");
        map.put("郭富城","小s");

        Set<Map.Entry<String,String>> set = map.entrySet();

        for (Map.Entry<String,String> entry:set){
            System.out.println(entry);
        }

        for (Map.Entry<String,String> entry:set){
            System.out.println(entry.getKey()+"...."+entry.getValue());
        }

    }
}

package itheima01;

import java.util.HashMap;

public class test {
    public static void main(String[] args) {
//        创建HashMap对象
        HashMap<String,String> map = new HashMap<>();

        map.put("周杰伦","蔡依林");
        map.put("黄晓明","Baby");
        map.put("邓超","孙俪");

        System.out.println(map);

        System.out.println(map.remove("周杰伦"));
        System.out.println(map);

        System.out.println(map.get("邓超"));
        System.out.println(map.get("黄晓明"));

        System.out.println(map.keySet());
        System.out.println(map.keySet().getClass());
    }
}

package HomeWork;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();

        map.put(1,"张三");
        map.put(2,"李四");
        map.put(1,"王五");

        System.out.println("数组的大小："+map.size());
//       键找值遍历：
        Set<Integer> set = map.keySet();

        for (Integer key:set){
            String str = map.get(key);
            System.out.println(key+"="+str);
        }
//        键值对遍历：
        Set<Map.Entry<Integer,String>> entrySet = map.entrySet();

        for (Map.Entry<Integer,String> entry:entrySet){

            System.out.println(entry.getKey()+"="+entry.getValue());

        }
//        获取键为1的值
        String value1 = map.get(1);
        System.out.println(value1);
//        获取键为10的值
        System.out.println(map.get(10));

        System.out.println(map.containsKey(10));

        Integer key = 1;

        int count = keyCount(map,key);

        System.out.println("此key出现的次数："+count);

        map.remove(1);

        System.out.println(map);


    }

    private static int keyCount(HashMap<Integer, String> map,Integer integer) {

        Set<Integer> set = map.keySet();
        int count =0;

        for (Integer key:set){

            if (key.equals(integer)){
                System.out.println("包含此key");
                count++;
            }
            else {
                System.out.println("不包含此key");
            }

        }
        return count;
    }
}

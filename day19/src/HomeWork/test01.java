package HomeWork;

import java.util.HashMap;
import java.util.Set;

public class test01 {
    public static void main(String[] args) {

        String str = "fje你kw我FDQFj你feAF他Eajf他eo2FA我FEjfew";

        HashMap<Character,Integer> map = new HashMap<>();

        Set<Character> set = map.keySet();

        for (int i = 0; i < str.length(); i++) {
                char c =str.charAt(i);

                if (map.containsKey(c)){
                    map.put(c,map.get(c)+1);
                }else {
                    map.put(c,1);
                }
            }

        for (Character c:set){

            System.out.println(c+"="+map.get(c));
        }
        System.out.println(map);

    }
}

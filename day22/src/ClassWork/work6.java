package ClassWork;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class work6 {
    public static void main(String[] args) {

        Map<Integer, AtomicInteger> map =new Hashtable<>();

        for (int i = 0; i < 10; i++) {

            map.put(i,new AtomicInteger(i));

        }

        System.out.println(map);

        Set<Map.Entry<Integer,AtomicInteger>> entry = map.entrySet();

        for (Map.Entry<Integer,AtomicInteger> integerEntry:entry){
            System.out.println(integerEntry.getValue().get());
        }


    }
}

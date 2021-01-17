package unmodifiableList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test1 {
    @Test
    public void show(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"a","b","c");
        list=Collections.unmodifiableList(list);
//        list.add("t");
//        list.set(1,"ios");
//        list.remove(1);//java.lang.UnsupportedOperationException
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}

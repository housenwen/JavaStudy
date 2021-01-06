package HomeWork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对给定的数字列表进行分组，要求返回的Map中，Key为数字，Value为该数字出现的列表。
 * 例如，输入的数字列表为：[1,2,3,3,4,2]，
 * 那么返回值应为：{1=[1],2=[2,2],3=[3,3],4=[4]}
 */
public class test06 {
    public static void main(String[] args) {
        //创建一个Map集合，用于保存需要返回的数据
        Map<Integer, List<Integer>> map = new HashMap<>();
        //创建数字集合列表：
        Collection<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1, 2, 3, 3, 4);
//        System.out.println(numbers);
        //定义一个List类型的变量
        List<Integer> list = null;

        //遍历参数集合，获取参数集合中每一个元素

        for (Integer integer : numbers) {
            //判断Map集合中是否已经包含这个元素做的键
            if (map.containsKey(integer)) {
//如果map集合中已经包含这个元素做的键，说明不是第一次出现，可以获取它的对应的值，也就是保存这个元素的List集合
                list = map.get(integer);
//                System.out.println(list);
            }
            //如果map集合中没有包含这个元素做的键，说明是第一次出现，所以需要新建一个List集合，用来保存这个元素
            else {
                list = new ArrayList<>();

            }
//            将这个元素添加到保存它的List集合中
            list.add(integer);
            System.out.println(list);
            map.put(integer, list);
        }

        System.out.println(map);

    }

}

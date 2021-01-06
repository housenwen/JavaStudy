package HomeWork;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 对给定的数字列表进行分组，要求返回的Map中，Key为数字，Value为该数字出现的列表。
 * 例如，输入的数字列表为：[1,2,3,3,4,2]，
 * 那么返回值应为：{1=[1],2=[2,2],3=[3,3],4=[4]}
 */
public class test05 {
    public static void main(String[] args) {

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list,1,2,3,3,4,2);

        Set<Integer> set = map.keySet();

        for (Integer key :set){

            for (Integer in :list){

                for (Integer i :list) {

                    if (list.get(in)==list.get(i)){

                        ArrayList<Integer> list1 = new ArrayList<>();
                        list1.add(list.get(in));
                        list1.add(list.get(in));
                        System.out.println(list1);

                    }else {

                        ArrayList<Integer> list2 = new ArrayList<>();
                        list.add(list2.get(in));
                        System.out.println(list2);
                    }

                }

            }

            }

        }

    }


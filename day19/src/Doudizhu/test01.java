package Doudizhu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 组合牌
 * 定义一个Map集合用来存储牌号  和 牌
 * 定义一个List集合用来存储牌号
 * 花色:♥-♠-♦-♣
 * 数字:2-A-K-Q-J-10-9-8-7-6-5-4-3
 * 洗牌
 * Collections.shuffle(牌号集合)
 * 发牌
 * 三个玩家三个集合
 * 发牌号
 * 排序
 * 看牌
 */
public class test01 {
    public static void main(String[] args) {

        HashMap<Integer, String> mapPooker = new HashMap<>();
        ArrayList<Integer> listPooker = new ArrayList<>();

        String[] colors = "♥-♠-♦-♣".split("-");
        String[] nums = "2-A-K-Q-J-10-9-8-7-6-5-4-3".split("-");

        int index = 2;
        for (String pookerNum : nums) {

            for (String pookerColor : colors) {

                String thisPooker = pookerColor + pookerNum;
                mapPooker.put(index, thisPooker);
                listPooker.add(index);
                index++;

            }

        }
        mapPooker.put(0, "大王");
        mapPooker.put(1, "小王");
        listPooker.add(0);
        listPooker.add(1);

        System.out.println(mapPooker);
        System.out.println(listPooker);

        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<Integer> player3 = new ArrayList<>();
        ArrayList<Integer> diPai = new ArrayList<>();

        Collections.shuffle(listPooker);

        for (int i = 0; i < listPooker.size(); i++) {
            Integer integer = listPooker.get(i);
            if (i > 50) {
                diPai.add(integer);
            } else if (i % 3 == 0) {
                player1.add(integer);
            } else if (i % 3 == 1) {
                player2.add(integer);
            } else if (i % 3 == 2) {
                player3.add(integer);
            }

        }
        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);
        Collections.sort(diPai);

        show("张三", player1, mapPooker);
        show("李四", player2, mapPooker);
        show("王五", player3, mapPooker);
        show("底牌", diPai, mapPooker);

    }

    private static void show(String name, ArrayList<Integer> player1,
                             HashMap<Integer, String> mapPooker) {

        System.out.println(name + "的牌:");
        for (Integer playKey : player1) {
            String thisPooker = mapPooker.get(playKey);
            System.out.print(thisPooker + "\t");
        }
        System.out.println();
    }
}

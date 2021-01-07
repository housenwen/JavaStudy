package itheima;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
public class test {
    public static void main(String[] args) {

        Map<Integer,String> mapPoo = new LinkedHashMap<>();

        ArrayList<Integer> list = new ArrayList<>();

        String [] colors = "♥-♠-♦-♣".split("-");
        String [] nums = "2-A-K-Q-J-10-9-8-7-6-5-4-3".split("-");

        int index = 2;

        for (String num :nums){

            for (String color:colors){

                String thisPoo = num+color;
                mapPoo.put(index,thisPoo);
                list.add(index);
                index++;

            }



        }

        mapPoo.put(0,"大王");
        mapPoo.put(1,"小王");
        list.add(0);
        list.add(1);

        Collections.shuffle(list);

        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();
        ArrayList<Integer> player3 = new ArrayList<>();
        ArrayList<Integer> diPai = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            if (i>50){
                diPai.add(list.get(i));
            }
            else if (i%3==0){
                player1.add(list.get(i));
            }else if (i%3==1){
                player2.add(list.get(i));
            }else if (i%3==2){
                player3.add(list.get(i));
            }

        }

        Collections.sort(player1);
        Collections.sort(player2);
        Collections.sort(player3);
        Collections.sort(diPai);


        show("周杰伦",mapPoo,player1);
        show("张学友",mapPoo,player2);
        show("刘德华",mapPoo,player3);
        show("底牌",mapPoo,diPai);


    }

    private static void show(String name, Map<Integer, String> mapPoo,
                             ArrayList<Integer> player) {
        System.out.println(name+"的手牌：");
        for (Integer key:player){

            String thisPoo = mapPoo.get(key);

            System.out.print(thisPoo+"\t");

        }

        System.out.println();
    }

}

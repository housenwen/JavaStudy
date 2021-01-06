package Doudizhu;


import java.util.*;

public class DouDiZhu {
    public static void main(String[] args) {
        double start =  System.currentTimeMillis();
        // 定义一个Map集合用来存储牌号  和 牌
        Map<Integer,String> pookerMap = new HashMap<>();
       //定义一个List集合用来存储牌号
        ArrayList<Integer> pookerList = new ArrayList<>();

        String[] colors = "♥-♠-♦-♣".split("-");
//        System.out.println(Arrays.toString(colors));

        String [] nums = "2-A-K-Q-J-10-9-8-7-6-5-4-3".split("-");
//        System.out.println(Arrays.toString(nums));

        int index = 2;

        for (String num:nums){
        for (String color:colors){
            String thisPooker = color+num;
//            System.out.println(thisPooker);
            //将扑克牌放入Map集合
            pookerMap.put(index,thisPooker);
            //将牌号放入到pookerList集合中
            pookerList.add(index);
            index++;

        }}
        //将大王小王添加到集合
        pookerMap.put(0,"大王");
        pookerMap.put(1,"小王");
        pookerList.add(0);
        pookerList.add(1);

        System.out.println(pookerMap);
		System.out.println(pookerList);

        //洗牌
        Collections.shuffle(pookerList);

        ArrayList<Integer> palyer1 = new ArrayList<>();
        ArrayList<Integer> palyer2 = new ArrayList<>();
        ArrayList<Integer> palyer3 = new ArrayList<>();
        ArrayList<Integer> Dipai = new ArrayList<>();

        //遍历牌号的集合 判断索引发牌号

        for (int i = 0; i < pookerList.size(); i++) {

            Integer pookerNum = pookerList.get(i);

            if (i>50){
                Dipai.add(pookerNum);
            }else if (i%3==0){
                palyer1.add(pookerNum);
            }else if (i%3==1){
                palyer2.add(pookerNum);
            }else if (i%3==2){
                palyer3.add(pookerNum);
            }

        }
        Collections.sort(palyer1);
        Collections.sort(palyer2);
        Collections.sort(palyer3);
        Collections.sort(Dipai);
        System.out.println(palyer1);
        System.out.println(palyer2);
        System.out.println(palyer3);
        System.out.println(Dipai);

        show("张三",palyer1,pookerMap);
        show("李四",palyer2,pookerMap);
        show("王五",palyer3,pookerMap);
        show("底牌",Dipai,pookerMap);

        double end = System.currentTimeMillis();
        System.out.println(end-start+"毫秒");

    }

    private static void show(String name, ArrayList<Integer> palyer, Map<Integer, String> pookerMap) {

        System.out.println(name+":");

        for (Integer pookernum:palyer){

            String thisPooker = pookerMap.get(pookernum);
            System.out.print(thisPooker+"\t");
        }
        System.out.println();
    }
}

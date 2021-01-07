package itheima;

/**
 *
 *
 * 第二种双色球开奖计算方式
 * 不使用随机数类： Random
 * 使用Collections.shuffle打乱list集合
 *
 */
import java.util.ArrayList;
import java.util.Collections;


public class test02 {
    public static void main(String[] args) {

        ArrayList<Integer> listRed = new ArrayList<>();

        ArrayList<Integer> totalList = new ArrayList<>();

        ArrayList<Integer> blueList = new ArrayList<>();



        for (int i = 1; i <= 36; i++) {

            totalList.add(i);

        }

        System.out.println(totalList);

        Collections.shuffle(totalList);

        for (int i = 0; i < 6; i++) {

           listRed.add(totalList.get(i));

        }

        Collections.sort(listRed);
        System.out.println(listRed);

        for (int i = 1; i <= 16; i++) {

            blueList.add(i);

        }
        Collections.shuffle(blueList);

        for (Integer integer :blueList){

            Integer blueBall = integer;

            System.out.println(blueBall);

            listRed.add(blueBall);

            break;

        }

        System.out.println("本期中奖号码："+listRed);

    }
}

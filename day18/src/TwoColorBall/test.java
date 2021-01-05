package TwoColorBall;
/*
 * 双色球规则:双色球每注投注号码由6个红色球号码和1个蓝色球号码组成。
 * 红色球号码从1—33中选择；蓝色球号码从1—16中选择.请随机生成一注双色球号码
 * */

import java.util.*;

public class test {
    public static void main(String[] args) {

        Random random = new Random();

        LinkedHashSet<Integer> setRed = new LinkedHashSet<>();

        while (setRed.size()<6){
            int num = random.nextInt(33)+1;
            setRed.add(num);
        }
//        Arrays.sort(new LinkedHashSet[]{setRed});
        System.out.println("红色球"+setRed);

        HashSet<Integer> setBlue = new HashSet<>();
        while (setBlue.size()<1){
            int num = random.nextInt(16)+1;
            setBlue.add(num);
        }
        System.out.println("蓝色球"+setBlue);

        TreeSet<Integer> list  = new TreeSet<>();
        list.addAll(setRed);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.addAll(list);
        list1.addAll(setBlue);
        System.out.println("本期中奖号码是："+list1);
    }
}

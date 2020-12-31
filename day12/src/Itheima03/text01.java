package Itheima03;

import java.util.Arrays;
import java.util.Random;

//①、已知某比赛的规则为 5 个裁判员打分，去掉一个最高分、一个最低分，剩余三个分数的 平均值就是该选手的最终得分。
//②、现根据该规则模拟打分，已知每个裁判打分都是从{10,20,30,40,50,60,70,80,90,100}
// 数组中随机抽取数据给选手打分，在控制台打印出 5 个裁判给该选手的打分情况和选手的最 终得分。
// PS:涉及到存储数据的容器都要求使用数组
public class text01 {
    public static void main(String[] args) {

        int[] ints = {10,20,30,40,50,60,70,80,90,100};

        int length =  ints.length;

        Random random = new Random();

        int[] nums = new int[5];


        for (int i = 0; i < nums.length; i++) {

            nums[i]= ints[random.nextInt(length)];

        }

        System.out.println(Arrays.toString(nums));

        getPing(nums);

    }

    private static void getPing(int[] nums) {
        int min =nums[0];
        int max =nums[0];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            if (min>nums[i]){
                min=nums[i];
            }
            if (max<nums[i]){
                max = nums[i];
            }
            sum+=nums[i];


        }
        System.out.println("最小值："+min);
        System.out.println("最大值："+max);
        System.out.println("总和为："+sum);
        int ping = (sum-min-max)/3;
        System.out.println("按规则的平均值："+ping);

    }

}

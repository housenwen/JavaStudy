package TwoColorBall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class test01 {
    public static void main(String[] args) {

            //实现的方式有很多，此处只展示一种
            //生成存储1-33的集合,1-16的集合
            ArrayList<Integer> list33 = new ArrayList<>();
            for(int i=1;i<33;i++) {
                list33.add(i);
            }

            //用于存储6位双色球号码,此处用ArrayList是为了排序
            ArrayList<Integer> list6 = new ArrayList<>();

            //生成6个1-33的数字而且要求不重复
            Random rd = new Random();
            for(int i=0;i<6;i++) {
                //通过随机数产生下标，范围在[0,33)，再通过下标获取
                int index = rd.nextInt(list33.size());
                //通过下标获取1-33其中一个数字,获取完毕之后从1-33集合中移除
                int num = list33.get(index);
                list6.add(num);
                list33.remove(index);
            }
            //生成1个 1-16的特别号码
            int specialNum = rd.nextInt(16)+1;
            //排序后打印
            Collections.sort(list6);
            System.out.println("6位不重复号码："+list6);
            System.out.println("特别号码："+specialNum);

        }
    }


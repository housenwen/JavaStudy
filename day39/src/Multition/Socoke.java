package Multition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//todo 多例模式
public class Socoke {

    private static final int maxCount = 3;

    private static List instanceList = new ArrayList();

    public Socoke() {
    }
    static {
        // 创建本类的多个实例,并存放到list集合中
        for (int i = 0; i < maxCount; i++) {
           Socoke socoke = new Socoke();
            instanceList.add(socoke);
        }
    }

    // 给外界提供一个获取类对象的方法
    public static Socoke getMultition(){
        Random random = new Random();
        // 生成一个随机数
        int i = random.nextInt(maxCount);
        // 从list集合中随机取出一个进行使用
        return (Socoke) instanceList.get(i);
    }


}

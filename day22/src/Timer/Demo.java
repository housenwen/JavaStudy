package Timer;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
//等待唤醒案例
        List<String> list = new ArrayList<>();
        // 创建线程对象
        Baozipu baozipu = new Baozipu("包子铺",list);
        ChiHuo chiHuo = new ChiHuo("吃货",list);

        baozipu.start();
        chiHuo.start();

    }
}

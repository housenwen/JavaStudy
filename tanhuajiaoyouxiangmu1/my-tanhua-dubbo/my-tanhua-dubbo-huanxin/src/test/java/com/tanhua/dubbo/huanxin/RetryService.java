package com.tanhua.dubbo.huanxin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    //停顿时间：2的n次方
    @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public int execute(int max) {
        int data = RandomUtil.randomInt(1, 99);
        System.out.println("生成：" + data +" " + DateUtil.now());
        if (data < max) {
            throw new RuntimeException();
        }
        return data;
    }

    @Recover //全部重试失败后执行
    public int recover(Exception e) {
        System.out.println("全部重试完成。。。。。");
        return 88; //返回默认
    }

}
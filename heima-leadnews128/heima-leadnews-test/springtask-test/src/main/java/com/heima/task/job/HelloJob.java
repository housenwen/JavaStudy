package com.heima.task.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/7/12 11:19
 **/
@Component
@Slf4j
public class HelloJob {
    @Value("${xxl.job.executor.port}")
    int port; // 执行器端口
    /**
     * @param params 调用中心传入的参数
     * @return ReturnT 任务的结果 返回值
     */
    @XxlJob("helloJob")
    public ReturnT helloJobHandler(String params){
        // 分片的信息
        ShardingUtil.ShardingVO shardingVo = ShardingUtil.getShardingVo();
        log.info("执行器实例数量: {} , 当前执行器下标:{} ",shardingVo.getTotal(),shardingVo.getIndex());
//        ReturnT.FAIL 处理失败
        List<Integer> list = getList();
        for (Integer integer : list) {
            //                    2            ==  0   1
            if(integer % shardingVo.getTotal() == shardingVo.getIndex()){
                System.out.println("第"+shardingVo.getIndex()+"分片执行，执行数据为："+integer);
            }
        }
//        log.info("xxljob定时触发任务: helloJob  执行器==> {} 当前时间==> {}",port, LocalDateTime.now().toString());
        return ReturnT.SUCCESS; // 任务成功
    }

    public static List<Integer> getList(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000 ; i++) {
            list.add(i);
        }
        return list;
    }
}

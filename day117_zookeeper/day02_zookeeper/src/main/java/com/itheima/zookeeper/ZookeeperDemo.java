package com.itheima.zookeeper;

import org.apache.zookeeper.*;

import java.util.Scanner;

public class ZookeeperDemo {
    // Zookeeper创建连接的方式是异步的.
    // 需要让main方法等待,我们使用了等待键盘录入
    public static void main(String[] args) throws Exception{
        //1.创建zookeeper对象,用于连接Zookeeper服务器
        // 参数1: 服务器地址:端口
        // 参数2: 会话连接超时时间
        // 参数3: 监控zookeeper对象的连接状态
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000,
                new Watcher() {
                    public void process(WatchedEvent event) {
                        if(event.getState()==Event.KeeperState.SyncConnected){
                            //   链接成功    线程放行
                            System.out.println("=======创建链接完毕======"+event.getState());
                        }
                    }
                }
        );
        //new Scanner(System.in);
        System.in.read();//  主线程阻塞 ：等待输入(目的是为了等待Zookeeper对象创建连接成功)
        //2.判断/tps节点是否存在,不存在则创建
        // 如果节点不存在 返回null
        if(zooKeeper.exists("/tps",false) == null){
            // 当 /tps 节点不存在是则创建节点,并设置值
            // 参数1: 节点路径
            // 参数2: 节点数据
            // 参数3: Access Control: 访问节点的权限控制
            //  OPEN_ACL_UNSAFE ：完全开放
            //  CREATOR_ALL_ACL ：给创建该znode连接所有权限
            //  READ_ACL_UNSAFE ：所有的客户端都可读
            // 参数4: 节点类型
            zooKeeper.create("/tps",
                    "hello zookeeper".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            System.out.println("/tps 节点创建完成-- ");
        }
        zooKeeper.close();//  关闭链接
    }
}

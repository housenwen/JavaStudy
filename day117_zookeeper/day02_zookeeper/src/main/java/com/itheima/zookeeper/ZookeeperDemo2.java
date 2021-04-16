package com.itheima.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZookeeperDemo2 {

    private ZooKeeper zooKeeper = null ;

    @Before
    public void init() throws Exception{
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000,
                new Watcher() {
                    public void process(WatchedEvent event) {
                        if(event.getState()==Event.KeeperState.SyncConnected){
                            //   链接成功    线程放行
                            System.out.println("=======创建链接完毕======"+event.getState());
                        }
                    }
                }
        );
        // 模拟阻塞,保证Zookeeper对象创建成功
        //System.in.read();
    }

    /**
     * 更新节点数据
     */
    @Test
    public void test5(){
        try {
            // 参数1: 节点路径
            // 参数2: 节点数据
            // 参数3: 节点版本
            //      当前版本号 或 -1
            Stat stat = zooKeeper.setData("/tps", "哈哈哈".getBytes(), -1);
            System.out.println(stat.getVersion());
            System.out.println("更新完成...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除节点
     */
    @Test
    public void test6(){
        try {
            zooKeeper.delete("/tps-p20000000017",-1);
            System.out.println("删除完成...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取节点中的数据
     */
    @Test
    public void test7(){
        try {
            // 创建一个存放节点状态的对象
            Stat stat = new Stat();
            // 获取的节点内容
            byte[] data = zooKeeper.getData("/tps", null, stat);
            System.out.println("获取到的节点数据为: "+new String(data));
            System.out.println("当前节点版本号: "+stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取子节点内容
     */
    @Test
    public void test8(){
        try {
            List<String> list = zooKeeper.getChildren("/tps", null);
            for (String sonPath : list) {
                // 创建一个存放状态的对象
                Stat stat = new Stat();
                byte[] data = zooKeeper.getData("/tps/" + sonPath, null, stat);
                System.out.println("/tps/"+sonPath+" : "+new String(data));
                System.out.println("版本号为: "+stat.getVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test  //  监听节点数据变化
    public  void  test9(){
        try {
            Stat state = new Stat();
            // 参数1: 节点路径/名称
            // 参数2: 编写监听对象 对节点数据进行监听
            zooKeeper.getData("/tps", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event);
                    //  当节点数据变化  该方法会自动执行
                    System.out.println("节点数据变化一次"+event.getState());
                    try {
                        String path = event.getPath();
                        byte[] data = zooKeeper.getData(path, null, null);
                        System.out.println("改变后的数据为: "+new String(data));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },state);
            System.in.read(); //  当前线程阻塞 一直监听
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test  //  监听节点数据变化
    public  void  test10(){
        try {
            List<String> children = zooKeeper.getChildren("/tps", null, null);
            for (String childPath : children) {
                zooKeeper.getData("/tps/" + childPath, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        System.out.println("监控到子节点发生改变了...");
                        try {
                            String path = event.getPath();
                            byte[] data = zooKeeper.getData(path, null, null);
                            System.out.println("改变后的数据为: "+new String(data));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },null);
            }
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() throws Exception{
        if(zooKeeper!=null){
            zooKeeper.close();
        }
    }
}

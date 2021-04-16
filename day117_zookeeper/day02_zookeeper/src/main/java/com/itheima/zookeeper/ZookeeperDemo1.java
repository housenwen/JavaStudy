package com.itheima.zookeeper;

import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZookeeperDemo1 {

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
     * 创建持久节点
     */
    @Test
    public  void  test1(){
        try {
            if(zooKeeper.exists("/tps-p1",false) == null){
                //  返回值 就是节点的路径
                String path = zooKeeper.create(
                        "/tps-p1",
                        "hello pesistent!!".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT
                );
                System.out.println("/tps-p1 持久节点创建完成-- "+path);
                byte[] data = zooKeeper.getData(path,null, null);
                System.out.println("/tps-e 持久节点数据 -- "+new String(data));
            }else {
                System.out.println("当前节点已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建持久有序节点
     */
    @Test
    public  void  test2(){
        try {
            if(zooKeeper.exists("/tps-p2",false) == null){
                //  返回值 就是节点的路径
                String path = zooKeeper.create(
                        "/tps-p2",
                        "hello 持久有序节点".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT_SEQUENTIAL
                );
                System.out.println("/持久节点创建完成-- "+path);
                byte[] data = zooKeeper.getData(path,null, null);
                System.out.println("持久节点数据 -- "+new String(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建临时节点
     */
    @Test
    public  void  test3(){
        try {
            if(zooKeeper.exists("/tps-p3",false) == null){
                //  返回值 就是节点的路径
                String path = zooKeeper.create(
                        "/tps-p3",
                        "hello 临时节点".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL
                );
                System.out.println("/临时节点创建完成-- "+path);
                byte[] data = zooKeeper.getData(path,null, null);
                System.out.println("临时节点数据 -- "+new String(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建临时有序节点
     */
    @Test
    public  void  test4(){
        try {
            if(zooKeeper.exists("/tps-p4",false) == null){
                //  返回值 就是节点的路径
                String path = zooKeeper.create(
                        "/tps-p4",
                        "hello 临时有序节点".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL
                );
                System.out.println("/临时有序节点创建完成-- "+path);
                byte[] data = zooKeeper.getData(path,null, null);
                System.out.println("临时有序节点数据 -- "+new String(data));
            }
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

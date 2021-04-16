package com.itheima.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class ZkClient1 {
    public static void main(String[] args) {
        //1.创建ZkClient对象
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        //2.创建节点
        if(zkClient.exists("/tps2")==false){
            User user = new User();
            user.setId(1L);
            user.setName("张三");
            // 创建节点
            zkClient.createPersistent("/tps2",user);
        }
        User user = zkClient.readData("/tps2");
        System.out.println(user);
        // 关闭
        zkClient.close();
    }

    public static void main1(String[] args) {
        //1.创建ZkClient对象
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        //2.创建节点
        if(zkClient.exists("/tps1")==false){
            // 创建节点
            zkClient.createPersistent("/tps1","6666");
        }
        String str = zkClient.readData("/tps1");
        System.out.println(str);
        // 关闭
        zkClient.close();
    }
}

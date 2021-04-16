package com.itheima.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class ZkClient4 {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        String path = "/http%3A%2F%2F192.168.190.13%3A8080%2FfindAll";
        if(zkClient.exists(path)==false){
            zkClient.createPersistent(path,"hello");
        }

        zkClient.close();
    }
}

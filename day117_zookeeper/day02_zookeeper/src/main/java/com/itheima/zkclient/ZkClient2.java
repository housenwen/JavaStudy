package com.itheima.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;

public class ZkClient2 {

    public static void main(String[] args) throws IOException {
        //1.连接服务器
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        // TODO:设置序列化工具类,用于将存放和取出的数据进行序列化操作
        zkClient.setZkSerializer(new StringValueSerializer());

        //监听数据变化
        zkClient.subscribeDataChanges("/zk", new IZkDataListener() {
            // 处理数据的改变
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("被监听的节点路径: " + dataPath);
                System.out.println("被监听的节点数据: " + data);
            }
            //  监听节点是否被删除
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("监听到节点删除了....");
                System.out.println("dataPath: " + dataPath);
            }
        });
        System.out.println("我正在监听--------------");
        System.in.read();
        zkClient.close();
    }
}

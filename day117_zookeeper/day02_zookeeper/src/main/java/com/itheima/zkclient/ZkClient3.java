package com.itheima.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;

public class ZkClient3{
    public static void main(String[] args) throws IOException {
        //1.连接服务器
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        zkClient.setZkSerializer(new StringValueSerializer());
        // 注意: 需要事先在父节点下创建子节点,必须对已存在的子节点进行监听
        //监听子节点变化
        List<String> children = zkClient.subscribeChildChanges("/zk", (parentPath, currentChilds) ->{
            System.out.println("父节点路径 : " + parentPath);
            System.out.println("当前子节点 : " + currentChilds);
        });

        //监听子节点数据变化
        for (String child : children) {
            System.out.println("-------");
            zkClient.subscribeDataChanges("/zk/"+child, new IZkDataListener() {
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    System.out.println("目标子节点路径：:" + dataPath);
                    System.out.println("目标子节点更新数据 :" + data);
                }
                //  处理子节点数据监听
                public void handleDataDeleted(String dataPath) throws Exception {
                    System.out.println("handleDataDeleted-----");
                    System.out.println("dataPath:" + dataPath);
                }
            });
        }
        System.out.println("我正在监听--------------");
        System.in.read();//  线程阻塞
        zkClient.close();
    }
}
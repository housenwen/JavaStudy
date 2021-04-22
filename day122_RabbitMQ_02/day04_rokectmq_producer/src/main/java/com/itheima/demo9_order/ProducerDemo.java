package com.itheima.demo9_order;

import com.itheima.demo9_order.pojo.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:顺序消费
 * 生产者代码实现
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group9");
        producer.setNamesrvAddr("192.168.190.148:9876");
        producer.start();

        //创建要执行的业务队列
        List<Order> orderList = new ArrayList<Order>();
        //------------------第一个订单-----------
        Order order11 = new Order();
        order11.setId(1);
        order11.setMsg("第一个订单-主单-1");
        orderList.add(order11);

        Order order12 = new Order();
        order12.setId(1);
        order12.setMsg("第一个订单-子单-2");
        orderList.add(order12);

        Order order13 = new Order();
        order13.setId(1);
        order13.setMsg("第一个订单-支付-3");
        orderList.add(order13);

        Order order14 = new Order();
        order14.setId(1);
        order14.setMsg("第一个订单-推送-4");
        orderList.add(order14);
        //--------------------第二个订单--------------
        Order order21 = new Order();
        order21.setId(2);
        order21.setMsg("第二个订单-主单-1");
        orderList.add(order21);

        Order order22 = new Order();
        order22.setId(2);
        order22.setMsg("第二个订单-子单-2");
        orderList.add(order22);
        //-------------------第三个订单----------
        Order order31 = new Order();
        order31.setId(3);
        order31.setMsg("第三个订单-主单-1");
        orderList.add(order31);

        Order order32 = new Order();
        order32.setId(3);
        order32.setMsg("第三个订单-子单-2");
        orderList.add(order32);

        Order order33 = new Order();
        order33.setId(3);
        order33.setMsg("第三个订单-支付-3");
        orderList.add(order33);

        //设置消息进入到指定的消息队列中
        for(final Order order : orderList){
            // 将Order封装到消息中
            Message msg = new Message("orderTopic",order.toString().getBytes());
            //TODO:发送时要指定对应的消息队列选择器
            SendResult result = producer.send(msg,new MessageQueueSelector() {
                //设置当前消息发送时使用哪一个消息队列
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // list: 存放一个group下所有的队列对象 (默认情况下:一个group中有4个队列)
                    // 数量只能通过修改 mq 的配置来改变(阿里开发团队认为,这个是敏感资源需要服务器管理员控制,而不是编码人员控制)
                    //根据发送的信息不同，选择不同的消息队列
                    //根据id来选择一个消息队列的对象，并返回->id得到int值
                    int mqIndex = order.getId().hashCode() % list.size();
                    return list.get(mqIndex);
                }
            }, null);
            System.out.println(result.getMessageQueue().getQueueId()+" : "+result);
        }
        //5.关闭连接
        producer.shutdown();
    }
}

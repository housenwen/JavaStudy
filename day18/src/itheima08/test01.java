package itheima08;
//需求：使用ArrayList集合，模拟实现一个栈；要求：
//
//1. 提供入队、出队和获取队列长度的功能；

public class test01 {
    public static void main(String[] args) {

        MyStack<String> mq = new MyStack<>();

        mq.add("111");
        mq.add("222");
        mq.add("333");

        System.out.println(mq.remove());

        while (mq.size()>1){
            System.out.println(mq.remove());
        }

    }
}

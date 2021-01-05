package itheima08;

import java.util.ArrayList;

class MyStack<T>{
    ArrayList<T> list = new ArrayList<>();
//入列
    public void add(T t){
        list.add(t);
    }
//    出列
    public T remove(){
        //因为队列具有先进后出的特点，而向ArrayList集合中添加数据是有序的，
        //所以移除数据时需要移除最后一个下标上的数据
        return list.remove(list.size()-1);
    }
//    获取队列长度
    public int size(){
        return list.size();
    }
}

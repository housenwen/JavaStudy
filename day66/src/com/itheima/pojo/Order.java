package com.itheima.pojo;


import java.util.List;

/**
 * 订单表
 * 
 */
public class Order {

    private Integer id;

    private String orderNumber;

    private List<Orderdetail> orderdetailList;


    //如果一个类中包含一个属性的pojo类型，就是assocition标签，如果是一个集合List<User>就Collection
    private User tbUser;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderdetailList=" + orderdetailList +
                /*", tbUser=" + tbUser +*/
                '}';
    }

    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public User getTbUser() {
        return tbUser;
    }

    public void setTbUser(User tbUser) {
        this.tbUser = tbUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}

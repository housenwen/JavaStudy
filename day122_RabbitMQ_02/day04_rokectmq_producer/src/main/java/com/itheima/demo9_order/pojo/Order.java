package com.itheima.demo9_order.pojo;

public class Order {
    private Integer id;
    private String msg;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

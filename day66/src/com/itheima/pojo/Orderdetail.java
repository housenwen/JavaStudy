package com.itheima.pojo;

public class Orderdetail {
    
    private Integer id;
    
    private Double totalPrice;
    
    private Integer status;

    private Item tbItem;

    @Override
    public String toString() {
        return "Orderdetail{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", tbItem=" + tbItem +
                '}';
    }

    public Item getTbItem() {
        return tbItem;
    }

    public void setTbItem(Item tbItem) {
        this.tbItem = tbItem;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

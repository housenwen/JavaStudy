package com.pojo;

public class Goods {
    private String type;
    private int count;
    private int total;

    @Override
    public String toString() {
        return "Goods{" +
                "type='" + type + '\'' +
                ", count=" + count +
                ", total=" + total +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

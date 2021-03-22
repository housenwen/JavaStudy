package com.itheima.case2.pojo;

import java.util.List;

public class PageBean {

    private List<User> userList;

    private int total;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

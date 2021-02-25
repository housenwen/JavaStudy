package com.pojo;

import java.util.List;

public class User {
    private int userId;
    private String userName;
    private List<Role> roles;
    private List<Access> accesses;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<Access> accesses) {
        this.accesses = accesses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                ", accesses=" + accesses +
                '}';
    }
}

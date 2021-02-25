package com.pojo;

public class Access {
    private int accessId;
    private String accessName;

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public String toString() {
        return "Access{" +
                "accessId=" + accessId +
                ", accessName='" + accessName + '\'' +
                '}';
    }
}

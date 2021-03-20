package com.itheima.bean;

import java.io.Serializable;
import java.util.List;
/*
*   PageBean 封装
* */
public class PageBean implements Serializable {
        // 1. 前端请求参数
    private Integer page; // 当前第几页
    private Integer count;// 每页显示的最大数量
        // 2. 数据库查询得到
    private List<Student> list; //每页查询的联系人数据
    private Integer sum; // 总记录数
        // 3. 计算得到
    private Integer first; // 首页
    private Integer pre; // 上一页
    private Integer next; // 下一页
    private Integer last; // 末页, 也是总页数

    /**
     * 方法: 获取一个PageBean
     *      // 1. 先获取前端请求参数
     *      // 2. 数据库查询结束
     *      才能调用此方法,创建一个PageBean
     */
    public static PageBean getBean(Integer page,Integer count,List<Student> list, Integer sum){
        int first = 1;
        int pre = page - 1;
        int next = page + 1;
            // 总页数
        int last = sum%count==0?sum/count : sum/count + 1;

        PageBean pageBean = new PageBean();
        pageBean.setPage(page);
        pageBean.setCount(count);
        pageBean.setList(list);
        pageBean.setSum(sum);
        pageBean.setFirst(first);
        pageBean.setPre(pre);
        pageBean.setNext(next);
        pageBean.setLast(last);
        return pageBean;
    }
    @Override
    public String toString() {
        return "PageBean{" +
                "page=" + page +
                ", count=" + count +
                ", list=" + list +
                ", sum=" + sum +
                ", first=" + first +
                ", pre=" + pre +
                ", next=" + next +
                ", last=" + last +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getPre() {
        return pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
}

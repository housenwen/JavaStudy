package com.itheima.dao;

import com.itheima.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
    学生持久层接口
 */
public interface StudentMapper {
    /*
        查询全部方法
     */
    @Select("SELECT * FROM student")
    public abstract List<Student> selectAll();

    /*
        添加数据方法
     */
    @Insert("INSERT INTO student VALUES (#{number},#{name},#{birthday},#{address})")
    public abstract void addStu(Student stu);

    /*
        修改数据方法
     */
    @Update("UPDATE student SET name=#{name},birthday=#{birthday},address=#{address} WHERE number=#{number}")
    public abstract void updateStu(Student stu);

    /*
        删除数据方法
     */
    @Delete("DELETE FROM student WHERE number=#{number}")
    public abstract void deleteStu(String number);

    /*
        分页查询
    * */
    @Select("SELECT * FROM student LIMIT #{index},#{count}")
    List<Student> findStudentByPage(@Param("index") int index, @Param("count")int count);
    /*
    *   查询总数
    * */
    @Select("SELECT count(*) FROM student")
    int findStudentSum();
}

package itheima07;

import java.util.ArrayList;
import java.util.Collection;

public class IDataOperationImpl implements IDataOperation<Student>{
    //因为要使用集合保存数据，所以先要创建一个集合容器
    private Collection<Student> c = new ArrayList<>();

    public IDataOperationImpl(Collection<Student> c) {
        this.c = c;
    }

    // 添加数据
    @Override
    public boolean add(Student t) {
        //因为不能出现两个一样学号的学生，所以再保存之前先根据学号查找学生信息。
        //如果找到了，就不保存，同时返回false
        Student stu = get(t.getId());
        if (stu!=null){
            System.out.println("该学员已存在，无法重复添加！");
        return false;
        }else {
            //不存在该学号的学生信息，就添加学生，同时返回true
            c.add(t);
            System.out.println("添加成功！！！");
            return true;
        }
    }
    // 根据唯一标识删除一个数据
    @Override
    public <T> boolean delete(T t) {
        Student stu = get(t);
        if (stu==null){
            System.out.println("没有这个学员信息！");
            return false;
        }else {
            System.out.println("删除成功");
            return c.remove(stu);
        }
    }
    // 修改一个数据
    @Override
    public <T> boolean update(Student student) {
        //要修改数据，需要先查找数据。因为学生的唯一标识是学号，所以先根据学号找到学生信息；
        Student stu = get(student.getId());
        if (stu==null){
            System.out.println("没有这个学生信息！");
        return false;}else {
            System.out.println("修改成功！！");
            stu.setAddress(student.getAddress());
            stu.setAge(student.getAge());
            stu.setName(student.getName());
            return true;
        }
    }
    // 根据唯一标识查找一个数据
    @Override
    public <T> Student get(T t) {
        if (c.isEmpty()){

            System.out.println("还没有学生信息！");
        }else {
            for (Student stu:c){
                if (t.equals(stu.getId())){
                    return stu;
                }
            }
        }
        return null;
    }

    @Override
    public Collection<Student> getAll() {
        return c;
    }

}

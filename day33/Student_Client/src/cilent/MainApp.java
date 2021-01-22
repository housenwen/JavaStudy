package cilent;

import pojo.Student;
import sun.reflect.generics.scope.Scope;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 本系统采用了我们学过的以下几个核心知识点：
 * 1). IO流技术
 * 2). 网络编程技术
 * 3). 序列化
 * 4). 多线程
 * <p>
 * 【说明】
 * 1).客户端和服务器端采用TCP连接；
 * 2).数据保存在服务器端；
 * 3). 客户端增删改查发送数据格式说明：
 * a). 添加："[1]数据"，例如："[1]张三,男,22"，意思：没有id字段，由服务器端在写入数据前自动添加。
 * b).根据id查询一条数据："[2]id"，例如："[2]1"，意思：查询id为1的学员信息
 * c). 修改一条数据："[3]新数据"。例如："[3]1,张三2,女,19"，意思：将id=1的学员改为后面的新数据。
 * d). 查询所有数据："[4]"。例如："[4]"，意思：后面不用带任何数据。
 * e). 删除一条数据："[5]id"。例如："[5]1"，意思：删除id为1的记录。
 */

/**
 * 学员管理系统(C/S版)——客户端
 * 一. 业务功能： 1.添加学员 2.修改学员 3.删除学员 4.查询学员
 * 二. 代码流程说明： 1.本客户端与服务器端实现"短连接"——
 * 每个功能当需要与服务器连接时，才建立连接，功能完毕，连接立即 断开；
 */
public class MainApp {
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("1.添加学员" + "\t" + "2.修改学员" + "\t" + "3.删除学员" + "\t" + "4.查询学员" + "\t" + "5.退出");
            System.out.println("-----------------------------------");

            int op = sc.nextInt();
            switch (op) {
                case 1://添加
                    addStudent(sc);
                    break;
                case 2:
                    // 修改
                    updateStudent(sc);
                    break;
                case 3:
                    //删除
                    deleteStudent(sc);
                    break;
                case 4://查询
                    findStudent(sc);
                    break;
                case 5://退出
                    System.out.println("谢谢使用，再见！");
                    System.exit(0);
            }
        }
    }

    //todo 1.添加学员
    public static void addStudent(Scanner sc) throws IOException{
        //1.接收用户数据
        System.out.println("请输入学员信息：");
        System.out.println("姓名：");
        String name = sc.next();
        System.out.println("性別：");
        String sex = sc.next();
        System.out.println("年龄：");
        int age = sc.nextInt();

        //2.获取连接后的输出流
        Socket socket = getSocket();
        if (socket==null){
            System.out.println("错误！无法链接到服务器");
            return;
        }
        //3.创建输出流
        OutputStream netOut = socket.getOutputStream();
        InputStream netIn = socket.getInputStream();
        //发送数据
        netOut.write(("[1]"+name+","+sex+","+age).getBytes());
        //接收反馈
        int b = netIn.read();
        //关闭链接
        socket.close();
        //判断反馈
        if(b==0){
            System.out.println("数据保存成功");
        }else {
            System.out.println("数据保存失败，请重试");
        }
        return;
    }
    //todo 2.修改学员
    public static void updateStudent(Scanner sc) throws IOException, ClassNotFoundException {
        //1.接受id
        System.out.println("请输入要修改的学员ID：");
        int id = sc.nextInt();

        //2.获取连接
        Socket socket = getSocket();
        //3.发送查询数据请求：
        OutputStream netOut = socket.getOutputStream();
        InputStream netIn = socket.getInputStream();
        //标记："2"根据ID查询一条记录
        netOut.write(("[2]"+id).getBytes());
        //接收结果
        ObjectInputStream objIn = new ObjectInputStream(netIn);
        Object obj = objIn.readObject();
        objIn.close();

        if (obj==null){
            System.out.println("查询失败，无查询结果");
            return;
        }if (!(obj instanceof Student)){
            System.out.println("[失败]返回数据错误，请重试");
            return;
        }
        socket.close();
        //向下转型
        Student stu = (Student) obj;
        System.out.println("[查询结果]");
        printStudent(stu);

        //接受新数据：
        System.out.println("请输入新姓名(保留原值请输入0)：");
        String newName = sc.next();
        System.out.println("请输入新性别(保留原值请输入0)：");
        String newSex = sc.next();
        System.out.println("请输入新年龄(保留原值请输入0)：");
        int newAge = sc.nextInt();

        if (!"0".equals(newName)){
            stu.setName(newName);
        }if (!"0".equals(newSex)){
            stu.setSex(newSex);
        }if (newAge!=0){
            stu.setAge(newAge);
        }
        socket = getSocket();
        //发送修改数据，格式：[3]....
        netOut = socket.getOutputStream();
        netOut.write(("[3]" + stu.getId() + "," +
                stu.getName() + "," +
                stu.getSex() + "," +
                stu.getAge()).getBytes());
        //接收反馈
        netIn = socket.getInputStream();
        int b = netIn.read();

        if (b==0){
            System.out.println("数据修改成功");
        }else {
            System.out.println("数据修改失败，请重试");
        }
        return;

    }
    //todo 3.删除学员
    public static void deleteStudent(Scanner sc) throws IOException, ClassNotFoundException {
        System.out.println("请输入要删除的学员ID：");
        int id = sc.nextInt();
        //获取链接：
        Socket socket = getSocket();
        //3.发送"查询"请求
        OutputStream netOut = socket.getOutputStream();
        InputStream netIn = socket.getInputStream();
        //标记："2"根据ID查询一条记录
        netOut.write(("[2]"+id).getBytes());

        ObjectInputStream objIn = new ObjectInputStream(netIn);
        //接收结果
        Object obj = objIn.readObject();
        if (obj==null){
            System.out.println("查询失败，无结果");
            return;
        }
        if(!(obj instanceof Student)){
            System.out.println("失败，返回数据错误，请重试");
            return;
        }
        //向下转型
        Student stu = (Student) obj;
        System.out.println("【查询结果】");
        printStudent(stu);//打印

        //关闭socket
        socket.close();

        //确认删除
        System.out.println("【确认】你确定删除这条记录吗？(y/n)：");
        String op = sc.nextLine();
        if (op.equals("y")){
            System.out.println("取消，操作被取消");
            return;
        }
        //再次链接
        socket = getSocket();
        //发送删除数据，格式：[5]id值....
        netOut = socket.getOutputStream();
        netOut.write(("[5]"+stu.getId()).getBytes());
        //接收反馈
        netIn = socket.getInputStream();
        int b = netIn.read();
        if (b==0){
            System.out.println("数据已经成功删除");
        }
        else {
            System.out.println("失败，数据删除失败，请重试");
        }
        return;

    }
    //todo 4.查询学员
    public static void findStudent(Scanner sc) throws IOException,ClassNotFoundException{
        Socket socket = getSocket();
        OutputStream netOut = socket.getOutputStream();
        //2.发送请求，格式：[4]
        netOut.write(("[4]").getBytes());
        ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());

        //3.接收结果，一个序列化的ArrayList<Student>
        Object o = objIn.read();
        if (o==null){
            System.out.println("查询失败，请重试");
            return;
        }
        if (!(o instanceof ArrayList)){
            System.out.println("数据类型返回错误，请重试！");
            return;
        }
        System.out.println("查询结果：");
        ArrayList<Student> list = (ArrayList<Student>) o;
        printStudentList(list);
        //关闭连接
        socket.close();

    }

    private static Socket getSocket() {
        String ip = "127.0.0.1";
        int port = 8888;
        try {
            Socket socket = new Socket(ip, port);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //打印ArrayList<Student>的方法
    public static void printStudentList(ArrayList<Student> list) {
        System.out.println("----------------------------");
        System.out.println("编号\t\t姓名\t\t\t性别\t\t年龄");
        for (int i = 0; i < list.size(); i++) {
            Student p = list.get(i);
            System.out.println(p.getId() + "\t\t" +
                    p.getName() + "\t\t\t" +
                    p.getSex() + "\t\t" +
                    p.getAge());
        }
        System.out.println("-----------------------------");
    }
    //打印Student的方法
    public static void printStudent(Student stu){
        System.out.println("--------------------------------------------------");
        System.out.println("编号\t\t姓名\t\t性别\t\t\t年龄");
        System.out.println(stu.getId() + "\t\t" +
                stu.getName() + "\t\t\t" +
                stu.getSex() + "\t\t" +
                stu.getAge());
        System.out.println("--------------------------------------------------");
    }
}

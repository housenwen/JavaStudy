package Server;

import pojo.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 服务器端线程：
 * 一.业务功能：
 * 1).接收客户端增、删、改、查的请求；
 * 2).调用StudentDAO处理增、删、改、查的业务；
 * 3).为客户端返回处理结果
 */

public class ServerThread extends Thread {
    private Socket socket;

    //与客户端连接的Socket对象
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream netIn = this.socket.getInputStream();
             OutputStream netOut = this.socket.getOutputStream();) {
            //接收客户端数据
            byte[] bytes = new byte[1024];
            int len = netIn.read(bytes);//只接收一次，最多1K
            String msg = new String(bytes, 0, len);
            if (msg.charAt(0) != '[' || msg.indexOf("]") == -1) {
                //关闭链接
                System.out.println("未知数据格式，线程结束");
                socket.close();
                return;
            }
            //解析标记位
            String flag = msg.substring(0 + 1, msg.indexOf("]"));
            //判断
            switch (flag) {
                case "1"://添加
                    addStudent(msg);
                    break;
                case "2"://根据id查询一条
                    System.out.println("查询一条");
                    findById(msg);
                    break;
                case "3"://修改一条
                    updateStudent(msg);
                    break;
                case "4"://查询所有
                    findAll(msg);
                    break;
                case "5"://删除一条
                    deleteById(msg);
                    break;
                default:
                    System.out.println("未知数据格式！");
                    socket.close();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除一条学员
    private void deleteById(String msg) {
        msg = msg.substring(msg.indexOf("]") + 1);
        int id = Integer.parseInt(msg);
        boolean b = StudentDao.deleteById(id);
        try {
            OutputStream netOut = socket.getOutputStream();
            if (b) {
                netOut.write(0);
            } else {
                netOut.write(1);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll(String msg) {
        ArrayList<Student> all = StudentDao.findAll();
        //直接序列化集合给客户端
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            objOut.writeObject(all); //关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void updateStudent(String msg) {
        msg = msg.substring(msg.indexOf("]") + 1);//"1,张三,男,22"
        String[] arr = msg.split(",");
        Student stu = new Student();
        stu.setId(Integer.parseInt(arr[0]));
        stu.setName(arr[1]);
        stu.setSex(arr[2]);
        stu.setAge(Integer.parseInt(arr[3]));
        boolean b = StudentDao.updateStudent(stu);
        try (OutputStream netOut = socket.getOutputStream()) {
            if (b) {
                netOut.write(0);
            } else {
                netOut.write(1);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findById(String msg) {
        msg = msg.substring(msg.indexOf("]") + 1);
        int id = Integer.parseInt(msg);
        Student stu = StudentDao.findById(id);
        try {
            OutputStream netOut = socket.getOutputStream();
            //直接序列化给客户端
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("序列化");
            objOut.writeObject(stu);
            System.out.println("序列化完毕"); //关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addStudent(String msg) {
        msg = msg.substring(msg.indexOf("]") + 1);//"张三,男,22"
        String[] arr = msg.split(",");
        Student stu = new Student();
        stu.setName(arr[0]);
        stu.setSex(arr[1]);
        stu.setAge(Integer.parseInt(arr[2]));
        boolean b = StudentDao.addStudent(stu);
        //返回给客户端处理结果
        try {
            OutputStream netOut = socket.getOutputStream();
            if (b) {
                netOut.write(0);
            } else {
                netOut.write(1);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


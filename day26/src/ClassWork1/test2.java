package ClassWork1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class test2 {
    public static void main(String [] args)   {
        Employee e = new Employee();
        e.name = "zhangsan";
        e.address = "beiqinglu";
        e.age = 20;
        try {
            // �������л�������
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.txt"));
            // д������
            out.writeObject(e);
            // �ͷ���Դ
            out.close();
//            fileOut.close();
            System.out.println("Serialized data is saved"); // ��������ַ�����л�������û�б����л���
        } catch(IOException i)   {
            i.printStackTrace();
        }
    }
}

package ClassWork1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class test1 {
    public static void main(String [] args)   {
        Employee e = null;
        try {
            // ���������л���
            FileInputStream fileIn = new FileInputStream("employee.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // ��ȡһ������
            e = (Employee) in.readObject();
            // �ͷ���Դ
            in.close();
            fileIn.close();
        }catch(IOException i) {
            // ���������쳣
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)  {
            // �������Ҳ����쳣
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        // ���쳣,ֱ�Ӵ�ӡ���
        System.out.println("Name: " + e.name);	// zhangsan
        System.out.println("Address: " + e.address); // beiqinglu
        System.out.println("age: " + e.age); // 0
    }
}

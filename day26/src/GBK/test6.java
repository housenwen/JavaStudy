package GBK;

import java.io.*;

public class test6 {
    public static void main(String[] args) throws IOException{
        // 1.�����ļ�·��
        String srcFile = "aaa";
        String destFile = "bbb";
        // 2.����������
        // 2.1 ת��������,ָ��GBK����
        Reader isr = new InputStreamReader(new FileInputStream(srcFile));
        // 2.2 ת�������,Ĭ��utf8����
        Writer osw = new OutputStreamWriter(new FileOutputStream(destFile),"GBK");
        // 3.��д����
        // 3.1 ��������

        // 3.2 ���峤��
        int len;
        // 3.3 ѭ����ȡ
        while ((len = isr.read())!=-1) {
            // ѭ��д��
            osw.write((char) len);
        }
        // 4.�ͷ���Դ
        osw.close();
        isr.close();
    }
}

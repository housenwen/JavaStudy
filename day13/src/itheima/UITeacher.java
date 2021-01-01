package itheima;

public class UITeacher extends YuanGong  implements paint{

    public UITeacher() {
    }

    public UITeacher(String name, String sex, String age) {
        super(name, sex, age);
    }

    @Override
    public void work() {
        System.out.println("疯狂绘图！");

    }

    @Override
    public void paint() {
        System.out.println("我会油画！！！");
    }
}

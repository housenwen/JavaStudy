package itheima02;

public class Company implements Money{

    private double ZongMoney = 1000000.0;

    public Company() {
    }

    public Company(double zongMoney) {
       this.ZongMoney = zongMoney;
    }

    @Override
    public void paySalary(Employee emp) {

        ZongMoney -= emp.getGongZi();
        System.out.println("给"+emp.getName()+"发工资"+emp.getGongZi()+" 元,"+"公司剩余："+ZongMoney+" 元");

    }
}

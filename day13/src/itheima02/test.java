package itheima02;

public class test {
    public static void main(String[] args) {

        Manager manager = new Manager("张小强",9000.0);
        Coder coder = new Coder("李小亮",5000.0);

        Company company = new Company();

        company.paySalary(manager);
        company.paySalary(coder);

    }
}

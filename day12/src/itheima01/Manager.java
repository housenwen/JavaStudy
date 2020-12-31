package itheima01;

 class Manager extends Employee{
     public Manager() {
     }
     public Manager(String id, String name, double salary) {
         super(id, name, salary);
     }
     // 2.重写父类的抽象方法
     @Override
     public void work() {
         System.out.println("管理其他人");
     }
 }

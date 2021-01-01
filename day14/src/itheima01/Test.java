package itheima01;

class Test{
    public static void main(String[] args){
        //定义方法内部类 实现 Animal 接口
        class Cat implements Animal {
            @Override
            public void show() {
                System.out.println("成员内部类作为参数...");
            }
        }

        Cat c = new Cat();

        fun(c);//请用子类的形式调用
        fun(new Animal() {
            @Override
            public void show() {
                System.out.println("匿名内部类作为参数...");
            }
        });//请用匿名内部类的形式调用
    }
    public static void fun(Animal a ){
        a.show();
    }
}
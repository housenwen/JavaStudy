package ClassWork1;

 class Employee implements java.io.Serializable {
    public String name;
    public String address;
    public transient int age; // transient˲̬���γ�Ա,���ᱻ���л�
    public void addressCheck() {
        System.out.println("Address  check : " + name + " -- " + address);
    }
}


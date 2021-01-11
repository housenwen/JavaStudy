package ClassWork01;

public class Student {
    private String name;
    private int socer;

    public Student(String name, int socer) {
        this.name = name;
        this.socer = socer;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSocer(int i) {
        return socer;
    }

    public void setSocer(int socer) throws ScoreException {
        if (socer<0){
            throw new ScoreException("分数不为负数");
        }
        if (socer>100){
            throw new ScoreException("分数不能大于100");
        }
        this.socer = socer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", socer=" + socer +
                '}';
    }
}

package itheima03;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int socer;


    public Student() {
    }

    public Student(int id, String name, int socer) {
        this.id = id;
        this.name = name;
        this.socer = socer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSocer() {
        return socer;
    }

    public void setSocer(int socer) {
        this.socer = socer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", socer=" + socer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return socer == student.socer &&
                name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, socer);
    }
}

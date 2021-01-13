package HomoWork1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class test {
    public static void main(String[] args) throws IOException {
        Students stu = new Students("迪丽热巴","女",18);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test5_1.txt"));
        oos.writeObject(stu);
        oos.close();
    }
}

    class Students implements Serializable {
        private String name;
        private String gender;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Students(String name, String gender, int age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }

        public Students() {
        }
}

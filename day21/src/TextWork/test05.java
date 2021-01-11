package TextWork;

public class test05 {
    public static void main(String[] args) throws WeightOutOfBoundsException {

        Person p = new Person(70);

        System.out.println(p);

        try {

            Person p2 = new Person(9999);

        } catch (WeightOutOfBoundsException e) {
            System.out.println(e);
            System.out.println();
        }

    }
}

package itheima05;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class testUser {
    public static void main(String[] args) {
        HashSet<User> set = new HashSet<>();
        set.add(new User("杰克", 18));
        set.add(new User("肉丝", 17));
        set.add(new User("杰克", 19));
        set.add(new User("肉丝", 16));

        for (User user :set){
            System.out.println(user);

        }
        int sum=0;
        for (User user :set){
            sum+=user.getAge();
        }

        System.out.println(sum);
        BigDecimal sum1 = new BigDecimal(sum);

        BigDecimal bd = sum1.divide(new BigDecimal(set.size()),3, RoundingMode.HALF_UP);

        System.out.println(bd);


    }
}

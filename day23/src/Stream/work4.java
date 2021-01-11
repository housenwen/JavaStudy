package Stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class work4 {
    public static void main(String[] args) {

        String [] strings = {"黄药师","冯蘅","郭靖","黄蓉","郭芙","郭襄","郭破虏"};

        Stream<String> st = Stream.of(strings);

        st.filter(s -> s.startsWith("郭")).forEach(s -> System.out.println(s));

        System.out.println("------------------------");

        String[] guos =  Stream.of(strings).filter(s -> s.startsWith("郭")).toArray(String[]::new);

        System.out.println(Arrays.toString(guos));

    }
}

package Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"陈玄风","梅超风","陆乘风","曲灵风","武眠风","冯默风","罗玉风");

        list.stream().limit(2).forEach(s -> System.out.println(s));
        System.out.println("-----------------------------------");
        list.stream().skip(list.size()-2).forEach(s -> System.out.println(s));

    }
}

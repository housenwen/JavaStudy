package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class work4 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        Collections.addAll(list,"“cab”",
                "“bac”",
                "“acb”",
                "“cba”",
                "“bca”",
                "“abc”");

        Collections.sort(list,(o1,o2)->o2.compareTo(o1));

        System.out.println(list);

    }
}

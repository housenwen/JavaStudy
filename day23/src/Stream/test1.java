package Stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.stream.Stream;

public class test1 {
    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1,-2,-3,4,-5);

        stream.map(Math::abs).forEach(s -> System.out.println(s));

    }
}

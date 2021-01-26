package HW;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.List;

public class test3 {
    public static void main(String[] args) throws Exception{

        SAXReader saxReader = new SAXReader();

        FileInputStream fis = new FileInputStream("bs.xml");

        Document document = saxReader.read(fis);

        Element root = document.getRootElement();

        List<Element> elements = root.elements("book");

        for (Element book :elements){
            String bookName = book.elementText("name");
            String author = book.elementText("author");
            String price = book.elementText("price");
            String body = book.elementText("body");

            System.out.println(bookName);
            System.out.println(author);
            System.out.println(price);
            System.out.println(body);
            System.out.println("---------------------------");
        }

    }
}

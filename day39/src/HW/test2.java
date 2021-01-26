package HW;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class test2 {
    public static void main(String[] args) throws Exception{
        SAXReader saxReader  = new SAXReader();
        Document document  =  saxReader.read("book.xml");
        Element root = document.getRootElement();
        List<Element> bookList  = root.elements("book");

        Element bookElement  = bookList.get(0);
        Element nameElement  = bookElement.element("name");
        Element authorElement  = bookElement.element("author");
        Element priceElement  = bookElement.element("price");
        System.out.println(
                "书名："+nameElement.getText() +
                "  作者："+ authorElement.getText()+
                "  价格："+ priceElement.getText());
        System.out.println("----------------------------");
        if (bookList!=null&&bookList.size()!=0){
            for (Element book:bookList){
                String bookName = book.elementText("name");
                String author = book.elementText("author");
                String price = book.elementText("price");
                String body = book.elementText("body");

                System.out.println(bookName);
                System.out.println(author);
                System.out.println(price);
                System.out.println(body);
            }
        }

    }
}

package HW;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws Exception {
        //（1）创建xml解析器对象
        SAXReader sax = new SAXReader();
        //（2）定位xml的位置
        InputStream in = new FileInputStream("books");
        //（3）获取Document对象
        Document doc = sax.read(in);
        //（4）获取根元素
        Element root = doc.getRootElement();
        //（5）获取根元素下的所有book的元素
        List<Element> elements = root.elements();
        //（6）遍历所有的book元素
        if (elements != null && elements.size() != 0) {//判断获取的子元素集合是否为空

            for (Element book : elements) {
                //（7）获取元素中各种内容
                String bookName = book.elementText("bookName");//获取该元素的子元素所对应的元素的文本内容
                String press = book.elementText("press");
                String date = book.elementText("publicationDate");
                String price = book.elementText("price");

                //（8）输出内容
                System.out.println(bookName);
                System.out.println(press);
                System.out.println(date);
                System.out.println(price);
            }
        }

    }
}
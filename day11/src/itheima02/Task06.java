package itheima02;

public class Task06 {

        public static void main(String[] args) {
            String property = "name";
            String getPropertyMethodName = getPropertyGetMethodName(property);
            String setPropertyMethodName = getPropertySetMethodName(property);
            System.out.println(getPropertyMethodName);
            System.out.println(setPropertyMethodName);
        }
        public static String getPropertyGetMethodName(String property) {
            //property.substring(0, 1).toUpperCase():将属性的第一个字母变成大写
            //property.substring(1):获取属性的第一个字母之后的内容(不包含第一个字母)
            //"get"+....:前面拼接get
            return "get"+ property.substring(0, 1).toUpperCase()+property.substring(1);
        }
        public static String getPropertySetMethodName(String property) {
            //property.substring(0, 1).toUpperCase():将属性的第一个字母变成大写
            //property.substring(1):获取属性的第一个字母之后的内容(不包含第一个字母)
            //"set"+....:前面拼接set
            return "set"+ property.substring(0, 1).toUpperCase()+property.substring(1);
        }

}

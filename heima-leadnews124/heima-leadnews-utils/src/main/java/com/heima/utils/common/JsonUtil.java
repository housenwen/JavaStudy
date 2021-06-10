package com.heima.utils.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static <T> String toJSONString(T obj) {
        if(obj == null){
            return null;
        }
        String s = null;
        try {
            s = obj instanceof String ? (String)obj :  objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
//    public static <T> String obj2StringPretty(T obj) {
//        if (obj == null) {
//            return null;
//        }
//        try {
//            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            return null;
//        }
//    }
    public static <T> T parseObj(String jsonStr, Class<T> clazz) {
        if(jsonStr == null || jsonStr.length()==0 || clazz == null){
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class)? (T)jsonStr : objectMapper.readValue(jsonStr,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
    /**
     * 在字符串与集合对象转换时使用
     */
    public static <T> T parseObj(String str, TypeReference<T> typeReference) {
        if (str ==null || str.length() ==0 || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 在字符串与集合对象转换时使用
     */
    public static <T> T parseArray(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            return null;
        }
    }
    //亮点：模拟构造方法设计模式提供类似于阿里巴巴FastJSON的put方式构造JSON功能
    public static JsonUtil.JsonBuilder builder() {
        return new JsonUtil.JsonBuilder();
    }
    public static class JsonBuilder {
        private Map<String ,Object> map = new HashMap<>();

        JsonBuilder() {
        }
        public JsonUtil.JsonBuilder put(String key ,Object value){
            map.put(key,value);
            return this;
        }
        public String build() {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(this.map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return "{}";
        }
    }
    public static void main(String[] args) {
        Map map1 = new HashMap();
        map1.put("name","xiaoming1");
        map1.put("age",22);
        Map map2 = new HashMap();
        map2.put("name","xiaoming2");
        map2.put("age",33);
        Map map3 = new HashMap();
        map3.put("name","xiaoming3");
        map3.put("age",44);
        List<Map> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        String s = JsonUtil.toJSONString(list);
        System.out.println("序列化成json: " + s);
        List<Map> mapList = JsonUtil.parseArray(s, List.class,Map.class);
        System.out.println("反序列化obj: " + mapList);

        System.out.println(JsonUtil.builder().put("id", 1).put("name", "xiaoming").put("sex", "男").build());
    }
}

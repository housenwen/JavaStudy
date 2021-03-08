<%@ page import="com.itheima.pojo.User" %>
<%@ page import="com.itheima.pojo.Dog" %><%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //准备数据
    User user = new User();
    Dog dog = new Dog();
    dog.setSex("公");
    dog.setType("泰迪");
    user.setUsername("张三");
    user.setPassword("123");
    user.setDog(dog);

    //放到域中
    request.setAttribute("user1",user);
%>

<%--

    el表达式获取域中对象的数据

    ${对象.属性名}
--%>

${user1.username}养了一只${user1.dog.type},狗的性别是${user1.dog.sex}

</body>
</html>

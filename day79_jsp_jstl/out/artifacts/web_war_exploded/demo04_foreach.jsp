<%@ page import="java.util.List" %>
<%@ page import="com.itheima.pojo.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--
    for循环：
        1.数字循环
        2.增强for循环


      for(int i=1;i<=10;i++){
        循环体
      }

      for(obj obj:list){
        循环体
      }

--%>


<%--
    <c:foreach>
            var ：声明变量，会被丢进pageContext域，通过${key}获取显示到页面
            begin=1: 等同于 i=1
            end=10 :  等同于i<=10
            step="2": 等同于i+=2;

            items: 被遍历的集合，一般是el表达式获取域中的数据
            varStatus : 声明的变量的状态信息
                index:索引
                first:是否是集合中的第一个元素
--%>

<c:forEach var="i" begin="1" end="10" step="1">
    ${i}<br>
</c:forEach>
<br>

<%
    //准备集合数据
    List<User> userList  = new ArrayList<>();

    User user = new User();
    user.setUsername("张三");
    user.setPassword("12345");

    User user1 = new User();
    user1.setUsername("李四");
    user1.setPassword("2222");

    userList.add(user);
    userList.add(user1);

    request.setAttribute("list",userList);

%>

<%--
     todo   遍历，并且显示到表格中
    --%>

<table width="80%" border="1px" cellpadding="0" cellspacing="0">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>密码</th>
    </tr>
    <c:forEach var="user" items="${list}" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

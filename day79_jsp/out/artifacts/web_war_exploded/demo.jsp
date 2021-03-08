<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
       jsp:注释，可以注释任何内容

         jsp中书写java代码的3种语法：
         1. 脚本声明   <%! 变量的声明 %>   底层：声明成servlet的成员变量
         2. 脚本表达式  <%= %>  底层：被翻译成out.write("");
         3. 脚本片段   <%  书写一段java代码的  %>
   --%>
<%! int a = 1; %>

你是第<%=a%>个访问的人

<%
    int a = 2;
    int b = 3;
    int c = a * b;

%>
a*b = <%=c%>
</body>
</html>

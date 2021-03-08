<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%--
  todo  jsp指令的语法：
        <%@  指令名称   属性名=属性值  %>
    page: 页面相关属性的配置
         contentType： 就等同于response.setContentType()
         language: 语言，当前jsp支持的是java语言
         import:导包
         errorPage: 如果jsp页面发生了异常，跳转到指定的异常页面
         isErrorPage: true，表示当前页面是一个用于处理异常的页面，会有一个exception对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List list = null;
    int i = 1/0;
%>
</body>
</html>

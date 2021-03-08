<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--
		对于jsp来说，和html的对比：
         1.书写html语法和html一样
	     2.可以书写java代码
	-->
<form>
    <input type="text" name="username"><br>
    <input type="password" name="password">
</form>


<%
    //java语法
    String username = "张三";
%>

欢迎回来，<%=username%>！！
</body>
</html>

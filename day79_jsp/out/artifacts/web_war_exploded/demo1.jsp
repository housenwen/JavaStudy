<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是demo1.jsp

<%--
   todo 转发
    此处转发，参数不是放到request域的，而是放到了http请求中。
    所以我们获取此处转发的参数时，使用的是request.getParameter();
--%>

<jsp:forward page="demo2.jsp">
    <jsp:param name="username" value="zhangsan"></jsp:param>
</jsp:forward>

</body>
</html>

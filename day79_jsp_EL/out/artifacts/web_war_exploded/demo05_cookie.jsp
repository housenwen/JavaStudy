<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--

       java代码如何获取cookie?
       request.getCookies();
       遍历
       根据cookie的名字判断cookie对象
       cookie.getValue(); 获取值


       el表达式从cookie中获取数据:
       ${cookie.cookieName.value}

   --%>

${cookie.username.value}

</body>
</html>

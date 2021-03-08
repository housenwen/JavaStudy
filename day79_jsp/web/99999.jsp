<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  todo     jsp中有9大内置对象，可以直接使用。
           request:  代表了请求
           response:  代表了响应
           session: 会话对象
           application: 就是servletConext，上下文对象，内部存放着项目相关的信息
           config: 就是ServletConfig，内部存放着当前servlet相关的配置信息
           out:和字符流相应对象一样，用于响应内容
           page: 当前页面对象
           pageContext: 域对象，用于在当前页面内实现数据共享的。
           exception: 用于异常页面的

    todo       4大域对象：
           pageContext: 页面域，用于jsp当前页面数据共享的，我们一般不用
           request:用于一次请求，一次响应。用于转换
           session:一个浏览器访问任意servlet30分分钟
           servletContext:整个项目，我们一般不用，spring介入web工程使用。

    --%>
<%
    request.setAttribute("name","request域");
    session.setAttribute("name","session域");
    application.setAttribute("name","servletContext域");
    pageContext.setAttribute("name","page域");
%>

</body>
</html>

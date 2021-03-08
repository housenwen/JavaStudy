<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  todo  el表达式主要用于从4大域获取数据，展示到页面的。
    el:表达式从4大域获取数据的语法
        ${域对象名称.属性名}
        ${pageScope.name}      从页面域获取数据
        ${requestScope.name}    request域
        ${sessionScope.name}    session域获取数据
        ${applicationScope.name}  servletContext域获取数据
      最终写法： {属性名}
      依次从4大域搜索数据，顺序   page>request>session>servletContext
--%>
<%
    //向4大域存储数据
    //pageContext.setAttribute("name","page域");
    //request.setAttribute("name","request域");
    //session.setAttribute("name","session域");
    application.setAttribute("name","application域");
%>

<%--
   todo java代码从4大于获取数据，并且展示到页面
--%>
<%=pageContext.getAttribute("name")%>
<%=request.getAttribute("name")%>
<%=session.getAttribute("name")%>
<%=application.getAttribute("name")%>

<%--todo el表达式从4大域获取数据展示到页面--%>

<br>
el表达式从4大域获取数据<br>
${pageScope.name}
${requestScope.name}
${sessionScope.name}
${applicationScope.name}

<br>
el表达式最终写法：
${name}
</body>
</html>

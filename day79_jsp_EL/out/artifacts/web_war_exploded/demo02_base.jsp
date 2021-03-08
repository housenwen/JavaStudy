<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("username","pageContext域");
    request.setAttribute("username","request域");
    session.setAttribute("username","session域");
    application.setAttribute("username","servletContext域");

%>

<%--

        todo    el表达式的基础语法：  ${域对象.属性名}
                el表达式中： pageScope对象代表了pageContext对象的域功能
                        requestScope对象代表了request对象的域功能
                        sessionScope对象代表了session对象的域功能
                        applicationScope对象代表了servletContext对象的域功能
        todo     el表达式的简写方式：  ${属性名}
                    依次从4大域搜索数据： pageSocpe >  rquestSoce > sessionScope > applicationScope
--%>
<%--
      todo  <%=pageContext.getAttribute("username")%>
            <%=request.getAttribute("username")%>
            <%=session.getAttribute("username")%>
            <%=application.getAttribute("username")%>
    --%>

${pageScope.username}<br>
${requestScope.username}<br>
${sessionScope.username}<br>
${applicationScope.username}<br>
<br>
实际企业开发el的用法<br>
${username}<br>

</body>
</html>

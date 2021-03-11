<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>${NAME}</title>
  </head>
  <body>
<h3>listener知识学习</h3>
  <h5>在线人数：${applicationScope.number}</h5>
  <a href="${pageContext.request.contextPath}/LogoutServlet">用户退出</a>
  </body>
</html>
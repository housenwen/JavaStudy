<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:53
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
    if: 用于条件判断的
        test:必须的，条件表达式


    ps:
        1.test属性不支持计算，所以一般是写el表达式
        2.没有else标签，在写一个if，条件取反即可

--%>

<%
    request.setAttribute("num",4);
%>

<c:if test="${num>5}">
    数值大于5
</c:if>
<c:if test="${!(num>5)}">
    数值不大于5
</c:if>



</body>
</html>

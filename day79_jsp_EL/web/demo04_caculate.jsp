<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    List<String> abc = new ArrayList<>();
    request.setAttribute("abc",abc);
%>

<%--
        el表达式的计算：
            算术运算   +  -   *   /   %
            比较运算  >   <   ==
            逻辑运算  && ||  !
            三元运算  ${表达式1?value1:value2}  ,简单判断并且取值时
            非空判单  empty

                    ""    true
                    "  "  false
                    null  true

                 集合里面没有元素，会认为是空的
    --%>

${empty abc}

</body>
</html>

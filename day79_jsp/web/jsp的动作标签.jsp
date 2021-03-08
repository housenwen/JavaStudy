<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
 todo 作用一致，引入公共资源。原理不一致
      <jsp:include>动态包含：先对包含的内容进行编译，然后再包含进来。
      <%@ include> 静态包含：先将内容引入到源码中，然后再进行编译。
      如果有10个页面，同时引入header.jsp
      动态包含来书：header.jsp的内容编译一次。
      静态包含来说：header.jsp的内容会被引入10次，所以重新编译10次。
      --%>

<jsp:include page="include.jsp"></jsp:include>
主体内容
</body>
</html>

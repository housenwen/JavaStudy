<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
        1.到后面学习maven时候，有些同学在jsp中书写el表达式不生效。
           在page指令中：  isELIgnored="false"  不忽略el表达式
--%>


<%--
        前端路径：
            1.全路径
            2.全路径简写 : (jsp中推荐)  /虚拟项目名/资源名
            3.相对路径：（推荐） html都用这个
--%>

${pageContext.request.contextPath}


</body>
</html>

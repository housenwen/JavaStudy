<%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="1000" cellpadding="0" cellspacing="0"  border="1px" align="center" bgcolor="#ff1493">
<tr>
    <th>编号</th>
    <th>姓名</th>
    <th>爱好</th>
    <th>性别</th>
    <th>地址</th>
    <th>密码</th>
</tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.hobby}</td>
            <td>${user.sex==1?"男":"女"}</td>
            <td>${user.address}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="com.itheima.pojo.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13008
  Date: 2021/3/8
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //准备集合数据：
    List<User> userList = new ArrayList<>();
    User user = new User();
    user.setUsername("张三");
        user.setPassword("1234");

        User user1 = new User();
        user1.setUsername("李四");
        user1.setPassword("3333");

        userList.add(user);
        userList.add(user1);
%>

<%--
todo 遍历，并且显示到表格中
--%>

<table width="500" border="1px" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <th>姓名</th>
        <th>密码</th>
    </tr>
    <%
        for (User u:userList){
    %>
    <tr>
        <td><%=u.getUsername()%></td>
        <td><%=u.getPassword()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

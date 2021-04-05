<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--导入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表jsp页面</title>
    <style>
        table {border:1px solid #000000}
        table th{border:1px solid #000000}
        table td{border:1px solid #000000}
    </style>

    <script>

        // 添加用户
        function toAdd(id) {
            window.location.href="${pageContext.request.contextPath}/customer/toAdd";
        }

        // 修改用户
        function toUpdate(id) {
            window.location.href="${pageContext.request.contextPath}/customer/toUpdate?customerId="+id;
        }
        // 删除用户
        function  doDel(id) {
            if(confirm("确定要删除吗？")){
                window.location.href="${pageContext.request.contextPath}/customer/doDel?customerId="+id;
            }
        }
    </script>
</head>
<body>
用户列表：<button type="button"  onclick="toAdd()">添加用户</button>
<table cellpadding="0" cellspacing="0" width="80%">
    <tr>
        <th>编号</th>
        <th>公司名称</th>
        <th>信息来源</th>
        <th>所属行业</th>
        <th>级别</th>
        <th>联系地址</th>
        <th>联系电话</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}" var="customer">
        <tr>
            <td>${customer.custId}</td>
            <td>${customer.custName}</td>
            <td>${customer.custSource}</td>
            <td>${customer.custIndustry}</td>
            <td>${customer.custLevel}</td>
            <td>${customer.custAddress}</td>
            <td>${customer.custPhone}</td>
            <td>
                <button type="button"  onclick="toUpdate('${customer.custId}')">修改</button>
                <button type="button"  onclick="doDel('${customer.custId}')">删除</button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>
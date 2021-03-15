<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查询所有联系人</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        tr,th {
            text-align: center;
        }
        h4{
            color: green;
        }
    </style>
</head>
<body>
<div class="container">
    <form  method="get" class="form-inline" id="contactForm">
        <h3>联系人列表</h3>
		<div class="row text-right" style="margin-bottom: 10px; margin-top: 15px;">
            <h4>欢迎回来,xxx</h4>

			姓名：<input id="name" type="text" value="${name}" name="name" class="form-control" placeholder="搜索的名字" style="width: 120px;"/>
			年龄：
			<input id="minAge" type="number" value="${minAge==0?"":minAge}" name="min" class="form-control" style="width: 60px;"/>~
			<input id="maxAge" type="number" value="${maxAge==0?"":maxAge}" name="max" class="form-control" style="width: 60px;"/>
			<input onclick="search2()" class="btn btn-default" value="查询"></input>
		</div>
        <div class="row">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>籍贯</th>
                    <th>QQ</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>

                <c:forEach var="contact" items="${pageBean.list}" varStatus="status">

                    <tr>
                        <td>${contact.id}</td>
                        <td>${contact.name}</td>
                        <td>${contact.sex}</td>
                        <td>${contact.age}</td>
                        <td>${contact.address}</td>
                        <td>${contact.qq}</td>
                        <td>${contact.email}</td>
                        <td>
                            <div class="btn-group btn-group-sm">
                                <a class="btn btn-primary btn-xs" href="#">添加</a>
                                <a class="btn btn-success btn-xs" href="findUserById.do?id=${contact.id}">修改</a>&nbsp;
                                <a class="btn btn-info btn-xs" href="#">删除</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <div class="row text-center">
            <div class="btn-group btn-group-sm">
					<a href="findContactByPage.do?currentPage=1&pageSize=${pageBean.pageSize}" class="btn btn-default">首页</a>
					<%-- 当前页码数大于1，显示上一页--%>
                    <c:if test="${pageBean.currentPage>1}">
                        <a href="findContactByPage.do?currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}" class="btn btn-default">上页</a>

                    </c:if>
                    <%-- 当前页码数小于总页码数，显示下一页--%>
                    <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                        <a href="findContactByPage.do?currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}" class="btn btn-default">下页</a>
                    </c:if>
					<a href="findContactByPage.do?currentPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}" class="btn btn-default">末页</a>
            </div>
			 每页

            <select onchange="findContactByPage(1,this.value)" id="pageSize" class="form-control" >
                <option ${pageBean.pageSize==3?"selected":""} value="3">3</option>
                <option ${pageBean.pageSize==5?"selected":""}  value="5">5</option>
                <option ${pageBean.pageSize==10?"selected":""}  value="10">10</option>
            </select>
            条

            第
            <select onchange="findContactByPage(this.value,${pageBean.pageSize})" id="current" class="form-control" name="current">

                <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                    <%-- 当前页码数选中--%>
                    <option  ${pageBean.currentPage==i?"selected":""} value="${i}">${i}</option>
                </c:forEach>
            </select>
            页/共${pageBean.totalPage}页
            共${pageBean.totalCount}条
        </div>
    </form>
</div>
</body>
<script type="text/javascript">

    function findContactByPage(currentPage,pageSize) {
        location.href="findContactByPage.do?pageSize="+pageSize+
                                                "&currentPage="+currentPage+
                                                "&name="+name+
                                                "&minAge="+minAge+
                                                "&maxAge="+maxAge;

        //console.log("sssssss:"+minAge);
    }

    //默认获取当前输入框的值
    var name =document.getElementById("name").value;
    var minAge =document.getElementById("minAge").value;
    var maxAge =document.getElementById("maxAge").value;

    function search2() {
        //获取输入框的值
        name = document.getElementById("name").value;
        minAge = document.getElementById("minAge").value;
        maxAge = document.getElementById("maxAge").value;
        //发送分页请求
        findContactByPage(1,${pageBean.pageSize});
    }

</script>

</html>

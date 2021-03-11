<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <form action="list" method="get" class="form-inline" id="contactForm">
        <h3>联系人列表</h3>
		<div class="row text-right" style="margin-bottom: 10px; margin-top: 15px;">
            <h4>欢迎回来,xxx</h4>

			姓名：<input type="text" name="name" class="form-control" placeholder="搜索的名字" style="width: 120px;"/>
			年龄：
			<input type="number" name="min" class="form-control" style="width: 60px;"/>~
			<input type="number" name="max" class="form-control" style="width: 60px;"/>
			<button class="btn btn-default"><i class="glyphicon glyphicon-search"></i>查询</button>
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
                <c:forEach var="contact" items="${list}">
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
                                <a class="btn btn-success btn-xs" href="findContactById?id=${contact.id}">修改</a>&nbsp;
                                <a class="btn btn-info btn-xs" href="#">删除</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="row text-center">
            <div class="btn-group btn-group-sm">
					<a href="#" class="btn btn-default">首页</a>
					<a href="#" class="btn btn-default">上页</a>
					<a href="#" class="btn btn-default">下页</a>
					<a href="#" class="btn btn-default">末页</a>
            </div>
			 每页
            <input type="number" class="form-control" name="size" value="" style="width: 60px;" id="size"/>
            条

            第
            <select id="current" class="form-control" name="current">

                    <option value="">1</option>

            </select>
            页/共5页
            共25条
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
</script>

</html>

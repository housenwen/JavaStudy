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
    <form action="findContactByPage" method="get" class="form-inline" id="contactForm">
        <h3>联系人列表</h3>
		<div class="row text-right" style="margin-bottom: 10px; margin-top: 15px;">
            <h4>欢迎回来,xxx</h4>

            <%-- 分页参数
                currentPage: 默认查询第一页
                pageSize:  上次一的值
               --%>
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="pageSize" value="${pageBean.pageSize}">

			姓名：<input type="text" value="${pageBean.name}" name="name" class="form-control" placeholder="搜索的名字" style="width: 120px;"/>
			年龄：
			<input type="number" value="${pageBean.min}" name="min" class="form-control" style="width: 60px;"/>~
			<input type="number" value="${pageBean.max}" name="max" class="form-control" style="width: 60px;"/>
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
                <%-- 用户信息 --%>
                <c:forEach var="contact" items="${pageBean.list}">
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
                                <a class="btn btn-success btn-xs" href="findUserById?id=${contact.id}">修改</a>&nbsp;
                                <a class="btn btn-info btn-xs" href="#">删除</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="row text-center">
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <%-- 当前页码数大于1 --%>
                    <c:if test="${pageBean.currentPage>1}">
                        <li>
                            <%--
                                传参：
                                    currentPage: 上一页数值 ${pageBean.prePage}
                                    pageSize: 上次选中的值  ${pageBean.pageSize}
                            --%>
<%--
                            <a href="findContactByPage?currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}" aria-label="Previous">
--%>
                            <a href="findContactByPage?currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}&name=${pageBean.name}&min=${pageBean.min}&max=${pageBean.max}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <%-- 页码数  遍历--%>
                    <c:forEach var="i" begin="1" end="${pageBean.totalPage}">
                        <%--
                            当前页码数选中：
                            i=当前页码数时，i选中状态


                            点击按钮：传参
                                currentPage: 当前按钮代表的值  ${i}
                                pageSize:   不变，就是上次选中的值，${pageBean.pageSize}
                        --%>
                       <%-- <li ${i==pageBean.currentPage?"class='active'":""} ><a href="findContactByPage?currentPage=${i}&pageSize=${pageBean.pageSize}">${i}</a></li>--%>

                        <%--
                            追加搜索条件：
                                name: 此时页面回显的条件  ${pageBean.name}
                                min: ...
                                max: ...
                        --%>
                        <li ${i==pageBean.currentPage?"class='active'":""} ><a href="findContactByPage?currentPage=${i}&pageSize=${pageBean.pageSize}&name=${pageBean.name}&min=${pageBean.min}&max=${pageBean.max}">${i}</a></li>
                    </c:forEach>


                        <%-- 当前页码数小于总页码数即可--%>
                    <c:if test="${pageBean.currentPage<pageBean.totalPage}">
                        <li>
                                <%--
                                   传参：
                                       currentPage: 下一页数值 ${pageBean.nextPage}
                                       pageSize: 上次选中的值  ${pageBean.pageSize}
                               --%>
<%--
                            <a href="findContactByPage?currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}" aria-label="Next">
--%>
                            <a href="findContactByPage?currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}&name=${pageBean.name}&min=${pageBean.min}&max=${pageBean.max}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
			 每页
            <select id="current" class="form-control" name="current" onchange="findContactByPage()">

                <option ${pageBean.pageSize==3?"selected":""} value="3">3</option>
                <option ${pageBean.pageSize==5?"selected":""} value="5">5</option>
                <option ${pageBean.pageSize==10?"selected":""} value="10">10</option>

            </select>
            条



            共${pageBean.totalPage}页
            共${pageBean.totalCount}条
        </div>
    </form>
</div>
</body>
<script type="text/javascript">


    function findContactByPage() {

        //获取下拉列表选中的值
        let pageSize =  document.getElementById("current").value;
        //向分页页面发起请求（跳转到分页资源）
        /*
            传参：
                currentPage:默认首页
                pageSize:当前下拉列表选中的值
         */
        location.href="findContactByPage?currentPage=1&pageSize="+pageSize+"&name=${pageBean.name}&min=${pageBean.min}&max=${pageBean.max}";
    }
</script>

</html>

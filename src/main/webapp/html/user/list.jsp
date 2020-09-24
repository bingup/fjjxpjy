<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/22
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/html/comom/top.jsp"%>
<%@include file="/html/comom/menu.jsp"%>
<div id="right">
    <form action="/user/findAll" method="post">
        <input type="text" name="username"  value="${username}" >
        <input type="submit" value="查询">
    </form>
    <br>
    <a href="/html/user/add.jsp">添加用户</a>
    <br>
    <table style="width: 80%;" border="1" cellspacing="0" cellpadding="10">
        <tr>
            <td>序号</td>
            <td>用户名称</td>
            <td>真实姓名</td>
            <td>性别</td>
            <td>邮箱</td>
            <td>年龄</td>
            <td>部门id</td>
            <td>部门名称</td>
            <td>登录时间</td>
            <td>是否私密</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${page.data}" var="user" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                    <%--<td>${status.index+1+(page-1)*3}</td>--%>
                <td>${user.username}</td>
                <td>${user.realName}</td>
                <td>
                        <%--方式1--%>
                    <c:choose>
                        <c:when test="${user.gender=='0'}">女</c:when>
                        <c:when test="${user.gender=='1'}">男</c:when>
                        <c:otherwise>其它</c:otherwise>
                    </c:choose>

                </td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>${user.deptId}</td>
                <td>${user.deptName}</td>
                <td>
                        <%--${user.birth}--%>
                        <%--后台传递过来的是Stirng类型--%>
                        <%--第一步：日期类型的字符串转换成日期--%>
                    <fmt:parseDate var="abc" value="${user.loginTime}" pattern="yyyy-MM-dd"></fmt:parseDate>
                        <%--第二步：格式化日期--%>
                    <fmt:formatDate value="${abc}" pattern="yyyy年MM月dd日"></fmt:formatDate>
                </td>
                    <%--方式1--%>
                <td>
                    <c:choose>
                        <c:when test="${user.isSecret=='0'}">私密</c:when>
                        <c:when test="${user.isSecret=='1'}">公开</c:when>
                    </c:choose>
                </td>

                <td>
                    <a href="/user/delete?id=${user.id}&username=${username}">删除</a>
                    <a href="/user/toupdate?id=${user.id}">修改</a>
                </td>

            </tr>
        </c:forEach>

    </table>
    <a href="/user/findAll?pageCurrent=1&username=${username}">首页</a>
    <a href="/user/findAll?pageCurrent=${page.pageCurrent-1}&username=${username}">上一页</a>
    <c:forEach begin="1" end="${page.pageTotal}" varStatus="i">
        <li><a href="/user/findAll?pageCurrent=${i.count}&username=${username}">${i.count}</a></li>
    </c:forEach>
    <a href="/user/findAll?pageCurrent=${page.pageCurrent+1}&username=${username}">下一页</a>
    <a href="/user/findAll?pageCurrent=${page.pageTotal}&username=${username}">尾页</a>

    <br>
    当前${page.pageCurrent}页，每页${page.size}条，共${page.count}条，共${page.pageTotal}页。
</div>
</body>
</html>

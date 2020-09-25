<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/25
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<%@include file="/html/comom/top.jsp" %>
<%@include file="/html/comom/menu.jsp" %>

<div id="right">

    <a href="/html/meeting/add.jsp">添加会议</a>
    <br>
    <table style="width: 80%;" border="1" cellspacing="0" cellpadding="10">
        <tr>
            <td>序号</td>
            <td>会议主题</td>
            <td>发布时间</td>
            <td>结束时间</td>
            <td>会议状态</td>
            <td>部门id</td>
            <td>部门名称</td>

        </tr>

        <c:forEach items="${page.data}" var="meeting" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                    <%--<td>${status.index+1+(page-1)*3}</td>--%>
                <td>${meeting.title}</td>
                <td>            <%--${user.birth}--%>
                        <%--后台传递过来的是Stirng类型--%>
                        <%--第一步：日期类型的字符串转换成日期--%>
                    <fmt:parseDate var="abc" value="${meeting.startTime}" pattern="yyyy-MM-dd"></fmt:parseDate>
                        <%--第二步：格式化日期--%>
                    <fmt:formatDate value="${abc}" pattern="yyyy年MM月dd日"></fmt:formatDate>
                </td>
                <td>

                        <%--${user.birth}--%>
                        <%--后台传递过来的是Stirng类型--%>
                        <%--第一步：日期类型的字符串转换成日期--%>
                    <fmt:parseDate var="abc" value="${meeting.startTime}" pattern="yyyy-MM-dd"></fmt:parseDate>
                        <%--第二步：格式化日期--%>
                    <fmt:formatDate value="${abc}" pattern="yyyy年MM月dd日"></fmt:formatDate>


                </td>
                <td>
                        <%--方式1--%>
                    <c:choose>
                        <c:when test="${meeting.status=='0'}">未开始</c:when>
                        <c:when test="${meeting.status=='1'}">进行中</c:when>
                        <c:otherwise>已结束</c:otherwise>
                    </c:choose>
                </td>
                <td>${meeting.deptId}</td>
                <td>${meeting.deptName}</td>


            </tr>
        </c:forEach>

    </table>
    <a href="/meet/findAll?pageCurrent=1&title=${title}">首页</a>
    <a href="/meet/findAll?pageCurrent=${page.pageCurrent-1}&title=${title}">上一页</a>
    <c:forEach begin="1" end="${page.pageTotal}" varStatus="i">
        <li><a href="/meet/findAll?pageCurrent=${i.count}&title=${title}">${i.count}</a></li>
    </c:forEach>
    <a href="/meet/findAll?pageCurrent=${page.pageCurrent+1}&title=${title}">下一页</a>
    <a href="/meet/findAll?pageCurrent=${page.pageTotal}&title=${title}">尾页</a>

    <br>
    当前${page.pageCurrent}页，每页${page.size}条，共${page.count}条，共${page.pageTotal}页。
</div>
</body>
</html>

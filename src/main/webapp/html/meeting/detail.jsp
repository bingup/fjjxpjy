<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/26
  Time: 9:49
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
    <%--
    //需要参加会议
      1参加
      2取消
    //不需要参加会议
    --%>

    <c:if test="${map.flag!=1}">
        <c:if test="${map.flag==2}">
            <%--说明已经参加--%>
            <a href="/meet/exitMeeting?id=${meeting.id}" class="btn btn-danger">取消</a>
        </c:if>
        <c:if test="${map.flag==3}">
            <a href="/meet/joinMeeting?id=${meeting.id}" class="btn btn-success">参加</a>
        </c:if>
    </c:if>
    <c:if test="${map.flag==1}">不需要参加会议
    原因：${map.msg}
    </c:if>

    <br><br>
    标题：${meeting.title}<br><br>

    部门名称:${meeting.deptName}<br><br>

    应到：${map.should}<br><br>
    实到：${map.realCount}<br><br>
    未到：${map.should-map.realCount}<br><br>

    开始时间：${meeting.startTime}<br><br>

    结束时间：${meeting.endTime}<br><br>

    会议内容：<textarea rows="5" cols="70" name="content">${meeting.content}</textarea><br><br>
</div>
</body>
</html>

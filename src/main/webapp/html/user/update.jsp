<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/23
  Time: 18:06
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
    <form action="/user/update" method="post">
        <h2>修改</h2>
        用户名：<input type="text"  name="username" value="${user.username}"><br>
        <input type="hidden" name="id" value="${user.id}">
        <%--部门id：<input type="text"  name="deptId" value="${user.deptId}"><br>--%>
        <select  name="DeptId" >
            <c:forEach items="${list}" var="dept">
            <option value="${dept.id}" <c:if test="${dept.id==user.deptId}">selected </c:if> >${dept.name}</option>
            </c:forEach>
        </select>

        邮箱:<input type="text"  name="email" value="${user.email}"><br>
        真实姓名：<input type="text"  name="realName" value="${user.realName}"><br>
        qq登录标识符：<input type="text"  name="qqOpenid" value="${user.qqOpenid}"><br>
        wx登录标识符：<input type="text"  name="wxOpenid" value="${user.wxOpenid}"><br>
        年龄：<input type="text"  name="age" value="${user.age}"><br>
        性别：<input type="radio" name="gender" value="1"
               <c:if test="${user.gender == 1}">checked</c:if> >男
        <input type="radio" name="gender" value="0"
               <c:if test="${user.gender == 0}">checked</c:if>>女 <br>
        是否私密:<input type="radio" name="isSecret" value="1"
               <c:if test="${user.isSecret == 1}">checked</c:if> >公开
        <input type="radio" name="isSecret" value="0"
               <c:if test="${user.isSecret == 0}">checked</c:if>>私密<br>
        登录时间：<input type="text" name="loginTime" value="${user.loginTime}" > <br>
        手机号<input type="text" name="phone" value="${user.phone}" ><br>
        创建时间：<input type="text" name="registerTime" value="${user.registerTime}" ><br>
        <input type="submit" value="修改">
    </form>
</div>
</body>
</html>

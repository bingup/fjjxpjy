<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/23
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/login" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text"  value="admin" name="username"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" value="admin" name="password"/></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="remember" value="1">7天内免登录</td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"/></td>
        </tr>
    </table>
</form>
</body>
</html>

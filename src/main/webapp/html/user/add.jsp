<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/23
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/add" method="post">
    <h2>添加</h2>
    用户名：<input type="text"  name="username" ><br>
    密码：<input type="text"  name="password" ><br>
    邮箱:<input type="text"  name="email"><br>
    真实姓名：<input type="text"  name="realName" ><br>
    qq登录标识符：<input type="text"  name="qqOpenid" ><br>
    wx登录标识符：<input type="text"  name="wxOpenid" ><br>
    简介：<input type="text"  name="desc1" ><br>
    年龄：<input type="text"  name="age" ><br>
    性别：<input type="radio" name="gender" value="1">男
    <input type="radio" name="gender" value="0" >女 <br>
    是否私密:<input type="radio" name="isSecret" value="1">公开
    <input type="radio" name="isSecret" value="0">私密<br>
    手机号<input type="text" name="phone" ><br>

    <select id="newOption" name="deptId">
        <option  value="">请选择部门</option>
        <select>
            <br>
       <input type="submit" value="添加">
</form>
</body>
<script>

    window.onload =function (ev) {

        $.ajax({
            url: "/dept/findAll",
            type: "post",
            data: "",
            dataType: "json",
            success: function (data) {

                var html ="";
                for (var i = 0; i <data.length ; i++) {
                    html += '<option  value="'+ data[i].id+
                        '">' +data[i].name+
                        '</option>';
                }
                $("#newOption").append(html);

            }

        });
    }
</script>
</html>

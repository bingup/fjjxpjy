<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/22
  Time: 15:15
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

    <form action="/meet/add" method="post">
        <h2>发布会议</h2>
        标题：<input type="text"  name="title" ><br>
        <select id="deptId" name="deptId">
            <option  value="">请选择部门</option>
            <select>
        <div id="newName">


        </div><br>
        开始时间:<input type="datetime-local" name="startTime"><br>
        终止时间:<input type="datetime-local" name="endTime"><br>
        会议内容:<input type="text" name =content><br>
                <br>
                <input type="submit" value="添加">
    </form>
</div>
<script>
    $(function () {
        $.ajax({
            url: "/dept/findAll",
            type: "post",
            data: "",
            dataType: "json",
            success: function (data) {
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += '<option  value="' + data[i].id +
                        '">' + data[i].name +
                        '</option>';
                }
                $("#deptId").append(html);

            }

        });

        $("#deptId").change(function () {
            $.ajax({
                url: "/user/getUserByDeptId",
                type: "post",
                data: {"deptId":$("#deptId").val()},
                dataType: "json",
                success: function (data) {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += '<input type="checkbox" name="makeUsers" value="' +data[i].id+
                            '">' +data[i].username+'';
                    }
                    $("#newName").append(html);
                }

            });
        })
        
    })


</script>
</body>
</html>

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
    <style>
        #top {
            border: 1px solid red;
            height: 10%;
        }

        #left {
            border: 1px solid red;
            width: 10%;
            height: 88%;
            float: left;
        }

        #right {
            border: 1px solid red;
            margin-left: 5px;
            width: 89%;
            height: 88%;
            float: left;
        }
        #detail-img {
            height: 50px;

        }

    </style>
</head>
<body>
<div id="top">
    这是顶部
    <img id="detail-img" src="/img/getHead?pic=${loginUser.pic}" alt="加载中"/>


    <!-- 真正的头像图片上传表单 -->
    <input type="file" id="picFile" style="display: none;">

</div>
</body>
<script>
    $(function () {

        $("#detail-img").click(function () {
            // 点击图片时触发文件表单控件
            $("#picFile").click();
        });

        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            // 上传图片
            $.ajax({
                url: "/img/updatePic",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                data: formData,
                type: "post",
                dataType: "json",
                async: true,
                success: function (data) {
                    alert(123);
                    if (data.code == '200') {
                        //成功
                        $("#detail-img").attr("src", "/img/getHead?pic=" + data.msg);
                    } else {
                        alert(data.msg);
                    }
                }
            });

        });
    });
</script>
</html>

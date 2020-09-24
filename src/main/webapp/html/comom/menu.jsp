<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/22
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div id="left">

</div>
</body>
<script>
    $(function () {
        $.ajax({
            url: "/menu/listAll",
            type: "post",
            data: "",
            dataType: "json",
            success: function (data) {
                var parent = data.parent;
                var son = data.son;
                var html="";
                for (var i = 0; i <parent.length ; i++) {
                    html +=parent[i].name+'<ul>';
                    for (var j = 0; j <son.length ; j++) {
                        if (son[j].parentId == parent[i].id) {
                            console.log(son[j].url);
                            html += ' <li><a href="'+son[j].url+'">'+son[j].name+'</a></li>';
                        }
                    }
                    html +='</ul>';
                }
                $("#left").append(html);

            }

        });
    })


</script>
<script src="/js/menu.js" type="text/javascript"></script>
</html>

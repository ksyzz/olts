<%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/13
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/login.css" rel="stylesheet" type="text/css">

    <script type="text/javascript">

        $(function () {

            $("#register").on('click',function () {
                var id = $("#id").val();
                var userName = $("#userName").val();
                var password = $("#password").val();
                if (id == "" || userName == "" || password == ""){
                    $("#register_error").html("请完善信息");
                    return;
                }
                $.ajax({
                    url:"/account/register",
                    type:"post",
                    data:$("#register_message").serialize(),
                    error:function (data) {
                        $("#register_error").html(data.responseText);
                    },
                    success:function () {
                        window.open("/pages/login.jsp", '_self');
                    }
                })
            })
        })
    </script>
</head>
<body>
<div style="text-align: center"> <h1>欢迎访问在线考试系统</h1></div>
<hr/>
<div class="place">
    <form class="form" id="register_message" method="post" action="/account/register">
        <label class="label">帐  号: </label><input class="text" type="text" placeholder="学号/教职工号" id="id" name="id"><label class="error"></label><br>
        <br>
        <label class="label">昵  称: </label><input class="text" type="text" placeholder="请输入用户名" id="userName" name="userName"><label class="error"></label><br>
        <br>
        <label class="label">密  码: </label><input class="text" type="password" placeholder="请输入密码" id="password" name="password"><label class="error"></label><br>
        <br>
        <label class="label">用户类型: </label><input type="radio" name="account_type" value="teacher"><label class="label">教师</label><input type="radio" name="account_type" value="student" checked="checked"><label class="label">学生</label>
        <br>
        <div id="register_error" class="error"></div><br>
        <button type="button" id="register">注册</button>
    </form>
</div>
</body>
</html>

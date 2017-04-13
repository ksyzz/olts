<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" >
        $(function () {
            $("#login").on('click', function () {
                $.ajax({
                    url: "/account/login",
                    type: "post",
                    data: $("#login_message").serialize(),
                    error: function (request, status, error) {
                        $("#login_error").append("帐号或密码错误");
                    }
//                    success:function (data) {
//                        $("#error").html(data);
//                    }
                })
            })
            $("#register").on('click', function () {
                window.open("/pages/register.jsp", "_self")
            })
        })
    </script>
</head>
<body>
<div style="text-align: center"> <h1>欢迎访问在线考试系统</h1></div>
<hr/>
    <div class="place">
            <form class="form" id="login_message" >
                <label class="label">帐  号: </label><input class="text" type="text" placeholder="id" name="id" ><br>
                <br/>
                <label class="label">密  码: </label><input class="text" type="password" placeholder="password" name="password"><br>
                <div id="login_error" class="error"></div><br>
                <button type="button" id="login">登录</button> <button type="button" id="register" >注册</button>
            </form>
    </div>

</body>
</html>

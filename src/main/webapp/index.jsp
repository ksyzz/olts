<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <script src="js/jQuery3.2.js" type="text/javascript"></script>
    <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<div style="text-align: center"> <h1>欢迎访问在线考试系统</h1></div>
<hr/>
    <div class="place">
        <form class="form" action="/account/login" method="post">
            <label class="label">帐  号: </label><input class="text" type="text" placeholder="username" name="userName" id="userName"><br>
            <br/>
            <label class="label">密  码: </label><input class="text" type="password" placeholder="password" name="password" id="password"><br>
            <div id="error"></div><br>
            <button id="login">登录</button> <input type="submit" value="注册" >
        </form>
    </div>
<%--<script type="text/javascript">--%>
    <%--$(function () {--%>
        <%--$("#login").click(function () {--%>

        <%--})--%>
    <%--})--%>
<%--</script>--%>
</body>
</html>

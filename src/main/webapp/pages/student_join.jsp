<%@ page import="sun.misc.Request" %>
<%@ page import="jdk.nashorn.internal.ir.RuntimeNode" %>
Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/13
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student</title>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <link href="../css/student.css" rel="stylesheet" type="text/css">
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <script src="../js/mustache.js" type="text/javascript"></script>
    <script src="../js/jquery.cookie.js" type="text/javascript"></script>
    <script src="../js/student.js" type="text/javascript"></script>
    <script src="../js/timers.js" type="text/javascript"></script>

</head>
<body class="index">
<div class="head">
    <div class="navgation"><p><a id="tojoin" onclick="var token = $.cookie('token'); window.open('/student?token='+token, '_self')" style="color: dodgerblue">参加考试</a></p></div>
    <div class="navgation"><p><a id="joined" onclick="var token = $.cookie('token'); window.open('/paper/get/account/'+token, '_self')">我的答卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit" >退出</a></p></div>
</div>
<div class="body">
    <div class="paper">
        <br><input type="text"  id="examId" class="password" placeholder="请输入试卷序号" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}"><br>
        <br><input type="text" id="password" placeholder="请输入考试密码" class="password"><br>
        <%--<br><input type="text" id="studentId" placeholder="请输入学号" class="password"><br>--%>
        <div class="error" style="color: red"></div>
        <br><button  type="button" id="join" style="border: none;background-color: inherit;font-size: 18px">参加</button><br>
    </div>
</div>

</body>
</html>

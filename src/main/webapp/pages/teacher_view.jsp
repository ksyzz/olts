<%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/18
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher_view</title>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/teacher.js"></script>
</head>
<body>
<div class="head">
    <div class="navgation"><p><a id="create" onclick="var token = $.cookie('token'); window.open('/teacher?token='+token, '_self')" style="color: dodgerblue">创建试卷</a></p></div>
    <div class="navgation"><p><a id="search" onclick="var token = $.cookie('token'); window.open('/teacher_view?token='+token, '_self')">查看试卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit">退出</a></p></div>
</div>
<div class="body">

</div>
</body>
</html>
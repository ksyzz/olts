<%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/13
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher</title>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/teacher.js"></script>
</head>
<body>
    <div class="head">
        <div class="navgation"><p><a id="create" style="color: dodgerblue">创建试卷</a></p></div>
        <div class="navgation"><p><a id="search">查看试卷</a></p></div>
        <div class="user"><p>${account.userName}|<a id="exit">退出</a></p></div>
    </div>
    <div class="body">
        <div class="list" >
            <br>
            <button class="type">单选题</button>
            <button class="type">多选题</button>
            <button class="type">问答题</button>
        </div>
    </div>
</body>
</html>

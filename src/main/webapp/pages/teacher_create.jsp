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
    <title>Teacher_create</title>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/teacher.js"></script>
    <script src="../js/jquery.cookie.js" type="text/javascript"></script>

</head>
<body>
    <div class="head">
        <div class="navgation"><p><a id="create" href="teacher_create.jsp" style="color: dodgerblue">创建试卷</a></p></div>
        <div class="navgation"><p><a id="search" href="teacher_view.jsp">查看试卷</a></p></div>
        <div class="user"><p>${account.userName}|<a id="exit" >退出</a></p></div>
    </div>
    <div class="body">
        <div class="list" >
            <br>
            <button class="type" id="addRadio">单选题</button>
            <button class="type" id="addMultiple">多选题</button>
            <button class="type" id="addEssay">问答题</button>
        </div>
        <div class="paper">
            <input type="text" id="title" class="title" placeholder="请输入试卷标题"><br><br>
            <input type="text" id="time_limited" placeholder="考试时长（分钟）" class="time" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}" >
            <input type="text" id="password" placeholder="请输入考试密码" class="password">
            <button class="create_exam" >创建</button><br>
        </div>
    </div>

    <script type="text/html" id="radio">
        <div class="question">
            <form id="{{questionId}}">
                <br><label >题目:</label><input type="text" class="text" style="width:90%;border: 1px lavenderblush" name="description"><br>
                <br><label>分值:</label><input type="text" class="text" name="score" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}"><br>
                <br><label>解析:</label><input type="text" class="text" name="analysis" style="width:90%;border: 1px lavenderblush"><br>
                <br><div></div><br>
                <br><button style="border: none">添加选项</button>>
            </form>
        </div>

    </script>
</body>
</html>

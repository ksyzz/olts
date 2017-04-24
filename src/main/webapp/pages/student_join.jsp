<%@ page import="sun.misc.Request" %>
<%@ page import="jdk.nashorn.internal.ir.RuntimeNode" %>
Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/13
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <link href="../css/student.css" rel="stylesheet" type="text/css">
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <script src="../js/mustache.js" type="text/javascript"></script>
    <script src="../js/student.js" type="text/javascript"></script>


</head>
<body>
<div class="head">
    <div class="navgation"><p><a id="tojoin" onclick="var token = $.cookie('token'); window.open('/teacher?token='+token, '_self')" style="color: dodgerblue">参加考试</a></p></div>
    <div class="navgation"><p><a id="joined" onclick="var token = $.cookie('token'); window.open('/teacher_view?token='+token, '_self')">我的答卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit" >退出</a></p></div>
</div>
<div class="body">
    <div class="paper">
        <br><input type="text"  id="examId" class="password" placeholder="请输入试卷序号" onkeyup="if(!/^\d+$/.test(this.value)) {alert('只能输入数字 !'); this.value=this.value.replace(/[^\d]+/g,'');}"><br>
        <br><input type="text" id="password" placeholder="请输入考试密码" class="password"><br>
        <br><input type="text" id="studentId" placeholder="请输入学号" class="password"><br>
        <div class="error" style="color: red"></div>
        <button  type="button" id="join">参加</button><br>
    </div>
</div>
<script type="text/html" id="test">
    <div class="head" style="text-align: center;height: 30px">
        <div class="subtop">试卷名：{{title}}</div><div class="subtop"> 考试时长：{{time_limited}}分钟 </div>  <div class="subtop" id="time"> </div>
    </div>
    <div class="body" id="{{id}}">
        {{#questions}}
        <div class="question">
            {{description}}<br>
            {{#if(questionType==RADIO)}}<div style="height: 40px"><input type="radio" name="option.isSolution" ></div>{{/endif}}
            {{#options}}{{/options}}
        </div>
        {{/questions}}
    </div>
</script>
</body>
</html>

<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/5/9
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>exam_detail</title>
    <script src="/js/jQuery3.2.js" type="text/javascript"></script>
    <link href="/css/teacher.css" rel="stylesheet" type="text/css">
    <link href="/css/student.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/js/teacher.js"></script>
    <script src="/js/view.js" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <script type="application/javascript">
        var paperInfos = ${paperInfos}
    </script>
</head>
<body>
<div class="head">
    <div class="navgation"><p><a id="create" onclick="var token = $.cookie('token'); window.open('/teacher?token='+token, '_self')" style="color: dodgerblue">创建试卷</a></p></div>
    <div class="navgation"><p><a id="search" onclick="var token = $.cookie('token'); window.open('/exam/teacher_view?token='+token, '_self')">查看试卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit">退出</a></p></div>
</div>
<div class="body" style="left: 5%;width: 90%;background-color: beige; font-size: 20px" >
    <div class="list" >
        <br>
        <button class="type" id="list">查看答卷列表</button>
        <button class="type" id="topk">查看topK学生</button>
        <button class="type" id="distribution">查看分数分布</button>
    </div>
    <div class="paper" id="paper_list">
        <ul>
            <c:forEach items="${paperInfos}" var="paperInfo">
                <li><a onclick="showPaper(this)">答卷人学号：${paperInfo.accountInfo.id}，答卷人：${paperInfo.accountInfo.userName}，分数：${paperInfo.score}，总分：${paperInfo.examInfo.score}</a></li>
                <div class="view" style="font-size: 16px">
                    <br>
                    <c:forEach items="${paperInfo.answerInfos}" var="answerInfo" varStatus="index">
                        <c:if test="${answerInfo.score==0}">X</c:if>
                        <c:if test="${answerInfo.score!=0}">\/</c:if>
                        ${index.index+1},${answerInfo.questionInfo.description}&nbsp;(${answerInfo.questionInfo.score}分)
                        <br>
                        <c:forEach items="${answerInfo.questionInfo.options}" var="optionInfo" varStatus="o_index">
                            &nbsp;&nbsp;&nbsp;&nbsp;${optionInfo.content}&nbsp;<c:if test="${optionInfo.correct}">\/</c:if> <br>
                        </c:forEach>
                        解析：${answerInfo.questionInfo.analysis}${answerInfo.questionInfo.essay_solution}<br>
                        学生答案：
                        <c:forEach items="${answerInfo.answers}" var="solution">
                            ${solution.content};
                        </c:forEach>
                        ${answerInfo.essay_answer}<br><br>
                    </c:forEach>
                </div>
                <br>
            </c:forEach>
        </ul>
    </div>
    <div class="paper" id="topk_view" style="display: none">
        <label>查看top</label><input type="number"  min="1" style="width: 40px" onchange="if (this.value > 0 ) { grade(this.value);}"><label>学生名单</label>
        <div id="student_list"></div>
    </div>
    <div class="paper" id="distribution_view"  style="display: none"></div>
</div>
</body>
</html>

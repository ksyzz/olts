<%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <link href="../css/student.css" rel="stylesheet" type="text/css">
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <script src="../js/mustache.js" type="text/javascript"></script>
    <script src="../js/jquery.cookie.js" type="text/javascript"></script>
    <script src="../js/exam.js" type="text/javascript"></script>
    <script src="../js/timers.js" type="text/javascript"></script>
</head>
<body>
    <div class="head" style="text-align: center;height: 30px">
        <div class="subtop">试卷名：${examInfo.title}</div><div class="subtop"> 考试时长：${examInfo.time_limited}分钟 &nbsp;总分:${examInfo.score}</div>  <div class="subtop" id="time"></div><div class="subtop" style="width: 10%" ><button id="commit" disabled="disabled" style="width: 100%;height: 100%;background-color: inherit">交卷</button></div>
        <div id="start"><button style="font-size: 20px; left: 48%;"  onclick="startExam(${examInfo.time_limited}, this)">开始考试</button></div>
    </div>

    <div class="body" style="width: 100%;display: none" id="${examInfo.examId}" >
        <form id="paper">
            <c:forEach items="${examInfo.questions}" var="question" varStatus="index">
                <div class="question"  id="question${question.questionId}">
                    ${index.index+1}. ${question.description} (${question.score}分)<br>
                        <input type="hidden" name="questionId" value="${question.questionId}">
                    <c:if test="${question.questionType=='MULTIPLE'}">
                        <c:forEach items="${question.options}" var="option">
                            <div style="height: 40px"><input type="checkbox" name="isSolution${question.questionId}" value="${option.optionId}">${option.content} <c:if test="${option.correct == true}">
                                <input class="answer" type="hidden" value="\/">
                            </c:if></div>
                        </c:forEach>
                        <div class="answer" style="float: none">解析：${question.analysis}</div>

                    </c:if>
                    <c:if test="${question.questionType=='RADIO'}">
                        <c:forEach items="${question.options}" var="option">
                            <div style="height: 40px;"><input type="radio" name="isSolution${question.questionId}" value="${option.optionId}">${option.content}      <c:if test="${option.correct == true}">
                                <input class="answer" type="hidden" value="\/">
                            </c:if></div>

                        </c:forEach>
                        <div class="answer" style="float: none">解析：${question.analysis}</div>

                    </c:if>
                    <c:if test="${question.questionType=='ESSAY'}">
                        <div ><textarea  class="essay" name="essay_answer">答案</textarea></div>
                        <div class="answer" style="float: none">答案：${question.essay_solution}</div>
                    </c:if>
                </div>
            </c:forEach>

        </form>

    </div>

</body>
</html>

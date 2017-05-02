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
        <div class="subtop">试卷名：${examInfo.title}</div><div class="subtop"> 考试时长：${examInfo.time_limited}分钟 </div>  <div class="subtop" id="time"><div class="subtop" style="width: 10%" ><button style="width: 100%;height: 100%;background-color: inherit">交卷</button></div>
    </div>
    <button style="font-size: 20px; left: 48%;" onclick="startExam(${examInfo.time_limited})">开始考试</button>
    <div class="body" style="width: 80%;display: none" id="${examInfo.examId}" >
        <form>
            <c:forEach items="${examInfo.questions}" var="question" varStatus="index">
                <div class="question"  id="${question.questionId}">
                    ${index.index+1}. ${question.description}<br>
                    <c:if test="${question.questionType=='MULTIPLE'}">
                        <c:forEach items="${question.options}" var="option">
                            <div style="height: 40px"><input type="checkbox" name="isSolution" >${option.content}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${question.questionType=='RADIO'}">
                        <c:forEach items="${question.options}" var="option">
                            <div style="height: 40px"><input type="radio" name="isSolution" >${option.content}</div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${question.questionType=='ESSAY'}">
                        <div ><textarea  class="essay" name="essay_answer">答案</textarea></div>
                    </c:if>
                </div>
            </c:forEach>

        </form>

    </div>

</body>
</html>
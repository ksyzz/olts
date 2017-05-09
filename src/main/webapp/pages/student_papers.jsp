<%--
  Created by IntelliJ IDEA.
  User: 夜落尽&天未明
  Date: 2017/5/7 0007
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的试卷</title>
    <link href="/css/teacher.css" rel="stylesheet" type="text/css">
    <link href="/css/student.css" rel="stylesheet" type="text/css">
    <script src="/js/jQuery3.2.js" type="text/javascript"></script>
    <script src="/js/mustache.js" type="text/javascript"></script>
    <script src="/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="/js/timers.js" type="text/javascript"></script>
    <script type="application/javascript">
        window.onload = function () {
            $("#joined").css('color', 'dodgerblue');
            $("#tojoin").css('color', 'black');
        }
        $(function () {
            $("#exit").on('click', function () {
                var token = $.cookie("token");
                $.ajax({
                    url:"/account/logout?token="+token,
                    type:"delete",
                    success:function () {
                        $.cookie('token', '', { expires: -1 });
                        window.open("/pages/login.jsp", "_self");
                    }
                })
            })
        })
        function showPaper(ele){
            var target = $(ele).parent().next();
            var status = target.css("display")
            if (status == "block"){
                target.hide();

            }else {
                target.show();
            }
        }

    </script>
</head>
<body class="index">
<div class="head">
    <div class="navgation"><p><a id="tojoin" onclick="var token = $.cookie('token'); window.open('/student?token='+token, '_self')" style="color: dodgerblue">参加考试</a></p></div>
    <div class="navgation"><p><a id="joined" onclick="var token = $.cookie('token'); window.open('/paper/get/account/'+token, '_self')">我的答卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit" >退出</a></p></div>
</div>
<div class="body" style="width: 100%">
    <c:if test="${paperInfos.size()==0}">
        你还未参加任何考试。
    </c:if>
    <c:if test="${paperInfos.size() != 0}">
        <ul>
        <c:forEach items="${paperInfos}" var="paperInfo">
          <li><a onclick="showPaper(this)">考试：${paperInfo.examInfo.title}，满分:${paperInfo.examInfo.score}，分数：${paperInfo.score}</a></li>
            <div class="view">
                <c:forEach items="${paperInfo.answerInfos}" var="answerInfo" varStatus="index">
                    <c:if test="${answerInfo.score==0}">X</c:if>
                    <c:if test="${answerInfo.score!=0}">\/</c:if>
                    ${index.index+1},${answerInfo.questionInfo.description}&nbsp;(${answerInfo.questionInfo.score}分)
                    <br>
                    <c:forEach items="${answerInfo.questionInfo.options}" var="optionInfo" varStatus="o_index">
                    &nbsp;&nbsp;&nbsp;&nbsp;${optionInfo.content}&nbsp;<c:if test="${optionInfo.correct}">\/</c:if> <br>
                    </c:forEach>
                    解析：${answerInfo.questionInfo.analysis}${answerInfo.questionInfo.essay_solution}<br>
                    你的答案：
                    <c:forEach items="${answerInfo.answers}" var="solution">
                        ${solution.content};
                    </c:forEach>
                    ${answerInfo.essay_answer}<br><br>
                </c:forEach>
            </div>
        </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>

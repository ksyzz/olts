<%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/18
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teacher_view</title>
    <script src="../js/jQuery3.2.js" type="text/javascript"></script>
    <link href="../css/teacher.css" rel="stylesheet" type="text/css">
    <link href="../css/student.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="../js/teacher.js"></script>
    <%--<script src="../js/view.js" type="text/javascript"></script>--%>
    <script src="../js/jquery.cookie.js" type="text/javascript"></script>
    <script type="application/javascript">
        window.onload = function () {
            $("#search").css('color', 'dodgerblue');
            $("#create").css('color', 'black');
        }
        function showPaper(ele){
            var target = $(ele).parent().next().next();
            var status = target.css("display")
            if (status == "block"){
                target.hide();

            }else {
                target.show();
            }
        }
        $(function () {
            $("#exit").on('click', function () {
                var token = $.cookie("token");
                $.ajax({
                    url: "/account/logout?token=" + token,
                    type: "delete",
                    success: function () {
                        $.cookie('token', '', {expires: -1});
                        window.open("/pages/login.jsp", "_self");
                    }
                })
            })
        })
    </script>
</head>
<body>
<div class="head">
    <div class="navgation"><p><a id="create" onclick="var token = $.cookie('token'); window.open('/teacher?token='+token, '_self')" style="color: dodgerblue">创建试卷</a></p></div>
    <div class="navgation"><p><a id="search" onclick="var token = $.cookie('token'); window.open('/exam/teacher_view?token='+token, '_self')">查看试卷</a></p></div>
    <div class="user"><p>${account.userName}|<a id="exit">退出</a></p></div>
</div>
<div class="body" style="left: 5%;width: 90%;background-color: beige; font-size: 20px" >
    <c:if test="${examInfos.size()==0}">
        您还未创建任何考试。
    </c:if>
    <c:if test="${examInfos.size()!=0}">
        <ul>
            <c:forEach items="${examInfos}" var="examInfo" varStatus="index">
                <div class="exam_list"><a onclick="showPaper(this)">${index.index+1},&nbsp;${examInfo.title}&nbsp;&nbsp;&nbsp;&nbsp;总分：${examInfo.score}，考试时长：${examInfo.time_limited}分钟，答卷人数：${examInfo.paper_number}</a>&nbsp;</div>
                <div class="papers"><button onclick="var token = $.cookie('token'); window.open('/paper/get/exam/${examInfo.examId}?token='+token, '_self')">查看答卷</button></div>
                <div class="view" style="font-size: 15px;float: left;">
                    <c:forEach items="${examInfo.questions}" var="question" varStatus="index">
                        ${index.index+1},${question.description}&nbsp;(${question.score}分)
                        <br>
                        <c:forEach items="${question.options}" var="optionInfo" varStatus="o_index">
                            &nbsp;&nbsp;&nbsp;&nbsp;${optionInfo.content}&nbsp;<c:if test="${optionInfo.correct}">\/</c:if> <br>
                        </c:forEach>
                        解析：${question.analysis}${question.essay_solution}<br>
                    </c:forEach>
                </div>
            </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
<%@ page import="org.hibernate.Session" %><%--
  Created by IntelliJ IDEA.
  User: csdc01
  Date: 2017/4/12
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        window.onload= function open() {
            var username = '<%=session.getAttribute("userName")%>';
            if (username != ""){
                alert("has been login");
                window.close();
            }
                }
    </script>

</head>
<body>
    test;
</body>
</html>

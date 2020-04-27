<%--
  Created by IntelliJ IDEA.
  User: lqk
  Date: 2020/4/19
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<jsp:forward page="/emps"></jsp:forward>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="emps" > dianji </a>
</body>
</html>


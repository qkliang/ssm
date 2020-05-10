<%@ page import="com.lqk.test.FreeMarker" %>
<%@ page import="com.lqk.test.DbUtils" %><%--
  Created by IntelliJ IDEA.
  User: lqk
  Date: 2020/5/8
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>chengg</h1>
<%

    String s = "sdfd" + "\r\n" + "sdfdfsdfs>";
    String s1 = FreeMarker.javaScriptStringEnc(s);
    System.out.println(s1);
%>
<script type="text/javascript">
    function aaa() {
        var s = "<%=s1%>";
        alert(s);
    }
    aaa();


</script>
</body>
</html>

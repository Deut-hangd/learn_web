<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/20
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Idiom.jsp</title>
</head>
<body>
<h2>四字成语接龙</h2>
<%
    String str = (String)application.getAttribute("message");
    if (str != null){
        out.print(str);
    }else{
        out.print("还没有成语,请您给出第一个成语.<br>");
    }
%>
<form action="HandleServlet" method="post">
    <input type="text" name="idiom" ><br>
    <input type="submit" value="提交">
</form>
</body>
</html>

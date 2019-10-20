<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/20
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>board.jsp</title>
</head>
<body>
<form action="show.jsp" method="post">
    用户名:&nbsp&nbsp&nbsp&nbsp<input name="name" type="text" ><br>
    留言标题:<input name="title" type="text"><br>
    留言:<br>
    <textarea name="content" rows="10" cols="20"></textarea><br>
    <br>
    <input type="submit" value="提交"><br>
    <a href="process.jsp">查看留言板</a>
</form>
</body>
</html>

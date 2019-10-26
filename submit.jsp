<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/26
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>submit.jsp</title>
</head>
<body>
<h1>欢迎您使用本系统</h1>
<form action="IncreaseServlet" method="post">
    商品名称:<input name="mark" type="text"><br>
    商品价格:<input name="price" type="text"><br>
    商品数量:<input name="number" type="text"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>

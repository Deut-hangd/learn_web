<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/5
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register.jsp</title>
</head>
<body>
<h2><p align="center">欢迎注册</p></h2>
<form action="RegisterServlet" method="post">
    <table align="center" border="0">
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>确认密码:</td>
            <td><input type="text" name="surePassword"></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
</html>

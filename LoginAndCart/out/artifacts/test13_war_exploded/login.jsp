<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/20
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder"%>
<html>
<head>
    <title>register.jsp</title>
</head>
<body>
<%
    int count = 0;
    String userName = null;
    String password = null;
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("userName")
                && !cookies[i].getName().equals("password")){
            userName = cookies[i].getValue();
            //userName = URLEncoder.encode(userName,"UTF-8");
            count++;
        }else if(cookies[i].getName().equals("password")
                && !cookies[i].getName().equals("userName")){
            password = cookies[i].getValue();
            password = URLEncoder.encode(password,"UTF-8");
            count++;
        }
        if (count > 1){
            response.sendRedirect("QuickLoginServlet" +
                    "?userName=" + userName + "&password=" + password);
            return;
        }
}
%>
<h2><p align="center">欢迎登陆本系统</p></h2>
<form action="LoginServlet" method="post">
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
            <td>两周内免登录</td>
            <td><input type="checkbox" name="keep"></td>
        </tr>
        <tr>
            <td><input type="submit" value="登录"></td>
            <td align="right"><button><a href="register.jsp">注册</a></button></td>
        </tr>
    </table>
</form>
</body>
</html>

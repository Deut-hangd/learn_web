<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/14
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" language="java" %>
<html>
<body>
<%
    //免登录状态检验
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie: cookies) {
        if(cookie.getName().equals("username")){
            response.sendRedirect("ValidateServlet");
            return;
        }
    }
%>
<script type = "text/javascript">
    //刷新验证码函数
    function refresh(){
        loginForm.imgValidate.src = "validate.jsp?id=" + Math.random();
    }
</script>
欢迎登陆本系统<br>
<form name = "loginForm" action = "ValidateServlet" method = "post">
    请您输入账号:<input type = "text" name = "username"/><br>
    请您输入密码:<input type = "password" name = "password"/><br>
    <input type = "checkbox" name = "keep">两周内免登录<br>
    请输入验证码:<input type = "text" name = "code" size= "10"/>
    <img name = "imgValidate" src = "validate.jsp" onclick = "refresh()"><br>
    <input type = "submit" value = "登陆">
</form>
</body>
</html>

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
    //���¼״̬����
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie: cookies) {
        if(cookie.getName().equals("username")){
            response.sendRedirect("ValidateServlet");
            return;
        }
    }
%>
<script type = "text/javascript">
    //ˢ����֤�뺯��
    function refresh(){
        loginForm.imgValidate.src = "validate.jsp?id=" + Math.random();
    }
</script>
��ӭ��½��ϵͳ<br>
<form name = "loginForm" action = "ValidateServlet" method = "post">
    ���������˺�:<input type = "text" name = "username"/><br>
    ������������:<input type = "password" name = "password"/><br>
    <input type = "checkbox" name = "keep">���������¼<br>
    ��������֤��:<input type = "text" name = "code" size= "10"/>
    <img name = "imgValidate" src = "validate.jsp" onclick = "refresh()"><br>
    <input type = "submit" value = "��½">
</form>
</body>
</html>

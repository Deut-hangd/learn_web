<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/20
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dragon.jsp</title>
</head>
<body>
<%!
    String message = "";
    ServletContext application;
    synchronized void sendMessage(String s){
        application = getServletConfig().getServletContext();
        message = message + s + "->";
        application.setAttribute("message",message);
    }
%>
<%
    String content = (String)session.getAttribute("Idiom");
    sendMessage(content);
    out.print("您的四字成语"+content+"已提交,3秒后返回提交界面.");
    response.addHeader("refresh","3;url=Idiom.jsp");
%>
</body>
</html>

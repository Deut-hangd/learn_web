<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/14
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>登陆成功</h2>
<%
    String  username = request.getParameter("username");
    int counter = 0;
    HttpSession hs = request.getSession(true);
    Object count = hs.getAttribute("COUNTER");
    if (count == null){
        counter = 1;
    }else{
        counter = ((Integer)count).intValue();
        counter++;
    }
    hs.setAttribute("COUNTER",new Integer(counter));
%>
<%= "欢迎您,尊敬的" + username + ",这是您第" + counter + "次访问本网站."%>
</body>
</html>

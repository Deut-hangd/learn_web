<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/7
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showNumber.jsp</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String goodsName = new String(request.getParameter("goodsName").getBytes("ISO-8859-1"),"UTF-8");
    String goodsNumber = request.getParameter("goodsNumber");
%>
<p align="center">您要购买的<%= goodsName%>数量不足.<br></p>
<p align="center">现在的数量是<%= goodsNumber%><br></p>
<p align="center">请重新选择.<br></p>
<p align="center"><a href="shop.jsp">返回继续购物</a></p>
</body>
</html>

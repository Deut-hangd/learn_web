<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/7
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error.jsp</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String goodsName = new String(request.getParameter("goodsName").getBytes("ISO-8859-1"),"UTF-8");
    String goodsPrice = request.getParameter("goodsPrice");
    String goodsNumber = request.getParameter("goodsNumber");
%>
您要删除的订单量不足.<br>
您要买的是:<%= goodsName%>,价钱为:<%= goodsPrice%><br>
您已定购的数量为<%= goodsNumber%><br>
<a href="showCart.jsp">返回购物车</a>
</body>
</html>

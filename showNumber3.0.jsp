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
    String goodsName = request.getParameter("goodsName");
    String goodsNumber = request.getParameter("goodsNumber");
%>
您要购买的<%= goodsName%>数量不足.<br>
现在的数量是<%= goodsNumber%><br>
请重新选择.<br>
<a href="shop.jsp">返回继续购物</a>
</body>
</html>

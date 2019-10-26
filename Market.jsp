<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/26
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.test.GoodServlet"
         import="java.util.ArrayList"
%>

<html>
<head>
    <title>Market.jsp</title>
</head>
<body>
<%
ArrayList goods = (ArrayList)application.getAttribute("goods");
if (goods == null){
    out.print("<h1>抱歉,暂无商品出售,请您稍后再来.</h1>");
}else{
%>
<table border="1">
<tr>
    <td><%= "商品名"%></td>
    <td><%= "商品价格"%></td>
    <td><%= "商品数量"%></td>
    <td><%= "购买"%></td>
</tr>
<%
    for(int i = 0; i < goods.size(); i++){
        GoodServlet good = (GoodServlet) goods.get(i);
%>
<tr bgcolor="green">
    <td><%= good.getName()%></td>
    <td><%= good.getPrice()%></td>
    <td><%= good.getNumber()%> </td>
    <td><%= "购买"%> </td>
</tr>
    <%
    }
}
    %>
</table>
</body>
</html>

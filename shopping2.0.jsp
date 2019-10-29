<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/27
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.test.GoodsServlet"
         import="java.util.HashMap"
         import="java.util.Set"
         import="java.util.Iterator"
%>
<html>
<head>
    <title>shopping.jsp</title>
</head>
<body>
<a href="showCart.jsp">查看购物车</a>
<hr>
<%
    HashMap mall = (HashMap)application.getAttribute("Mall");
    if (mall == null){
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
        Set set = mall.keySet();
        Iterator ite = set.iterator();
        while(ite.hasNext()){
            String mark = (String)ite.next();
            GoodsServlet goods = (GoodsServlet) mall.get(mark);
    %>
    <tr bgcolor="green">
        <td><%= goods.getName()%></td>
        <td><%= goods.getPrice()%></td>
        <td><%= goods.getNumber()%> </td>
        <td><a href="buy.jsp?name=<%= goods.getName()%>">购买</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

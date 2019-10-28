<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/27
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.HashMap"
         import="com.test.GoodServlet"
         import="java.util.Set"
         import="java.util.Iterator"
%>
<html>
<head>
    <title>showCart.jsp</title>
</head>
<body>
<%
    HashMap goods = (HashMap)session.getAttribute("sessionGoods");
    Object sum = session.getAttribute("SUM");
    float sumer = ((Float)sum).floatValue();
    if (goods == null){
        out.print("暂无商品信息,请稍后再试.");
        out.print("<a action='shopping.jsp'>返回商城</a>");
    }else{
%>
<table border="1" bgcolor="green">
    <tr>
        <td><%= "商品名"%></td>
        <td><%= "商品价格"%></td>
        <td><%= "商品数量"%></td>
        <td><%= "删除"%></td>
    </tr>
    <%
        Set set = goods.keySet();
        Iterator ite = set.iterator();
        while(ite.hasNext()){
            String mark = (String)ite.next();
            GoodServlet good = (GoodServlet)goods.get(mark);
    %>
    <tr>
        <td><%= good.getName()%></td>
        <td><%= good.getPrice()%></td>
        <td><%= good.getNumber()%></td>
        <td><a href="RemoveServlet?name=<%= good.getName()%>" type="hidden">删除</a></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
<%= "现金总额为:" + sumer%><br>
<hr>
<a href="shopping.jsp">继续购物</a>
</body>
</html>


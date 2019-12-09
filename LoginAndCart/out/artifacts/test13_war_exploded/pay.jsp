<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/9
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap"
         import="java.util.Set" %>
<%@ page import="java.util.Iterator"
         import="vo.Goods" %>
<html>
<head>
    <title>pay.jsp</title>
</head>
<body>
<%
    HashMap cart = (HashMap)session.getAttribute("CART");
    if (cart == null){
        out.print("您的购物车为空,请添加商品.");
    }else{
        Set set = cart.keySet();
        Iterator ite = set.iterator();
        while(ite.hasNext()){
            String goodsId = (String) ite.next();
            Goods goods = (Goods) cart.get(goodsId);
%>
<p>您花费<%= goods.getGoodsPrice() * goods.getGoodsNumber()%>元,
    购买了<%= goods.getGoodsNumber()%> 个 <%= goods.getGoodsName()%>.</p><br>
<%
        }
        Object sum = session.getAttribute("MONEY");
        float money = ((Float)sum).floatValue();
%>
<h5>您总共花费了<%= money%>元.</h5>
<%
        money = 0;
        cart = null;
        session.setAttribute("CART", cart);
        session.setAttribute("MONEY", money);
    }
%>
<p><a href="shop.jsp">继续购物</a></p>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/6
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="java.util.HashMap"
    import="java.util.Set"
    import="java.util.Iterator"
    import="vo.Goods"
%>
<html>
<head>
    <title>showCart.jsp</title>
</head>
<body>
<table board="1">
    <tr bgcolor="pink">
        <td>商品编号</td>
        <td>商品名</td>
        <td>商品价格</td>
        <td>商品类型</td>
        <td>商品数量</td>
        <td>删除数量</td>
        <td>删除</td>
    </tr>

<%
    HashMap cart = (HashMap)session.getAttribute("CART");
    if(cart == null){
        out.print("您的购物车现在为空,请添加商品.");
    }else{
        //遍历购物车将商品输出到页面
        Set set = cart.keySet();
        Iterator ite = set.iterator();
        while(ite.hasNext()){
            String goodsId = (String)ite.next();
            Goods goods = (Goods)cart.get(goodsId);
%>

    <tr bgcolor="green">
        <td><%= goods.getGoodsId()%></td>
        <td><%= goods.getGoodsName()%></td>
        <td><%= goods.getGoodsPrice()%></td>
        <td><%= goods.getGoodsType()%></td>
        <td><%= goods.getGoodsNumber()%></td>
        <form action="RemoveServlet" method="post">
            <input type="hidden" name="goodsId" value="<%= goods.getGoodsId()%>">
            <td><input type="text" name="removeNumber" size="5"></td>
            <td><input type="submit" value="删除"></td>
        </form>
    </tr>

<%
        }
    }
%>
</table>
现金总额:<%= session.getAttribute("MONEY")%><hr>
<br>
<a href="shop.jsp">继续购物</a>
</body>
</html>

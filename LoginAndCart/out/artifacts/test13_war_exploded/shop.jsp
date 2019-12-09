<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/4
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         import="java.util.HashMap"
         import="java.util.Set"
         import="java.util.Iterator"
         import="vo.Goods"
%>
<html>
<head>
    <title>shop.jsp</title>
</head>
<body>
<h5><p align="right"><a href="login.jsp">退出登录</a></p></h5>
<h4><p align="center">欢迎购买商品</p></h4>
<table border="1" align="center">
    <tr bgcolor="pink">
        <td>商品编号</td>
        <td>商品名</td>
        <td>商品价格</td>
        <td>商品类型</td>
        <td>商品数量</td>
        <td>购买</td>
    </tr>
<%
    //遍历哈希表输出查询数据库结果
    HashMap mall = (HashMap)application.getAttribute("MALL");
    if (mall != null){
       Set set = mall.keySet();
       Iterator ite = set.iterator();
       while(ite.hasNext()){
           String goodsId = (String)ite.next();
           Goods goods = (Goods)mall.get(goodsId);
%>
    <tr bgcolor="green">
        <td><%= goods.getGoodsId()%></td>
        <td><%= goods.getGoodsName()%></td>
        <td><%= goods.getGoodsPrice()%></td>
        <td><%= goods.getGoodsType()%></td>
        <td><%= goods.getGoodsNumber()%></td>
        <td><a href="buyGoods.jsp?goodsId=<%= goods.getGoodsId()%>">购买</a></td>
    </tr>
    <%
       }
   }
    %>

</table>
<p align="center"><a href="showCart.jsp">查看购物车</a></p>
</body>
</html>

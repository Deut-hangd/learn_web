<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/5
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.HashMap"
         import="vo.Goods"
         import="java.util.Set"
         import="java.util.Iterator"
%>
<html>
<head>
    <title>buyGoods.jsp</title>
</head>
<body>
<%
    //获取参数
    Goods goods = null;
    String goodsId = request.getParameter("goodsId");
    HashMap mall = (HashMap)application.getAttribute("MALL");
    if (goodsId != null){
        Set set = mall.keySet();  //遍历商品清单
        Iterator ite = set.iterator();
        while(ite.hasNext()){
            String mark = (String)ite.next();
            goods = (Goods) mall.get(mark);
            if (goodsId.equals(goods.getGoodsId())){
                break;
            }
        }
    }
    if (goods != null){
%>
<p align="center">您要购买的是:<%= goods.getGoodsName()%></p>
<form action="AddServlet" method="post">
    <p align="center"><%= goods.getGoodsName() %>的单价是<%= goods.getGoodsPrice()%><br></p>
    <p align="center">请输入您要购买的数量: <input name="demandNumber" type="text"></p>
    <input name="goodsId" type="hidden" value="<%= goods.getGoodsId()%>">
    <p align="center"><input type="submit" value="购买"></p>
</form>
<%
    }else{
        out.print("<p align='center'>您要购买的商品不存在.</p>");
%>
<p align="center"><a href="shop.jsp">返回继续购物</a></p>
<%
    }
%>
</body>
</html>

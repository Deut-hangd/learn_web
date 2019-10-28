<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/27
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.test.GoodServlet"
         import="java.util.HashMap"
         import="java.util.Set"
         import="java.util.Iterator"
%>
<html>
<head>
    <title>buy.jsp</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "utf-8");
    HashMap goods = (HashMap) application.getAttribute("applicationGoods");
    //获取实例对象
    GoodServlet good = null;
    if (name != null) {
        Set set = goods.keySet();
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            String mark = (String)ite.next();
            good = (GoodServlet)goods.get(mark);
            if (good.getName().equals(name)) {
                break;
            }
        }
    }
%>
<% if (good != null){
%>
<h5>您要购买的是:</h5>
<%= good.getName()%><br>
<h5>单价为:</h5>
<%= good.getPrice()%><br>
<h5>购买数量:</h5>
<form action="AddServlet" method="post">
    <input type="hidden" name="goodname" value="<%= good.getName()%>">
    <input type="hidden" name="goodprice" value="<%= good.getPrice()%>">
    <input type="hidden" name="goodnumber" value="<%= good.getNumber()%>">
    <input type="text" name="number">
    <input type="submit" value="提交">
</form>
<%
}else{
    response.setCharacterEncoding("UTF-8");
    out.print("抱歉未找到您想要的商品");
%>
<a href="shopping.jsp">点击返回购物界面.</a>
<%
    }
%>
</body>
</html>

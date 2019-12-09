<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/11/4
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="vo.Goods"%>
<html>
<head>
    <title>input.jsp</title>
</head>
<body>
<h4><p align="center">商品编号是主键,不能重复,每个信息都必须输入!</h4></p>
<form action="InsertServlet" method="post">
<table bgcolor="green" align="center">
    <tr>
        <td>商品编号:</td>
        <td><input type="text" name="goodsId"></td>
    </tr>
    <tr>
        <td>商品名:</td>
        <td><input type="text" name="goodsName"></td>
    </tr>
    <tr>
        <td>商品价格:</td>
        <td><input type="text" name="goodsPrice"></td>
    </tr>
    <tr>
        <td>商品类型:</td>
        <td><input type="text" name="goodsType"></td>
    </tr>
    <tr>
        <td>商品数量:</td>
        <td><input type="text" name="goodsNumber"></td>
    </tr>
    <tr>
        <td><input type="submit" value="提交"></td>
        <td><input type="reset" value="重置"></td>
    </tr>
</table>
</form>
</body>
</html>

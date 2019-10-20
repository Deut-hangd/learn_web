<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/20
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList"
         import="java.util.Date"
         import="java.text.SimpleDateFormat"
%>
<html>
<head>
    <title>show.jsp</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");  //获取用户名
    String title = request.getParameter("title");  //获取标题
    String content = request.getParameter("content");  //获取正文
    ServletContext application2 = getServletConfig().getServletContext();  //获得application对象
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    String user = "时间:" + dateFormat.format(date.getTime()) + "<br>" + "留言人:" + name
        + "<br>" + "标题:" + title + "<br>" + "正文:" + "<br>" + content;
    //从application2处获得users,若为空则实例化
    ArrayList users = (ArrayList)application2.getAttribute("users");  //获取application中users的值
    if (users == null){  //若users的值为空
    users = new ArrayList();  //重新实例化users
    application2.setAttribute("users",users);  //将users以键值的形式存入application
         }
         //将留言人信息存入users
    if (name != null && !"".equals(name)){
        users.add(user);  //添加留言信息
    }
%>
<a href = "board.jsp">返回留言界面</a>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/20
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.ArrayList"%>
<html>
<head>
    <title>process.jsp</title>
</head>
<body>
<%
    ServletContext application3 = getServletConfig().getServletContext();  //获取application对象
    ArrayList users = (ArrayList)application3.getAttribute("users");  //获取application中的users的值
    if (users.size() > 0){  //若留言人数不为0
        //循环遍历users,依次输出留言信息
        for (int i = 0; i < users.size(); i++){
            out.print("NO:" + (i+1) + "<br>" + users.get(i) + "<br>");
            %>
<hr>
<%
        }
    }else{  //若无留言人留言
        out.print("暂无用户留言.<br>");
    }
%>
<a href = "board.jsp">返回主界面</a>
</body>
</html>

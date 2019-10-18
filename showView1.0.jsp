<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/14
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import ="java.util.Date"%>
<html>
<body>
<%
    HttpSession hs = request.getSession( );  //获取会话对象
    Object count = hs.getAttribute("COUNTER");
    String p = (String)hs.getAttribute("PASS");
    String username = request.getParameter("username");
    Cookie[] cookies = request.getCookies();  //获取cookie数组
    //判定用户身份是否合法
    if (p == null){
        response.sendRedirect("refresh.jsp");
        return;
    }
    %>
<h2>登陆成功</h2>
<%!int counter = 0;%>
<%
    //统计用户访问次数
    if (count == null){
        counter = 1;
    }else{
        //若用户不是第一次访问
       for (Cookie cookie: cookies){
           if (cookie.getName().equals("lastAccessTime")){
               out.print("您上次的访问时间是:<br>");
               Long lastAccessTime = Long.parseLong(cookie.getValue());  //获取参数lastAccessTime的值
               Date date = new Date(lastAccessTime);  //创建一个Date类对象,初始值为用户上次的访问时间
               out.print(date.toString() + "<br>");  //格式化输出时间
           }
       }
        counter = ((Integer)count).intValue();  //获取之前的访问次数
        counter++;  //访问次数加1
    }
    hs.setAttribute("COUNTER",new Integer(counter));  //将当前的方为次数存入session对象
    Cookie cookieT = new Cookie("lastAccessTime",System.currentTimeMillis() + "");  //将当前时间存入Cookie对象
    cookieT.setMaxAge(30);  //cookie的最大保存时间
    response.addCookie(cookieT);  //将cookie对象存入客户端
%>
<%= "欢迎您,尊敬的" %>
<%
    //匹配用户身份
    for (Cookie cookie: cookies){
        if (cookie.getName().equals("username")){
            out.print(cookie.getValue());
        }
}
%>
        <%=",这是您第" + counter + "次访问本网站."%>
</body>
</html>

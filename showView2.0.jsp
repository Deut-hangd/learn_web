<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/10/14
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" language="java"
         import="java.util.Date"
         import="java.net.URLDecoder"%>
<html>
<body>
<%
    HttpSession hs = request.getSession( );  //��ȡ�Ự����
    Object count = hs.getAttribute("COUNTER");
    String p = (String)hs.getAttribute("PASS");
    String username = request.getParameter("username");
    Cookie[] cookies = request.getCookies();  //��ȡcookie����
    //�ж��û�����Ƿ�Ϸ�
    if (p == null){
        response.sendRedirect("refresh.jsp");
        return;
    }
    %>
<h2>��½�ɹ�</h2>
<%!int counter = 0;%>
<%
    //ͳ���û����ʴ���
    if (count == null){
        counter = 1;
    }else{
        //���û����ǵ�һ�η���
       for (Cookie cookie: cookies){
           if (cookie.getName().equals("lastAccessTime")){
               out.print("���ϴεķ���ʱ����:<br>");
               Long lastAccessTime = Long.parseLong(cookie.getValue());  //��ȡ����lastAccessTime��ֵ
               Date date = new Date(lastAccessTime);  //����һ��Date�����,��ʼֵΪ�û��ϴεķ���ʱ��
               out.print(date.toString() + "<br>");  //��ʽ�����ʱ��
           }
       }
        counter = ((Integer)count).intValue();  //��ȡ֮ǰ�ķ��ʴ���
        counter++;  //���ʴ�����1
    }
    hs.setAttribute("COUNTER",new Integer(counter));  //����ǰ�ķ�Ϊ��������session����
    Cookie cookieT = new Cookie("lastAccessTime",System.currentTimeMillis() + "");  //����ǰʱ�����Cookie����
    cookieT.setMaxAge(30);  //cookie����󱣴�ʱ��
    response.addCookie(cookieT);  //��cookie�������ͻ���
%>
<%= "��ӭ��,�𾴵�" %>
<%
    //ƥ���û����
    for (Cookie cookie: cookies){
        if (cookie.getName().equals("username")){
            out.print(URLDecoder.decode(cookie.getValue(),"GBK"));
        }
}
%>
        <%=",��������" + counter + "�η��ʱ���վ."%>
</body>
</html>

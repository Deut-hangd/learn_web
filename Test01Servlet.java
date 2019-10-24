package com.test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Test01Servlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String surepass = request.getParameter("surepass");
        String sex = request.getParameter("sex");
        String[] fav = request.getParameterValues("fav");
        String textarea = request.getParameter("textarea");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("姓名:" + user + "<br>");
        out.print("密码:" + password + "<br>");
        out.print("性别:" + sex + "<br>");
        out.print("爱好为:<br>");
        for (int i = 0; i < fav.length; i++){
            out.println(fav[i]);
        }
        out.print("<br>个人介绍:<br>" + textarea + "<br>");
        out.print("<a href = 'regist.html'>返回主界面</a>");
    }
    public void doPost(HttpServletRequest request,
                   HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
}

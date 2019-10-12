package com.test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Night extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html; charset=GBK");
        response.setCharacterEncoding("GBK");
        PrintWriter pw = response.getWriter();
        pw.print("<html> <body>");
        pw.print("<h1>很晚了,您该休息了.");
        pw.print("</body> </html>");
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

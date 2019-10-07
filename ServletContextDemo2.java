package com.test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ServletContextDemo2 extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        ServletContext context = getServletContext();
        String data = (String)context.getAttribute("data");
        response.getWriter().write("data=" + data);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doPost(request,response);
    }
}

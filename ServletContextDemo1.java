package com.test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ServletContextDemo1 extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String data = "ServletContexttest";
        ServletContext context = getServletContext();
        context.setAttribute("data",data);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

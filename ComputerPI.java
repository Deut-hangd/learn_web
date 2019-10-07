package com.test;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ComputerPI extends HttpServlet{
    double sum = 0;
    double i = 1 , j = 1;
    int number = 0;
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter pw = response.getWriter();
        number++;
        sum += i / j;
        j += 2;
        i = -i;
        pw.println("<html>");
        pw.println("<body>");
        pw.println("Your are " +number+ "th People come to here.<br>" );
        pw.println("Now PI=" +4*sum);
        pw.println("</body>");
        pw.println("</html>");
    }

}

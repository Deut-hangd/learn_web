package com.test;
import java.util.Date;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class DateServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=GBK");
        response.setCharacterEncoding("GBK");
        PrintWriter pw = response.getWriter();
        pw.print("<html>");
        pw.print("<body>");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if (hour >= 24){
            response.sendRedirect("Night");
            return;
        }else if (hour >= 6 && hour < 23){
            pw.print("现在的时间是:");
            pw.print(hour + ":" + minute + ":" + second + "<br>");
            pw.print("<h3>欢迎您访问本页面.");
        }
        pw.print("</body>");
        pw.print("</html>");
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

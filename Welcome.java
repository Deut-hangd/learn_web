import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Welcome extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("gb2312");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>主界面</h1>");
        pw.println("<a href = 'LoginServlet'>返回重新登录.</a>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

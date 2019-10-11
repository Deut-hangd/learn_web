import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<body>");
        pw.println("<h1>登录界面</h1>");
        pw.println("<form action=LoginClServlet method=post");
        pw.println("<br>");
        pw.println("<br>");
        pw.println("用户名:<input type=text name=username><br>");
        pw.println("<br>");
        pw.println("密  码:<input type=password name=password><br>");
        pw.println("<input type=checkbox name=keep>两周内免登录<br>");
        pw.println("<input type=submit value=登录><br>");
        pw.println("</form>");
        pw.println("</body>");
        pw.println("</html>");
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request, response);
    }

}

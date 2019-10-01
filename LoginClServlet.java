import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LoginClServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        if (u.equals("China") && p.equals("123")){
            response.sendRedirect("Welcome");
        }else {
            response.sendRedirect("LoginServlet");
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

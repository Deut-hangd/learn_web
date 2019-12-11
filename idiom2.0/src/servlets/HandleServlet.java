package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HandleServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        String idiom = request.getParameter("idiom");  //获取用户输入的成语
        HttpSession session = request.getSession();  //创建session对象
        if (idiom.length() == 4){  //判定成语长度是否为4
            String prevIdiom = (String)session.getAttribute("Idiom");  //获取用户上次输入的成语
            if (prevIdiom == null){
                session.setAttribute("Idiom", idiom);  //保存用户本次输入的成语
            }else{
                if (prevIdiom.charAt(3) == idiom.charAt(0)){  //比较上次输入成语的词尾和本次输入成语的词头
                    session.setAttribute("Idiom",idiom);  //保存用户本次输入的成语
                }else{
                    response.sendRedirect("wrong.jsp");
                    return;
                }
            }
            response.sendRedirect("dragon.jsp");  //跳转到dragon.jsp
            return;
        }
        response.sendRedirect("error.jsp");
        return;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request, response);
    }
}

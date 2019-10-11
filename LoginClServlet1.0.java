import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LoginClServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String isOk;  //标记用户是否合法
        if (u != null && !u.trim().equals("")) {  //用户名不能为空
            if (u.equals("China") && p.equals("123")) {  //验证用户身份
                if (request.getParameter("keep") != null) {  //是否启用免登录
                    Cookie cookie = new Cookie("username", u);
                    cookie.setMaxAge(60*60*24*7*2);  //cookie保存的时间
                    response.addCookie(cookie);  //将cookie保存到浏览器
                }
                HttpSession hs = request.getSession(true);  //获取session对象
                hs.setMaxInactiveInterval(30);  //设置两次会话的最大间隔
                hs.setAttribute("isOk","ok");  //设置isOk属性的值
                response.sendRedirect("Welcome?username=" + u +"&password=" + p);  //重定向到welcmoe
                return;
            }
        } else {  //请用免登录
            Cookie[] cookies = request.getCookies();  //获取cookie
            if (cookies != null && cookies.length > 0) {  //若取得cookie
                for (Cookie cookie : cookies) {  //遍历Cookie
                    if (cookie.getValue().equals("China")) {  //判定用户身份是否正确
                        response.sendRedirect("Welcome");
                        return;
                    }
                }
            }
        }
        if (u != null && !u.trim().equals("")) {  //若用户名为空

        } else {  //用户名不为空,但未找到符合要求的cookie
            response.sendRedirect("LoginServlet");
            return;
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}

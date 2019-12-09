package servlets;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import vo.User;

public class LoginServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String userName =
                request.getParameter("userName");
        String password =
                request.getParameter("password");
        String keep = request.getParameter("keep");
        //用户输入不能为空
        if (userName != null && !userName.trim().equals(" ")
                && password != null && !password.trim().equals(" ")){
            UserDao userDao = new UserDao();
            HashMap<String, User> users = null;
            try {
                users = userDao.getUser(userName);  //查询用户是否存在
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (users != null){  //若用户存在
                if (users.get(userName).getPassword().equals(password)){  //若用户输入密码正确
                    if (keep != null){
                        //设置cookie,并设置存在时间为两周
                        userName = URLEncoder.encode(userName,"UTF-8");
                        Cookie cookieN = new Cookie("userName",userName);
                        Cookie cookieP = new Cookie("password",password);
                        //设置cookie的存活期为两周
                        cookieN.setMaxAge(60*60*24*7*2);
                        cookieN.setMaxAge(60*60*24*7*2);
                        //将cookie保存于客户端
                        response.addCookie(cookieN);
                        response.addCookie(cookieP);
                    }
                    response.sendRedirect("shop.jsp");
                    return;
                }
            }
        }
        response.sendRedirect("Login.jsp");
        return;

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
}

package servlets;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import vo.User;

public class QuickLoginServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String userName =
                request.getParameter("userName");
        userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
        String password =
                request.getParameter("password");
        password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
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

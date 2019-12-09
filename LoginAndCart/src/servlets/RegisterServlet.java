package servlets;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import vo.User;

public class RegisterServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String surePassword = request.getParameter("surePassword");
        //用户输入不能为空,且两次输入密码相等
        if (userName != null && !userName.trim().equals(" ")
                && password != null && !password.trim().equals(" ")
                && surePassword != null && !surePassword.trim().equals(" ")
                && password.equals(surePassword)) {
            UserDao userDao = new UserDao();
            HashMap<String, User> users = null;
            try {
                users = userDao.getUser(userName);  //获取用户信息
            } catch (Exception e) {
                e.printStackTrace();
            }
            //若用户两次输入的密码相等,且该用户不存在
            if (users == null
                    && password.equals(surePassword)) {
                //实例化一个用户对象存入数据库
                UserDao dao = new UserDao();
                User user = new User();
                user.setUserName(userName);
                user.setPassword(password);
                try {
                    dao.insertUser(user);  //将用户信息存入数据库
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("reminder.jsp");  //跳转到登录界面
            } else {
                response.sendRedirect("register.jsp");  //跳转到注册界面
            }
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException{
        this.doGet(request,response);
    }

}

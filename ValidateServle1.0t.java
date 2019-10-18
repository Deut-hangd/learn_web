package com.test;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

public class ValidateServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //得到提交的验证码
        String code = request.getParameter("code");
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        //获取session中的验证码
        HttpSession session = request.getSession();  //获取会话对象
        session.setMaxInactiveInterval(20);  //最大会话间隔
        String randStr = (String) session.getAttribute("randStr");  //获取随机生成的验证码
        PrintWriter out = response.getWriter();
        //判定用户身份是否合法
        if (!u.equals("") && !p.equals("")){
            session.setAttribute("PASS", "OK");
        }
        //判定用户名和密码是否匹配
        if (!u.trim().equals("")){
            //若身份匹配成功
            if (u.equals("China") && p.equals("123")){
                Cookie cookie = new Cookie("username", u);
                cookie.setMaxAge(30);  //设置cookie的存在时间
                response.addCookie(cookie);  //把cookie添加到客户端
                //判定验证码是否正确
                if (code.equals(randStr)){
                    response.sendRedirect("/showView.jsp");
                    return;
                }else{
                    out.println("验证码错误!");
                }
            }
        }else{  //启用两周内免登录
            Cookie[] cookies = request.getCookies();  //获取客户端的cookie数组
            if (cookies != null && cookies.length > 0){
                for (Cookie cookie : cookies) {  //遍历cookies数组找到匹配的cookie
                    if (cookie.getValue().equals("China")){
                       u = cookie.getValue();
                        session.setAttribute("PASS","OK");  //认定用户身份合法
                        response.sendRedirect("/showView.jsp");
                        return;
                    }
                }
            }
        }
        //若用户名和密码为空,且无免登录权限
        if (!u.trim().equals("")){

        } else {
            response.sendRedirect("/refresh.jsp");
            return;
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}
